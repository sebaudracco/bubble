package org.telegram.messenger.config;

import android.net.Uri;
import android.util.Log;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC.TL_contacts_resolveUsername;
import org.telegram.tgnet.TLRPC.TL_messages_importChatInvite;

public class GroupManager {
    public static final String ALLOW_SUBSCRIBE = "allowSubscribe";
    public static final String GROUPS_PREF = "groups";
    private static volatile GroupManager Instance = null;
    public static final String PREVIOUS_LINKS_SET = "prevLinksSet";
    private String botUser = null;
    private Lock lock = new ReentrantLock();

    private GroupManager() {
    }

    public static GroupManager getInstance() {
        GroupManager localInstance = Instance;
        if (localInstance == null) {
            synchronized (GroupManager.class) {
                try {
                    localInstance = Instance;
                    if (localInstance == null) {
                        GroupManager localInstance2 = new GroupManager();
                        try {
                            Instance = localInstance2;
                            localInstance = localInstance2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            localInstance = localInstance2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return localInstance;
    }

    public void requestGroupLinks(String widgetId, String guid) {
        new OkHttpClient().newCall(new Builder().url("http://remote.appsgeyser.com/?widgetid=" + widgetId + "&guid=" + guid).build()).enqueue(new 1(this));
    }

    public void joinGroups(List<String> groupUrlList) {
        for (String url : groupUrlList) {
            parseGroupUrl(url);
        }
    }

    private void parseGroupUrl(String url) {
        Log.d("GroupManager", "Group url parse" + url);
        String initialUrl = url;
        Uri data = Uri.parse(url);
        String username = null;
        String group = null;
        if (data != null) {
            String scheme = data.getScheme();
            if (scheme != null) {
                String sticker;
                String message;
                String botChat;
                if (scheme.equals("http") || scheme.equals("https")) {
                    String host = data.getHost().toLowerCase();
                    if (host.equals("telegram.me") || host.equals("t.me") || host.equals("telegram.dog")) {
                        String path = data.getPath();
                        if (path != null && path.length() > 1) {
                            path = path.substring(1);
                            if (path.startsWith("joinchat/")) {
                                group = path.replace("joinchat/", "");
                            } else if (path.startsWith("addstickers/")) {
                                sticker = path.replace("addstickers/", "");
                            } else if (path.startsWith("msg/") || path.startsWith("share/")) {
                                message = data.getQueryParameter("url");
                                if (message == null) {
                                    message = "";
                                }
                                if (data.getQueryParameter("text") != null) {
                                    if (message.length() > 0) {
                                        message = message + "\n";
                                    }
                                    message = message + data.getQueryParameter("text");
                                }
                            } else if (path.length() >= 1) {
                                List<String> segments = data.getPathSegments();
                                if (segments.size() > 0) {
                                    username = (String) segments.get(0);
                                    if (segments.size() > 1 && Utilities.parseInt((String) segments.get(1)).intValue() == 0) {
                                    }
                                }
                                this.botUser = data.getQueryParameter("start");
                                botChat = data.getQueryParameter("startgroup");
                            }
                        }
                    }
                } else if (scheme.equals("tg")) {
                    if (url.startsWith("tg:resolve") || url.startsWith("tg://resolve")) {
                        data = Uri.parse(url.replace("tg:resolve", "tg://telegram.org").replace("tg://resolve", "tg://telegram.org"));
                        username = data.getQueryParameter(ClientCookie.DOMAIN_ATTR);
                        this.botUser = data.getQueryParameter("start");
                        botChat = data.getQueryParameter("startgroup");
                        if (Utilities.parseInt(data.getQueryParameter("post")).intValue() == 0) {
                        }
                    } else if (url.startsWith("tg:join") || url.startsWith("tg://join")) {
                        group = Uri.parse(url.replace("tg:join", "tg://telegram.org").replace("tg://join", "tg://telegram.org")).getQueryParameter("invite");
                    } else if (url.startsWith("tg:addstickers") || url.startsWith("tg://addstickers")) {
                        sticker = Uri.parse(url.replace("tg:addstickers", "tg://telegram.org").replace("tg://addstickers", "tg://telegram.org")).getQueryParameter("set");
                    } else if (url.startsWith("tg:msg") || url.startsWith("tg://msg") || url.startsWith("tg://share") || url.startsWith("tg:share")) {
                        data = Uri.parse(url.replace("tg:msg", "tg://telegram.org").replace("tg://msg", "tg://telegram.org").replace("tg://share", "tg://telegram.org").replace("tg:share", "tg://telegram.org"));
                        message = data.getQueryParameter("url");
                        if (message == null) {
                            message = "";
                        }
                        if (data.getQueryParameter("text") != null) {
                            if (message.length() > 0) {
                                message = message + "\n";
                            }
                            message = message + data.getQueryParameter("text");
                        }
                    }
                }
            } else if (url.startsWith("@")) {
                username = url.substring(1);
            }
        }
        if (!(group == null || group.length() == 0)) {
            TL_messages_importChatInvite req = new TL_messages_importChatInvite();
            req.hash = group;
            ConnectionsManager.getInstance().sendRequest(req, new 2(this), 2);
        }
        if (username != null) {
            TL_contacts_resolveUsername req2 = new TL_contacts_resolveUsername();
            req2.username = username;
            ConnectionsManager.getInstance().sendRequest(req2, new 3(this));
        }
    }
}

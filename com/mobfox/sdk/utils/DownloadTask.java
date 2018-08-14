package com.mobfox.sdk.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.mobfox.sdk.constants.Constants;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, String> {
    Context context;
    String filepath = "";
    Listener listener;

    public interface Listener {
        void onDownloaded(String str);
    }

    public DownloadTask(Context context, Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    protected String doInBackground(String... params) {
        InputStream input = null;
        OutputStream outputStream = null;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(params[0]).openConnection();
            connection.connect();
            if (connection.getResponseCode() != 200) {
                Log.e(Constants.MOBFOX_NETWORK, "bad connection");
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e) {
                    }
                }
                if (input != null) {
                    input.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
                this.listener.onDownloaded(this.filepath);
                Log.d(Constants.MOBFOX_BANNER, this.filepath + " cached");
                return this.filepath;
            }
            input = connection.getInputStream();
            this.filepath = params[1];
            OutputStream output = new FileOutputStream(this.filepath);
            try {
                byte[] data = new byte[4096];
                while (true) {
                    int count = input.read(data);
                    if (count == -1) {
                        break;
                    }
                    output.write(data, 0, count);
                }
                if (output != null) {
                    try {
                        output.close();
                    } catch (Exception e2) {
                    }
                }
                if (input != null) {
                    input.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
                this.listener.onDownloaded(this.filepath);
                Log.d(Constants.MOBFOX_BANNER, this.filepath + " cached");
                outputStream = output;
                return this.filepath;
            } catch (Exception e3) {
                outputStream = output;
                try {
                    Log.d(Constants.MOBFOX_NETWORK, "download task");
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e4) {
                            if (connection != null) {
                                connection.disconnect();
                            }
                            this.listener.onDownloaded(this.filepath);
                            Log.d(Constants.MOBFOX_BANNER, this.filepath + " cached");
                            return this.filepath;
                        }
                    }
                    if (input != null) {
                        input.close();
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                    this.listener.onDownloaded(this.filepath);
                    Log.d(Constants.MOBFOX_BANNER, this.filepath + " cached");
                    return this.filepath;
                } catch (Throwable th) {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e5) {
                            if (connection != null) {
                                connection.disconnect();
                            }
                            this.listener.onDownloaded(this.filepath);
                            Log.d(Constants.MOBFOX_BANNER, this.filepath + " cached");
                            return this.filepath;
                        }
                    }
                    if (input != null) {
                        input.close();
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                    this.listener.onDownloaded(this.filepath);
                    Log.d(Constants.MOBFOX_BANNER, this.filepath + " cached");
                    return this.filepath;
                }
            } catch (Throwable th2) {
                outputStream = output;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (input != null) {
                    input.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
                this.listener.onDownloaded(this.filepath);
                Log.d(Constants.MOBFOX_BANNER, this.filepath + " cached");
                return this.filepath;
            }
        } catch (Exception e6) {
            Log.d(Constants.MOBFOX_NETWORK, "download task");
            if (outputStream != null) {
                outputStream.close();
            }
            if (input != null) {
                input.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
            this.listener.onDownloaded(this.filepath);
            Log.d(Constants.MOBFOX_BANNER, this.filepath + " cached");
            return this.filepath;
        }
    }
}

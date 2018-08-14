package org.altbeacon.beacon.distance;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import org.altbeacon.beacon.logging.LogManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ModelSpecificDistanceCalculator implements DistanceCalculator {
    private static final String CONFIG_FILE = "model-distance-calculations.json";
    private static final String TAG = "ModelSpecificDistanceCalculator";
    private Context mContext;
    private AndroidModel mDefaultModel;
    private DistanceCalculator mDistanceCalculator;
    private final ReentrantLock mLock;
    private AndroidModel mModel;
    Map<AndroidModel, DistanceCalculator> mModelMap;
    private String mRemoteUpdateUrlString;
    private AndroidModel mRequestedModel;

    class C47881 implements CompletionHandler {
        C47881() {
        }

        public void onComplete(String body, Exception ex, int code) {
            if (ex != null) {
                LogManager.m16379w(ModelSpecificDistanceCalculator.TAG, "Cannot updated distance models from online database at %s", ex, ModelSpecificDistanceCalculator.this.mRemoteUpdateUrlString);
            } else if (code != 200) {
                LogManager.m16379w(ModelSpecificDistanceCalculator.TAG, "Cannot updated distance models from online database at %s due to HTTP status code %s", ModelSpecificDistanceCalculator.this.mRemoteUpdateUrlString, Integer.valueOf(code));
            } else {
                LogManager.m16371d(ModelSpecificDistanceCalculator.TAG, "Successfully downloaded distance models from online database", new Object[0]);
                try {
                    ModelSpecificDistanceCalculator.this.buildModelMapWithLock(body);
                    if (ModelSpecificDistanceCalculator.this.saveJson(body)) {
                        ModelSpecificDistanceCalculator.this.loadModelMapFromFile();
                        ModelSpecificDistanceCalculator.this.mDistanceCalculator = ModelSpecificDistanceCalculator.this.findCalculatorForModelWithLock(ModelSpecificDistanceCalculator.this.mRequestedModel);
                        LogManager.m16375i(ModelSpecificDistanceCalculator.TAG, "Successfully updated distance model with latest from online database", new Object[0]);
                    }
                } catch (JSONException e) {
                    LogManager.m16380w(e, ModelSpecificDistanceCalculator.TAG, "Cannot parse json from downloaded distance model", new Object[0]);
                }
            }
        }
    }

    public ModelSpecificDistanceCalculator(Context context, String remoteUpdateUrlString) {
        this(context, remoteUpdateUrlString, AndroidModel.forThisDevice());
    }

    public ModelSpecificDistanceCalculator(Context context, String remoteUpdateUrlString, AndroidModel model) {
        this.mRemoteUpdateUrlString = null;
        this.mLock = new ReentrantLock();
        this.mRequestedModel = model;
        this.mRemoteUpdateUrlString = remoteUpdateUrlString;
        this.mContext = context;
        loadModelMap();
        this.mDistanceCalculator = findCalculatorForModelWithLock(model);
    }

    public AndroidModel getModel() {
        return this.mModel;
    }

    public AndroidModel getRequestedModel() {
        return this.mRequestedModel;
    }

    public double calculateDistance(int txPower, double rssi) {
        if (this.mDistanceCalculator != null) {
            return this.mDistanceCalculator.calculateDistance(txPower, rssi);
        }
        LogManager.m16379w(TAG, "distance calculator has not been set", new Object[0]);
        return -1.0d;
    }

    DistanceCalculator findCalculatorForModelWithLock(AndroidModel model) {
        this.mLock.lock();
        try {
            DistanceCalculator findCalculatorForModel = findCalculatorForModel(model);
            return findCalculatorForModel;
        } finally {
            this.mLock.unlock();
        }
    }

    private DistanceCalculator findCalculatorForModel(AndroidModel model) {
        LogManager.m16371d(TAG, "Finding best distance calculator for %s, %s, %s, %s", model.getVersion(), model.getBuildNumber(), model.getModel(), model.getManufacturer());
        if (this.mModelMap == null) {
            LogManager.m16371d(TAG, "Cannot get distance calculator because modelMap was never initialized", new Object[0]);
            return null;
        }
        int highestScore = 0;
        AndroidModel bestMatchingModel = null;
        for (AndroidModel candidateModel : this.mModelMap.keySet()) {
            if (candidateModel.matchScore(model) > highestScore) {
                highestScore = candidateModel.matchScore(model);
                bestMatchingModel = candidateModel;
            }
        }
        if (bestMatchingModel != null) {
            LogManager.m16371d(TAG, "found a match with score %s", Integer.valueOf(highestScore));
            LogManager.m16371d(TAG, "Finding best distance calculator for %s, %s, %s, %s", bestMatchingModel.getVersion(), bestMatchingModel.getBuildNumber(), bestMatchingModel.getModel(), bestMatchingModel.getManufacturer());
            this.mModel = bestMatchingModel;
        } else {
            this.mModel = this.mDefaultModel;
            LogManager.m16379w(TAG, "Cannot find match for this device.  Using default", new Object[0]);
        }
        return (DistanceCalculator) this.mModelMap.get(this.mModel);
    }

    private void loadModelMap() {
        boolean mapLoaded = false;
        if (this.mRemoteUpdateUrlString != null) {
            mapLoaded = loadModelMapFromFile();
            if (!mapLoaded) {
                requestModelMapFromWeb();
            }
        }
        if (!mapLoaded) {
            loadDefaultModelMap();
        }
        this.mDistanceCalculator = findCalculatorForModelWithLock(this.mRequestedModel);
    }

    private boolean loadModelMapFromFile() {
        IOException e;
        Throwable th;
        File file = new File(this.mContext.getFilesDir(), CONFIG_FILE);
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream inputStream2 = new FileInputStream(file);
            try {
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream2));
                while (true) {
                    try {
                        String line = reader2.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line).append("\n");
                    } catch (FileNotFoundException e2) {
                        reader = reader2;
                        inputStream = inputStream2;
                    } catch (IOException e3) {
                        e = e3;
                        reader = reader2;
                        inputStream = inputStream2;
                    } catch (Throwable th2) {
                        th = th2;
                        reader = reader2;
                        inputStream = inputStream2;
                    }
                }
                if (reader2 != null) {
                    try {
                        reader2.close();
                    } catch (Exception e4) {
                    }
                }
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (Exception e5) {
                    }
                }
                try {
                    buildModelMapWithLock(sb.toString());
                    reader = reader2;
                    inputStream = inputStream2;
                    return true;
                } catch (IOException e6) {
                    LogManager.m16374e(e6, TAG, "Cannot update distance models from online database at %s with JSON: %s", this.mRemoteUpdateUrlString, sb.toString());
                    reader = reader2;
                    inputStream = inputStream2;
                    return false;
                }
            } catch (FileNotFoundException e7) {
                inputStream = inputStream2;
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e8) {
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e9) {
                    }
                }
                return false;
            } catch (IOException e10) {
                e6 = e10;
                inputStream = inputStream2;
                try {
                    LogManager.m16374e(e6, TAG, "Cannot open distance model file %s", file);
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (Exception e11) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e12) {
                        }
                    }
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (Exception e13) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e14) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                inputStream = inputStream2;
                if (reader != null) {
                    reader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e15) {
            if (reader != null) {
                reader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return false;
        } catch (IOException e16) {
            e6 = e16;
            LogManager.m16374e(e6, TAG, "Cannot open distance model file %s", file);
            if (reader != null) {
                reader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return false;
        }
    }

    private boolean saveJson(String jsonString) {
        FileOutputStream outputStream = null;
        try {
            outputStream = this.mContext.openFileOutput(CONFIG_FILE, 0);
            outputStream.write(jsonString.getBytes());
            outputStream.close();
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                }
            }
            LogManager.m16375i(TAG, "Successfully saved new distance model file", new Object[0]);
            return true;
        } catch (Exception e2) {
            LogManager.m16380w(e2, TAG, "Cannot write updated distance model to local storage", new Object[0]);
            if (outputStream == null) {
                return false;
            }
            try {
                outputStream.close();
                return false;
            } catch (Exception e3) {
                return false;
            }
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e4) {
                }
            }
        }
    }

    @TargetApi(11)
    private void requestModelMapFromWeb() {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
            LogManager.m16379w(TAG, "App has no android.permission.INTERNET permission.  Cannot check for distance model updates", new Object[0]);
        } else {
            new ModelSpecificDistanceUpdater(this.mContext, this.mRemoteUpdateUrlString, new C47881()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    void buildModelMapWithLock(String jsonString) throws JSONException {
        this.mLock.lock();
        try {
            buildModelMap(jsonString);
        } finally {
            this.mLock.unlock();
        }
    }

    private void buildModelMap(String jsonString) throws JSONException {
        HashMap<AndroidModel, DistanceCalculator> map = new HashMap();
        JSONArray array = new JSONObject(jsonString).getJSONArray("models");
        for (int i = 0; i < array.length(); i++) {
            JSONObject modelObject = array.getJSONObject(i);
            boolean defaultFlag = false;
            if (modelObject.has("default")) {
                defaultFlag = modelObject.getBoolean("default");
            }
            Double coefficient1 = Double.valueOf(modelObject.getDouble("coefficient1"));
            Double coefficient2 = Double.valueOf(modelObject.getDouble("coefficient2"));
            Double coefficient3 = Double.valueOf(modelObject.getDouble("coefficient3"));
            String version = modelObject.getString("version");
            String buildNumber = modelObject.getString("build_number");
            String model = modelObject.getString("model");
            String manufacturer = modelObject.getString("manufacturer");
            CurveFittedDistanceCalculator distanceCalculator = new CurveFittedDistanceCalculator(coefficient1.doubleValue(), coefficient2.doubleValue(), coefficient3.doubleValue());
            AndroidModel androidModel = new AndroidModel(version, buildNumber, model, manufacturer);
            map.put(androidModel, distanceCalculator);
            if (defaultFlag) {
                this.mDefaultModel = androidModel;
            }
        }
        this.mModelMap = map;
    }

    private void loadDefaultModelMap() {
        try {
            buildModelMap(stringFromFilePath(CONFIG_FILE));
        } catch (Exception e) {
            this.mModelMap = new HashMap();
            LogManager.m16374e(e, TAG, "Cannot build model distance calculations", new Object[0]);
        }
    }

    private String stringFromFilePath(String path) throws IOException {
        Throwable th;
        InputStream stream = null;
        BufferedReader bufferedReader = null;
        StringBuilder inputStringBuilder = new StringBuilder();
        try {
            stream = ModelSpecificDistanceCalculator.class.getResourceAsStream(BridgeUtil.SPLIT_MARK + path);
            if (stream == null) {
                stream = getClass().getClassLoader().getResourceAsStream(BridgeUtil.SPLIT_MARK + path);
            }
            if (stream == null) {
                throw new RuntimeException("Cannot load resource at " + path);
            }
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            try {
                for (String line = bufferedReader2.readLine(); line != null; line = bufferedReader2.readLine()) {
                    inputStringBuilder.append(line);
                    inputStringBuilder.append('\n');
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (stream != null) {
                    stream.close();
                }
                return inputStringBuilder.toString();
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (stream != null) {
                    stream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (stream != null) {
                stream.close();
            }
            throw th;
        }
    }
}

package com.qcloud;

import java.util.TreeMap;

import com.qcloud.Module.Wenzhi;
import com.qcloud.Utilities.Json.JSONObject;

public class WenzhiUtil {

    private static final String SECRET_ID = "SecretId";
    private static final String SECRET_ID_VALUE = "AKIDkS4a5Utz54wiJmrEFG6lMp7E9tSqF3sW";
    private static final String SECRET_KEY = "SecretKey";
    private static final String SECRET_KEY_VALUE = "bMxTtNpAPYabVJ1d9ZtAOVbbVJ1c7Wkn";
    private static final String REQUEST_METHOD = "RequestMethod";
    private static final String REQUEST_METHOD_VALUE = "GET";
    private static final String DEFAULT_REGION = "DefaultRegion";
    private static final String DEFAULT_REGION_VALUE = "bj";

    public static JSONObject wenzhiInit(TreeMap<String, Object> params, String action) {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        config.put(SECRET_ID, SECRET_ID_VALUE);
        config.put(SECRET_KEY, SECRET_KEY_VALUE);
        config.put(REQUEST_METHOD, REQUEST_METHOD_VALUE);
        config.put(DEFAULT_REGION, DEFAULT_REGION_VALUE);

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Wenzhi(), config);

        String result = null;
        try {
            result = module.call(action, params);
            JSONObject json_result = new JSONObject(result);
            return json_result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


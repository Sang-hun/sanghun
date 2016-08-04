package com.example.user.cmoney;

import org.json.JSONObject;

public interface onNetworkResponseListener {
    void onSuccess(String api_key, JSONObject response);
    void onFailure(String api_key, String error_cd, String error_msg);

}

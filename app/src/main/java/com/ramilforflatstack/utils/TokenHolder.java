package com.ramilforflatstack.utils;

import android.content.Context;

import com.vk.sdk.VKAccessToken;

/**
 * Created by Ramil on 14.06.2015.
 */
public class TokenHolder {

    private static final String TOKEN_KEY = "vk_sdk_token";

    public static void save(Context context, VKAccessToken token){
        token.saveTokenToSharedPreferences(context, TOKEN_KEY);
    }

    public static VKAccessToken get(Context context){
        return VKAccessToken.tokenFromSharedPreferences(context, TOKEN_KEY);
    }
}

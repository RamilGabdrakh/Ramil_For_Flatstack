package com.ramilforflatstack.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

/**
 * Created by Ramil on 03.03.2015.
 */
public class NetworkUtils {
    public static boolean isNetworkAvailable(@NonNull Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo network = cm.getActiveNetworkInfo();
        return network != null && network.isConnectedOrConnecting();
    }
}

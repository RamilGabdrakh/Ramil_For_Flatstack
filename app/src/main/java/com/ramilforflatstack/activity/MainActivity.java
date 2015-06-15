package com.ramilforflatstack.activity;

import android.os.Bundle;
import android.util.Log;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.ramilforflatstack.BuildConfig;
import com.ramilforflatstack.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.api.VKError;
import com.vk.sdk.dialogs.VKCaptchaDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends VkActivity {

    @InjectView(R.id.progressBar)
    CircleProgressBar progressBar;

    private static final String[] sMyScope = new String[]{
            VKScope.WALL,
            VKScope.FRIENDS
    };

    private VKSdkListener mListener = new VKSdkListener() {
        @Override
        public void onCaptchaError(VKError captchaError) {
            Log.e("my_tag", "onCaptchaError");
            new VKCaptchaDialog(captchaError).show(MainActivity.this);
        }

        @Override
        public void onTokenExpired(VKAccessToken expiredToken) {
            Log.e("my_tag", "onTokenExpired");
            VKSdk.authorize(sMyScope);
        }

        @Override
        public void onAccessDenied(VKError authorizationError) {
            Log.e("my_tag", "onAccessDenied");
            VKSdk.authorize(sMyScope);
        }

        @Override
        public void onReceiveNewToken(VKAccessToken newToken) {
            Log.e("my_tag", "onReceiveNewToken");
            openNewsFeedActivity();
        }

        @Override
        public void onAcceptUserToken(VKAccessToken token) {
            Log.e("my_tag", "onAcceptUserToken");
            openNewsFeedActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.inject(this);
//        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
//        for(String fingerprint : fingerprints) {
//            Log.e("mytag", "fingerprint=" + fingerprint);
//        }

        progressBar.setShowArrow(true);

        VKSdk.initialize(mListener, Long.toString(BuildConfig.VK_API_KEY));
        if (VKSdk.isLoggedIn()) {
            openNewsFeedActivity();
        } else {
            VKSdk.authorize(sMyScope);
        }
    }

    private void openNewsFeedActivity(){
        finish();
        NewsFeedActivity.start(this);
    }
}

package com.ramilforflatstack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.ramilforflatstack.BuildConfig;
import com.ramilforflatstack.R;
import com.ramilforflatstack.tools.NetworkUtils;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.api.VKError;
import com.vk.sdk.dialogs.VKCaptchaDialog;

import droidkit.annotation.InjectView;
import droidkit.util.Sequence;


public class MainActivity extends VkActivity {

    @InjectView(R.id.progressBar)
    CircleProgressBar progressBar;

    public static int NEWS_FEED_REQUEST = Sequence.get().nextInt();
    public static int REAUHTORIZE = Sequence.get().nextInt();

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
//        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
//        for(String fingerprint : fingerprints) {
//            Log.e("mytag", "fingerprint=" + fingerprint);
//        }

        progressBar.setShowArrow(true);

        VKSdk.initialize(mListener, Long.toString(BuildConfig.VK_API_KEY));


        if (!NetworkUtils.isNetworkAvailable(this) || VKSdk.isLoggedIn() || VKSdk.wakeUpSession()) {
            openNewsFeedActivity();
        } else {
            VKSdk.authorize(sMyScope);
        }

    }

    private void openNewsFeedActivity() {
        finish();
        NewsFeedActivity.start(this, NEWS_FEED_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEWS_FEED_REQUEST) {
            if (resultCode == REAUHTORIZE) {
                VKSdk.authorize(sMyScope);
            } else {
                finish();
            }
        }
    }
}

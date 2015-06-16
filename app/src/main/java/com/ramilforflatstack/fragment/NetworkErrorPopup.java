package com.ramilforflatstack.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ramilforflatstack.R;


public class NetworkErrorPopup extends DialogFragment {

    private static int sSingleGuard;

    public static void show(@NonNull FragmentManager fragmentManager) {
        if (sSingleGuard <= 0 && fragmentManager.findFragmentByTag(NetworkErrorPopup.class.getName()) == null) {
            new NetworkErrorPopup().show(fragmentManager, NetworkErrorPopup.class.getName());
            ++sSingleGuard;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new MaterialDialog.Builder(getActivity())
                .title(R.string.error)
                .content(R.string.error_internet)
                .positiveText(R.string.next)
                .backgroundColorRes(R.color.white)
                .titleColorRes(R.color.popup_title)
                .contentColorRes(R.color.popup_text)
                .positiveColorRes(R.color.popup_text)
                .show();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        --sSingleGuard;
        super.onDismiss(dialog);
    }
}

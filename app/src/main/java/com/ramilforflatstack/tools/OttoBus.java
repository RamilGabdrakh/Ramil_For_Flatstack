package com.ramilforflatstack.tools;

import android.os.Looper;
import android.support.annotation.NonNull;

import com.squareup.otto.Bus;

import droidkit.concurrent.MainQueue;

public final class OttoBus {

    private final Bus mBus;

    public OttoBus(Bus mBus) {
        this.mBus = mBus;
    }

    public static OttoBus get() {
        return Holder.INSTANCE;
    }

    public void post(final Object event) {
        MainQueue.invoke(new Runnable() {
            @Override
            public void run() {
                mBus.post(event);
            }
        });
    }

    public void register(@NonNull Object object) {
        mBus.register(object);
    }

    public void unregister(@NonNull Object object) {
        mBus.unregister(object);
    }

    private static final class Holder {
        public static final OttoBus INSTANCE = new OttoBus(new Bus());
    }
}

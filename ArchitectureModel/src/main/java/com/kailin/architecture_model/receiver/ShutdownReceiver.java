package com.kailin.architecture_model.receiver;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;


public final class ShutdownReceiver extends BroadcastReceiver {

    public static final String ACTION_SHUTOWN = "com.kailin.shutdown";

    public static final void shutdownApp(Context context) {
        Intent intent = new Intent();
        intent.setAction(ACTION_SHUTOWN);
        context.sendBroadcast(intent);

        SystemClock.sleep(800);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
        System.gc();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null)
            return;

        if (context instanceof Activity) {
            ((Activity) context).finish();
        } else if (context instanceof Service) {
            ((Service) context).stopSelf();
        }
    }
}

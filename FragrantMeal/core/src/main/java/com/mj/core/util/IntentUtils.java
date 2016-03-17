package com.mj.core.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Intent跳转管理
 *
 * @author Robin Yang
 * @createTime 14-4-29
 */
public class IntentUtils {

    public static void forward(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public static void forward(Context context, Class<?> clasz) {
        Intent intent = new Intent(context, clasz);
        context.startActivity(intent);
    }

    public static void forward(Context context, Class<?> clasz, Bundle bundle) {
        Intent intent = new Intent(context, clasz);

        if (null != bundle)
            intent.putExtras(bundle);

        context.startActivity(intent);
    }

    public static void forwardForResult(Activity activity, Class<?> clasz, int requestCode) {
        Intent intent = new Intent(activity, clasz);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void forwardForResult(Activity activity, Class<?> clasz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(activity, clasz);
        if (null != bundle)
            intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void forwardForResult(Fragment fragment, Class<?> clasz, int requestCode) {
        Intent intent = new Intent(fragment.getActivity(), clasz);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void forwardForResult(Fragment fragment, Class<?> clasz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(fragment.getActivity(), clasz);
        if (null != bundle)
            intent.putExtras(bundle);
        fragment.startActivityForResult(intent, requestCode);
    }


    public static void forwardCall(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }

    public static void forwardDial(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }

    public static void forwardSystemBrowser(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    public static void sendBroadcast(Context context,Intent intent) {
        context.sendBroadcast(intent);
    }
}


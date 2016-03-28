package com.fragrantmeal.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by CaoBin on 2016/1/7.
 */
public class ShowToast {

    public static void showToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}

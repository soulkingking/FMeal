package com.mj.core.ui.widgets.dialogfragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


/**
 * 列表对话框
 *
 * @author Robin Yang
 * @createTime 14-6-6
 */
@SuppressLint("ValidFragment")
public class ListDialogFragment extends DialogFragment {
    private ListDialogFragment mInstance;

    private int mTitleResource;
    private CharSequence[] mDataSet;
    private DialogInterface.OnClickListener mOnClickListener;

    public ListDialogFragment(int titleResource, CharSequence[] listItems, DialogInterface.OnClickListener onClickListener) {
        mInstance = this;
        mTitleResource = titleResource;
        mDataSet = listItems;
        mOnClickListener = onClickListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(mTitleResource)
                .setItems(mDataSet, mOnClickListener);
        return builder.create();
    }

    public ListDialogFragment setTitle(int resource) {
        mTitleResource = resource;
        return mInstance;
    }

    public ListDialogFragment setListResource(CharSequence[] listItems) {
        mDataSet = listItems;
        return mInstance;
    }

    public ListDialogFragment setListItemOnClickListener(DialogInterface.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
        return mInstance;
    }


}

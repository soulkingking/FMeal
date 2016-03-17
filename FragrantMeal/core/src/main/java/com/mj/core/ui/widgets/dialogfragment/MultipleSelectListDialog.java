package com.mj.core.ui.widgets.dialogfragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.mj.core.R;
import java.util.ArrayList;

/**
 * 多选列表对话框
 * Created by robinyang on 15/1/9.
 */

@SuppressLint("ValidFragment")
public class MultipleSelectListDialog extends DialogFragment {
    private MultipleSelectListDialog mInstance;

    private int mTitleResource;
    private CharSequence[] mItems;
    private OnItemSelectFinishListener mItemSelectFinishListener;
    private ArrayList<Integer> mSelectedItems = new ArrayList<Integer>();


    public MultipleSelectListDialog(int titleResource, CharSequence[] items, OnItemSelectFinishListener itemSelectFinishListener) {
        mInstance = this;
        mTitleResource = titleResource;
        mItems = items;
        mItemSelectFinishListener = itemSelectFinishListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(mTitleResource)
                .setMultiChoiceItems(mItems, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            mSelectedItems.add(which);
                        } else if (mSelectedItems.contains(which)) {
                            // Else, if the item is already in the array, remove it
                            mSelectedItems.remove(Integer.valueOf(which));
                        }
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        mItemSelectFinishListener.onItemSelectFinish(mSelectedItems);
                    }
                });
//                .setItems(mItemArrayResource, mOnClickListener);
        return builder.create();
    }

    public MultipleSelectListDialog setTitle(int resource) {
        mTitleResource = resource;
        return mInstance;
    }

    public MultipleSelectListDialog setItemSelectFinishListener(OnItemSelectFinishListener listener) {
        mItemSelectFinishListener = listener;
        return mInstance;
    }

    public static interface OnItemSelectFinishListener {
        void onItemSelectFinish(ArrayList<Integer> selectedItems);
    }

}

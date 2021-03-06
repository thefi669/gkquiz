package com.nextinnovation.gkquiz.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.nextinnovation.gkquiz.R;
import com.nextinnovation.gkquiz.application.App;

/**
 * Created by rifqi on Mar 06, 2016.
 */
public abstract class ConfirmDialog extends DialogFragment {
    private int mTitleRes;
    private int mContentRes;
    private int mYesRes;
    private int mNoRes;

    public ConfirmDialog(int titleStrRes, int contentStrRes) {
        this(titleStrRes, contentStrRes, 0, 0);
    }

    public ConfirmDialog(int titleStrRes, int contentStrRes, int yesRes, int noRes) {
        mTitleRes = titleStrRes;
        mContentRes = contentStrRes;
        mYesRes = yesRes;
        mNoRes = noRes;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirm);
        dialog.show();

        // initialize views
        TextView mNoButton = (TextView)dialog.findViewById(R.id.confirm_dlg_no_button);
        TextView mYesButton = (TextView)dialog.findViewById(R.id.confirm_dlg_yes_button);
        TextView mTitle = (TextView)dialog.findViewById(R.id.confirm_dlg_title);
        TextView mContent = (TextView)dialog.findViewById(R.id.confirm_dlg_content);

        // apply typeface to all views
        mNoButton.setTypeface(App.getBoldTypeface());
        mYesButton.setTypeface(App.getBoldTypeface());
        mTitle.setTypeface(App.getRegularTypeface());
        mContent.setTypeface(App.getRegularTypeface());

        // apply content data
        mTitle.setText(getString(mTitleRes));
        mContent.setText(getString(mContentRes));

        // apply button caption
        if(mYesRes > 0) mYesButton.setText(mYesRes);
        if(mNoRes > 0) mNoButton.setText(mNoRes);
        if(mYesRes == -1) mYesButton.setVisibility(View.GONE);
        if(mNoRes == -1) mNoButton.setVisibility(View.GONE);

        // apply listeners
        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoClicked();
                dismiss();
            }
        });
        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onYesClicked();
                dismiss();
            }
        });

        return dialog;
    }

    protected abstract void onYesClicked();
    protected abstract void onNoClicked();
}

package com.vxfc.shenxin.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.vxfc.shenxin.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ChooseDialogFragment extends DialogFragment {
    @InjectView(R.id.btn_live)Button btnLive;
    @InjectView(R.id.btn_fenxi) Button btnFenxi;
    @InjectView(R.id.btn_quiz) Button btnQuiz;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_choose, null);
        builder.setView(view);

        ButterKnife.inject(this,view);
        Dialog dialog=builder.create();
        dialog.getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

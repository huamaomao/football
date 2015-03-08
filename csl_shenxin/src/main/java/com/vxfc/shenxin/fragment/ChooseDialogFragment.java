package com.vxfc.shenxin.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.vxfc.shenxin.R;
import com.vxfc.shenxin.ui.LiveActivity;
import com.vxfc.shenxin.util.Util;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ChooseDialogFragment extends DialogFragment {
    @InjectView(R.id.btn_live)ImageButton btnLive;
    @InjectView(R.id.btn_fenxi) ImageButton btnFenxi;
    @InjectView(R.id.btn_quiz) ImageButton btnQuiz;
    private OnClickListener onClickListener;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_choose,null);
        ButterKnife.inject(this,view);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));
        return view;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @OnClick(R.id.btn_live)
    public void onLive(){
       if (Util.notNull(onClickListener)){
           onClickListener.onLive();
       }
        dismiss();
    }
    @OnClick(R.id.btn_fenxi)
    public void onFenxi(){
        if (Util.notNull(onClickListener)){
            onClickListener.onFenxi();
        }
        dismiss();
    }
    @OnClick(R.id.btn_quiz)
    public void onQuiz(){
        if (Util.notNull(onClickListener)){
            onClickListener.onQuiz();
        }
        dismiss();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

   public interface OnClickListener{
       void onLive();
       void onFenxi();
       void onQuiz();
   }
}

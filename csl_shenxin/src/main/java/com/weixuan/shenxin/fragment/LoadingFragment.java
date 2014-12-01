package com.weixuan.shenxin.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.weixuan.shenxin.R;

/**
 * Created by Hua on 2014/8/18.
 */
public class LoadingFragment extends DialogFragment {
    private  View mProgressContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int mNum=4;
        int style = android.app.DialogFragment.STYLE_NORMAL, theme = 0;
        switch ((mNum - 1) % 6) {
            case 1:
                style = android.app.DialogFragment.STYLE_NO_TITLE;
                break;
            case 2:
                style = android.app.DialogFragment.STYLE_NO_FRAME;
                break;
            case 3:
                style = android.app.DialogFragment.STYLE_NO_INPUT;
                break;
            case 4:
                style = android.app.DialogFragment.STYLE_NORMAL;
                break;
            case 5:
                style = android.app.DialogFragment.STYLE_NORMAL;
                break;
            case 6:
                style = android.app.DialogFragment.STYLE_NO_TITLE;
                break;
            case 7:
                style = android.app.DialogFragment.STYLE_NO_FRAME;
                break;
            case 8:
                style = android.app.DialogFragment.STYLE_NORMAL;
                break;
        }
        switch ((mNum - 1) % 6) {
            case 4:
                theme = android.R.style.Theme_Holo;
                break;
            case 5:
                theme = android.R.style.Theme_Holo_Light_Dialog;
                break;
            case 6:
                theme = android.R.style.Theme_Holo_Light;
                break;
            case 7:
                theme = android.R.style.Theme_Holo_Light_Panel;
                break;
            case 8:
                theme = android.R.style.Theme_Holo_Light;
                break;
        }
        setStyle(android.app.DialogFragment.STYLE_NO_TITLE,0);
    }

    public static LoadingFragment newInstance(String tag){
        LoadingFragment frag = new LoadingFragment();
        Bundle args = new Bundle();
        args.putString("tag", tag);
        frag.setArguments(args);

        return frag;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_custom_progress, container, false);
        mProgressContainer=view.findViewById(R.id.progress_container);
        return view;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
         boolean animate=true;
    /*    if (animate) {
            mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
        } else {
            mProgressContainer.clearAnimation();
        }*/
    }
}

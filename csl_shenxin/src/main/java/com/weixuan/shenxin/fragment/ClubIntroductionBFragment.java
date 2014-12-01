package com.weixuan.shenxin.fragment;

        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import com.weixuan.shenxin.R;

/****
 * 俱乐部简介
 *
 */
public class ClubIntroductionBFragment extends BaseFragment{
    private RadioGroup rgGroup;
    private TextView textView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_club_i_menu2);
    }

    @Override
    public void initView(View view, LayoutInflater inflater) {
        textView=(TextView)view.findViewById(R.id.txt);
    }

}

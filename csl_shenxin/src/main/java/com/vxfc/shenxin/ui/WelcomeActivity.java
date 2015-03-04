/*
 * Created by Storm Zhang, Feb 11, 2014.
 */

package com.vxfc.shenxin.ui;

import android.os.Bundle;

import com.vxfc.shenxin.R;
import com.vxfc.shenxin.presenter.WelcomePresenter;
import com.vxfc.shenxin.view.IWelcome;

public class WelcomeActivity extends BaseActivity implements IWelcome{

    private WelcomePresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        presenter=new WelcomePresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.activityWelcome();
    }

}

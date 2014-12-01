/*
 * Copyright (C) 2013 Evgeny Shishkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weixuan.shenxin.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import com.weixuan.shenxin.R;


public class CustomLayoutProgressFragment extends ProgressFragment {
    private View mContentView;
    private Handler  mHandler = new Handler();
    private Runnable mShowContentRunnable = new Runnable() {

        @Override
        public void run() {
            setContentEmpty(true);
            setContentShown(true);
        }

    };

    public static CustomLayoutProgressFragment newInstance() {
        CustomLayoutProgressFragment fragment = new CustomLayoutProgressFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //mContentView = inflater.inflate(R.layout.view_content, null);
        return inflater.inflate(R.layout.fragment_custom_progress, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Setup content view
        //setContentView(mContentView);
        // Setup text for empty content
        setEmptyText(R.string.empty);
        obtainData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeCallbacks(mShowContentRunnable);
    }

    private void obtainData() {
        // Show indeterminate progress
        setContentShown(false);
        mHandler.postDelayed(mShowContentRunnable, 3000);
    }
}

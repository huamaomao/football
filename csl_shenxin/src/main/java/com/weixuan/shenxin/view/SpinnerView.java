package com.weixuan.shenxin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.weixuan.shenxin.R;

/**
 * @author Hua
 * @date 2014/10/16.
 */
public class SpinnerView extends FrameLayout {
    private final String TAG = "SpinnerView";
    private TextView textView;
    private ImageView imageView;
    private int imgWidth=50;
    private PopMenu  popMenu;

    public SpinnerView(Context context) {
        super(context);
        initView();
    }

    public SpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    public void setItemValue(String value){
        textView.setText(value);
    }

    public String getItemValue(){
       return this.textView.getText().toString();
    }

    public SpinnerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View view = getChildAt(0);
        view.layout(0,0, getMeasuredWidth(), getMeasuredHeight());
        ImageView view1 = (ImageView)getChildAt(1);
       view1.layout(getMeasuredWidth()-imgWidth,getMeasuredHeight()-imgWidth, getMeasuredWidth()-10, getMeasuredHeight()-10);
    }
    private void initView() {
        textView = new TextView(getContext());
        textView.setText("哈尔滨毅腾");
        textView.setTextSize(17f);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.white));
        addView(textView);
        imageView=new ImageView(getContext());
        imageView.setImageResource(R.drawable.select);
        imageView.setLayoutParams(new LayoutParams(imgWidth,imgWidth));
        addView(imageView);

    }

    public void setItems(int resId){
        popMenu=new PopMenu(getContext(),getResources().getStringArray(resId),this);
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popMenu.showAsDropDown(v);
            }
        });
    }
    public void setItems( Object[] items){
        popMenu=new PopMenu(getContext(), items,this);
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popMenu.showAsDropDown(v);
            }
        });
    }

    public void setSelection(int index){
        popMenu.setSelection(index);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        popMenu.setOnItemClickListener(listener);
    }
    public Object getSelectionItem(){
       return popMenu.getSelectionItem();
    }

    public int getSelectionIndex(){
        return popMenu.getSelectionIndex();
    }
    public void dismiss(){
        popMenu.dismiss();
    }


}

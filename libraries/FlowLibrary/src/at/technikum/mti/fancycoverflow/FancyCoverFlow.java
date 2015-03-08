package at.technikum.mti.fancycoverflow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public class FancyCoverFlow extends Gallery {

    public static final int ACTION_DISTANCE_AUTO = Integer.MAX_VALUE;

    /**
     * 图片向上突出，可以通过代码控制，也可以在xml上控制
     */
    public static final float SCALEDOWN_GRAVITY_TOP = 0.0f;
    /**
     * 图片中间突出
     */
    public static final float SCALEDOWN_GRAVITY_CENTER = 0.5f;
    /**
     * 图片向下突出
     */
    public static final float SCALEDOWN_GRAVITY_BOTTOM = 1.0f;

    private Camera transformationCamera;

     /**中间x**/
    private int centerX;

    public FancyCoverFlow(Context context) {
        super(context);
        this.initialize();
    }

    public FancyCoverFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize();
        this.applyXmlAttributes(attrs);
    }

    @SuppressLint("NewApi")
    public FancyCoverFlow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (Build.VERSION.SDK_INT >= 11) {
            this.setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
        this.initialize();
        this.applyXmlAttributes(attrs);
    }

    private void initialize() {
        this.transformationCamera = new Camera();
        this.setStaticTransformationsEnabled(true);
        this.setChildrenDrawingOrderEnabled(true);
    }

    private void applyXmlAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.FancyCoverFlow);
    }


    /****
     *  this.left+width/2
     * @param paramView
     * @return
     */
    private static int viewX(View paramView)
    {
        return paramView.getLeft() + paramView.getWidth() / 2;
    }
    /***
     *中间宽度
     * @return
     */
    private int getCenterOfCoverflow()
    {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    }

    protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        this.centerX = getCenterOfCoverflow();
        super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    private void initImageView(ImageView paramImageView, Transformation paramTransformation, int paramInt)
    {
        this.transformationCamera.save();
        Matrix localMatrix = paramTransformation.getMatrix();
        int i = paramImageView.getLayoutParams().height;
        int j = paramImageView.getLayoutParams().width;
        int k =paramInt/100;
        int l=Math.abs(k);
        float zf=0f;
        zf = (float)(1.0D * l*40);
        this.transformationCamera.translate(0.0F, 150+20.0F*l, zf);
        this.transformationCamera.getMatrix(localMatrix);
        localMatrix.preTranslate(-(j / 2), -(i / 2));
        localMatrix.postTranslate(j / 2, i / 2);
        this.transformationCamera.restore();
    }

    @Override
    protected boolean getChildStaticTransformation(View child, Transformation transformation) {
        int i = viewX(child);
        int j = child.getWidth();
        transformation.clear();
        transformation.setTransformationType(Transformation.TYPE_BOTH);
        int k =((this.centerX - i));
        initImageView((ImageView) child, transformation, k);
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            child.postInvalidate();
        }
        return true;
    }


    @Override
    protected int getChildDrawingOrder(int childCount, int order) {
        // Current selected index.
        setChildrenDrawingOrderEnabled(true);
        int mFirstPosition = getFirstVisiblePosition();
        int mSelectedPosition = computeHorizontalScrollOffset();
        int selectedIndex = mSelectedPosition - mFirstPosition;
        // Just to be safe
        if (order == childCount - 1) {
            // Draw the selected child last
            return selectedIndex;
        } else if (order >= selectedIndex) {
            // Move the children after the selected child from last to the selected
            return (childCount - 1) - (order - selectedIndex);
        } else {
            // Keep the children before the selected child the same
            return order;
        }
    }

    private boolean isTouchAble = true;

    public void disableTouch() {
        isTouchAble = false;
    }

    public void enableTouch() {
        isTouchAble = true;
    }

    public boolean isTouchAble() {
        return isTouchAble;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (int i = 0; i < getChildCount(); i++) {
           getChildAt(i).invalidate();
        }
        if (isTouchAble) {
            return super.onTouchEvent(event);
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (isTouchAble) {
            return super.onInterceptTouchEvent(event);
        } else {
            return true;
        }
    }

    // 使快速滑动失效
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        return false;
    }

}
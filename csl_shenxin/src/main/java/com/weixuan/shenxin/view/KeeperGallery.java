package com.weixuan.shenxin.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;
import java.io.PrintStream;

public class KeeperGallery extends Gallery
{
  private Camera camera = new Camera();
  private int b = 60;
  private int c = -50;
  private int d;

  public KeeperGallery(Context paramContext)
  {
    super(paramContext);
  }

  public KeeperGallery(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public KeeperGallery(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private static int a(View paramView)
  {
    return paramView.getLeft() + paramView.getWidth() / 2;
  }

  private void a(ImageView paramImageView, Transformation paramTransformation, int paramInt)
  {
    this.camera.save();
    Matrix localMatrix = paramTransformation.getMatrix();
    int i = paramImageView.getLayoutParams().height;
    int j = paramImageView.getLayoutParams().width;
    int k = Math.abs(paramInt);
    if (k <= 30)
      this.camera.translate(0.0F, 0.0F, -100.0F);
    float zf=0f;
    if (k < this.b)
    {
      zf = (float)(this.c + 1.0D * k);
      //
      this.camera.translate(0.0F, 0.0F, zf * 3.0F);
    }
    while (true)
    {
      this.camera.getMatrix(localMatrix);
      localMatrix.preTranslate(-(j / 2), -(i / 2));
      localMatrix.postTranslate(j / 2, i / 2);
      this.camera.restore();
      break;
     /* label192: if ((d.a((Activity)getContext()) <= 1.9D) && (d.a((Activity)getContext()) > 1.4D))
      {
        this.camera.translate(0.0F, 0.0F, f);
        continue;
      }*/
     //camera.translate(0.0F, 0.0F,zf);
    }
  }

  private int getCenterOfCoverflow()
  {
    return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
  }

  protected boolean getChildStaticTransformation(View paramView, Transformation paramTransformation)
  {
    int i = a(paramView);
    int j = paramView.getWidth();
    paramTransformation.clear();
    paramTransformation.setTransformationType(Transformation.TYPE_BOTH);
    if (i == this.d)
    {
      a((ImageView)paramView, paramTransformation, 0);
      return true;
    }
    int k = (int)((this.d - i) / j * this.b);
    for (k = -this.b; ; k = this.b)
    {
      a((ImageView)paramView, paramTransformation, k);
      break;
    }
    return true;
  }

  public int getMaxRotationAngle()
  {
    return this.b;
  }

  public int getMaxZoom()
  {
    return this.c;
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.d = getCenterOfCoverflow();
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setMaxRotationAngle(int paramInt)
  {
    this.b = paramInt;
  }

  public void setMaxZoom(int paramInt)
  {
    this.c = paramInt;
  }

  public void setSpacing(int paramInt)
  {
    super.setSpacing(-30);
  }
}
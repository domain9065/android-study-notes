package com.domain.android.study.notes.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.domain.android.study.notes.R;

/**
 * <pre>
 *
 *     author : domain
 *     e-mail : sunlongyue@foxmail.com
 *     time   : 2019/07/12
 *     desc   :画布错切
 *
 *canvas.skew(float sx,float sy),sx和sy分别表示将画布在x和y方向上倾斜相应的角度对应的tan值。
 *当sx=1时，即将画布在x方向上旋转45度，其实就是x轴保持方向不变，y轴逆时针旋转45度。
 *当sy=1时，即将画布在y方向上旋转45度，其实就是y轴保持方向不变，x轴顺时针旋转45度。
 *
 *
 *     version: 1.0
 * </pre>
 */
public class CustomViewSkew extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 宽高
    private int mWidth, mHeight;

    public CustomViewSkew(Context context) {
        super(context);
    }

    public CustomViewSkew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewSkew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         mWidth = MeasureSpec.getSize(widthMeasureSpec);
         mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_liuyifei);

        // 将画布坐标原点移动到中心位置
        canvas.translate(mWidth / 2, mHeight / 2);
        //把图片画在画布的中间， 现在原点是在屏幕中心， 但是bitmap的x,y两个参数是指图片的左上角的位置，所以这里需要计算一下左上角的位置， 把左上角往上面， 左边移动图片的一半，图片就居中了
        canvas.drawBitmap(bitmap,-(bitmap.getWidth()/2),-(bitmap.getHeight()),paint);


        canvas.save();
        //y轴逆时针旋转45°
        canvas.skew(1,0);
        canvas.drawBitmap(bitmap,-(bitmap.getWidth()/2),-(bitmap.getHeight()),paint);
        //恢复画布状态
        canvas.restore();

    }
}

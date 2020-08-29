package com.example.imgeditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class EditView extends View {

    Paint brush;
    private Canvas mExtraCanvas;
    private Bitmap mExtraBitmap;
    private Path mPath;
    private static final float TOUCH_TOLERANCE = 4;
    private float mX, mY;


    public EditView(Context context) {
        super(context);
        init();

    }

    public EditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

   public void init(){

       mPath = new Path();

   brush=new Paint(Paint.ANTI_ALIAS_FLAG);
   brush.setStyle(Paint.Style.STROKE);
   brush.setColor(Color.MAGENTA);
   brush.setStrokeWidth(10);
   brush.setDither(true);
   brush.setStrokeJoin(Paint.Join.ROUND);
   brush.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mExtraBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mExtraCanvas = new Canvas(mExtraBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mExtraBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();


        utility(event, x, y);

        return true;

    }

    public void utility(MotionEvent event, float x, float y) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);

                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();

                break;
            default:
        }
    }

    private void touchStart(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {

            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);

            mX = x;
            mY = y;

            mExtraCanvas.drawPath(mPath,brush);
        }
    }

    private void touchUp() {

        mPath.reset();
    }
}

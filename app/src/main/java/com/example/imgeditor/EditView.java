package com.example.imgeditor;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class EditView extends View {

    Paint brush;
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

   brush=new Paint(Paint.ANTI_ALIAS_FLAG);
   brush.setStyle(Paint.Style.STROKE);
   brush.setColor(Color.MAGENTA);
   brush.setStrokeWidth(10);

    }


}

package com.lrpshuai.testdemo.lrpshuaitest.weight.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lrpshuai.testdemo.lrpshuaitest.R;

/**
 * 水印图片
 * Created by LRP1989 on 2017/5/11.
 */
public class WatermarkView extends ImageView {

    private String secondTxt;

    public WatermarkView(Context context) {
        this(context, null);
    }

    public WatermarkView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WatermarkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs, defStyleAttr);
        initView();
    }

    private void initAttrs(AttributeSet attrs, int defStyleAttr) {
        if (attrs == null) {
            return;
        }
        TintTypedArray array = TintTypedArray.obtainStyledAttributes(getContext(), attrs, R.styleable.Watermark, defStyleAttr, 0);
        secondTxt = array.getString(R.styleable.Watermark_second_txt);
    }

    private void initView() {
        String firstTxt = getResources().getString(R.string.app_name);

        Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(getResources().getColor(R.color.color_eeeeee));

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(80);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(getResources().getDimension(R.dimen.txt_size_28));

        //文字倾斜路径
        Path path = new Path();
        path.moveTo(0, 200);
        path.lineTo(400, 0);
        //绘制应用名称
        canvas.drawTextOnPath(firstTxt, path, 0, 30, paint);

        //第二行文字倾斜
        Path pathName = new Path();
        pathName.moveTo(0, 400);
        pathName.lineTo(400, 200);

        canvas.drawTextOnPath(secondTxt, pathName, 0, 0, paint);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        bitmapDrawable.setDither(true);
        this.setBackgroundDrawable(bitmapDrawable);
    }
}

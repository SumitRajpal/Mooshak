package com.ramster.sumit.mooshak;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class Overlay extends AppCompatActivity implements View.OnClickListener {
ImageView img; Bitmap bmp; Button b; EditText editText; String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay);


        b=(Button)findViewById(R.id.button5);

        editText=(EditText)findViewById(R.id.str);

        s=editText.getText().toString();

        b.setOnClickListener(this);

        img=(ImageView)findViewById(R.id.imageView2);

    }
    public Bitmap drawTextToBitmap(Context mContext, int resourceId, String mText) {
        try {
            Resources resources = mContext.getResources();
            float scale = resources.getDisplayMetrics().density;
            Bitmap bitmap = BitmapFactory.decodeResource(resources, resourceId);

            android.graphics.Bitmap.Config bitmapConfig =   bitmap.getConfig();

            if(bitmapConfig == null) {
                bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
            }

            bitmap = bitmap.copy(bitmapConfig, true);

            Canvas canvas = new Canvas(bitmap);

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            paint.setColor(Color.rgb(255,255, 255));

            paint.setTextSize((int) (122 * scale));

            paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);


            Rect bounds = new Rect();

            paint.getTextBounds(mText, 0, mText.length(), bounds);

            int x = (bitmap.getWidth() - bounds.width())/6;
            int y = (bitmap.getHeight() + bounds.height())/5;

            canvas.drawText(mText, x * scale, y * scale, paint);

            return bitmap;

        } catch (Exception e) {




            return null;
        }

    }


    @Override
    public void onClick(View view) {

        String str=editText.getText().toString();

        bmp =drawTextToBitmap(getApplicationContext(),R.drawable.battlefield1,str);

        img.setImageBitmap(bmp);

        //saving bitmap
        saveImage(bmp);
    }

    private void saveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".png";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

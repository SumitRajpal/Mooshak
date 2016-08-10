package com.ramster.sumit.mooshak;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Share extends AppCompatActivity {
ImageView iv4;Button a,b,c;String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        iv4=(ImageView)findViewById(R.id.imageView4);
        a=(Button)findViewById(R.id.button2);
        b=(Button)findViewById(R.id.button3);
        c=(Button)findViewById(R.id.button4);
        s="http://im.firstpost.com/fpimages/380x285/fixed/jpg/2016/08/rupani-swear-in-ANI-take-this.jpg-small_cr.jpg";
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareImageWhatsApp(s);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareFacebook(s);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extra(s);
            }
        });

        Picasso.with(getApplicationContext())
                .load("http://im.firstpost.com/fpimages/380x285/fixed/jpg/2016/08/rupani-swear-in-ANI-take-this.jpg-small_cr.jpg")
                .error(android.R.drawable.stat_notify_error)
                .into(iv4);

    }
    public void shareImageWhatsApp(String s) {

       Bitmap adv = BitmapFactory.decodeResource(getResources(), R.drawable.rup);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        adv.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + "temporary_file.jpg");
        try {
            f.createNewFile();
            new FileOutputStream(f).write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        share.putExtra(Intent.EXTRA_STREAM,
                Uri.parse( Environment.getExternalStorageDirectory()+ File.separator+"temporary_file.jpg"));
        share.putExtra(Intent.EXTRA_TEXT, s);
        if(isPackageInstalled("com.whatsapp",this)){
            share.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(share, "Share Image"));

        }else{

            Toast.makeText(getApplicationContext(), "Please Install Whatsapp", Toast.LENGTH_LONG).show();
        }

       /* Uri uri = Uri.parse("http://im.firstpost.com/fpimages/380x285/fixed/jpg/2016/08/rupani-swear-in-ANI-take-this.jpg-small_cr.jpg");



        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "lele");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.setType("image/*");
        intent.setPackage("com.whatsapp");
        startActivity(intent);*/

    }

    public void shareFacebook(String s){

        String urlToShare = s;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, urlToShare);


        boolean facebookAppFound = false;
        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                intent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }


        if (!facebookAppFound) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }

        startActivity(intent);
    }

    public void extra(String s){

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
        startActivity(Intent.createChooser(sharingIntent,"Share using"));
    }

    private boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}

package com.ramster.sumit.mooshak;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {
    ImageView ivImageUrl;TelephonyManager tm ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ivImageUrl=(ImageView)findViewById(R.id.imageView);

        }


    public void Clicked(View view)
    {

        tm= (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mMobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

//WIFI CONNECTION

        if (mWifi.isConnected() == false && mMobile.isConnected() == false) {

            Toast.makeText(getApplicationContext(),"No connection",Toast.LENGTH_SHORT).show();
        }

        else if(mWifi.isConnected() == true){

            Toast.makeText(getApplicationContext(),"WIFI connection",Toast.LENGTH_SHORT).show();
            Picasso.with(getApplicationContext())
                    .load("https://i.ytimg.com/vi/U-HFHvI3lKQ/maxresdefault.jpg")
                    .error(android.R.drawable.stat_notify_error)
                    .into(ivImageUrl);
//2G CONNECTION
        }
        else if(mMobile.isConnected()==true && tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_EDGE ){

            Toast.makeText(getApplicationContext(),"2G connection",Toast.LENGTH_SHORT).show();
            Picasso.with(getApplicationContext())
                    .load("https://i.ytimg.com/vi/U-HFHvI3lKQ/maxresdefault.jpg")
                    .error(android.R.drawable.stat_notify_error).resize(256,144).onlyScaleDown()
                    .into(ivImageUrl);
        }
//3G CONNECTION
        else if(mMobile.isConnected()==true && tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSPA  ){

            Toast.makeText(getApplicationContext(),"3G connection",Toast.LENGTH_SHORT).show();
            Picasso.with(getApplicationContext())
                    .load("https://i.ytimg.com/vi/U-HFHvI3lKQ/maxresdefault.jpg")
                    .error(android.R.drawable.stat_notify_error).resize(512,288).onlyScaleDown()
                    .into(ivImageUrl);
        }
//4G CONNECTION
        else if(mMobile.isConnected()==true && tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_LTE ){

            Toast.makeText(getApplicationContext(),"4G connection",Toast.LENGTH_SHORT).show();
            Picasso.with(getApplicationContext())
                    .load("https://i.ytimg.com/vi/U-HFHvI3lKQ/maxresdefault.jpg")
                    .error(android.R.drawable.stat_notify_error).resize(768,432).onlyScaleDown()
                    .into(ivImageUrl);
        }

    }

}

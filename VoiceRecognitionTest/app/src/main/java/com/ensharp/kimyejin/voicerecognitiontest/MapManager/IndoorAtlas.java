package com.ensharp.kimyejin.voicerecognitiontest.MapManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.ensharp.kimyejin.voicerecognitiontest.MainActivity;
import com.ensharp.kimyejin.voicerecognitiontest.R;
import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;

public class IndoorAtlas{

    private MainActivity main;
    IALocationManager locationManager;

    public IndoorAtlas(MainActivity main) {
        this.main = main;
    }

    IALocationListener locationListener = new IALocationListener() {
        @Override
        public void onLocationChanged(IALocation iaLocation) {
            Log.e(main.getClass().getName(),String.valueOf("---------------------------------♡♡"+iaLocation.getLatitude() +","+iaLocation.getLongitude()));
            ((TextView)main.findViewById(R.id.indoorText)).setText(String.valueOf(iaLocation.getLatitude() +","+iaLocation.getLongitude()+" , "+iaLocation.getBearing()));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }
    };

    public IALocationManager getLocationManager() {return IALocationManager.create(main);}

    public IALocationListener getLocationListener() {return locationListener;}
}

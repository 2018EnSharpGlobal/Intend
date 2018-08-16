package com.ensharp.kimyejin.voicerecognitiontest.MapManager;

import android.widget.TextView;

import com.ensharp.kimyejin.voicerecognitiontest.R;

public class IndoorAtlas {
//    private final int CODE_PERMISSIONS = 0;//...
//
//    private TextView mStatus;
//    IALocationManager mLocationManager;
//
//    IALocationListener mLocationListener = new IALocationListener() {
//        @Override
//        public void onLocationChanged(IALocation iaLocation) {
//            TextView txtLoc = (TextView)findViewById(R.id.textView);
//            Log.e(this.getClass().getName(),String.valueOf("---------------------------------"+iaLocation.getLatitude() +","+iaLocation.getLongitude()));
//            txtLoc.setText(String.valueOf(iaLocation.getLatitude() +","+iaLocation.getLongitude()+" , "+iaLocation.getBearing()));
//        }
//
//        @Override
//        public void onStatusChanged(String s, int i, Bundle bundle) {
//
//        }
//    };
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        String[] neededPermissions = {
//                Manifest.permission.CHANGE_WIFI_STATE,
//                Manifest.permission.ACCESS_WIFI_STATE,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//        };
//
//        ActivityCompat.requestPermissions( this, neededPermissions, CODE_PERMISSIONS );
//
//        mStatus = (TextView) findViewById(R.id.textView);
//
//        mLocationManager = IALocationManager.create(this);
//        SpeechRecognizerManager.getInstance().initializeLibrary(this);
//        TextToSpeechManager.getInstance().initializeLibrary(getApplicationContext());
//        findViewById(R.id.button2).setOnClickListener(this);
//
//        //Log.e(this.getClass().getName(),"�쁿�쁾�쁿�쁾"+getKeyHash(this));
//    }
//    @Override
//    protected void onResume(){
//        super.onResume();
//        mLocationManager.requestLocationUpdates(IALocationRequest.create(),mLocationListener);
//    }
//
//    @Override
//    protected void onPause(){
//        super.onPause();
//        mLocationManager.removeLocationUpdates(mLocationListener);
//    }
//
//    @Override
//    protected void onDestroy(){
//        super.onDestroy();
//        mLocationManager.destroy();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        //Handle if any of the permissions are denied, in grantResults
//    }
}

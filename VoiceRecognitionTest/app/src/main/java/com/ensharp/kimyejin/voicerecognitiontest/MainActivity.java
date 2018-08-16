package com.ensharp.kimyejin.voicerecognitiontest;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SoundManager soundManager;
    private Analyzer analyzer;
    private String address = "3cfc9e37";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundManager = new SoundManager(this);
        analyzer = new Analyzer(this, address);

        findViewById(R.id.inputButton).setOnClickListener(this);
        findViewById(R.id.outputButton).setOnClickListener(this);
        findViewById(R.id.lineButton).setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 0:
                if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.inputButton:
                soundManager.onInputButtonClick();
                analyzer.clearAllInformation();
                break;
            case R.id.outputButton:
                soundManager.onOutputButtonClick();
                break;
            case R.id.lineButton:
                analyzer.analyzeLine();
                ((EditText)findViewById(R.id.detailInformation)).setText(analyzer.getResult());
                break;
        }
    }
}

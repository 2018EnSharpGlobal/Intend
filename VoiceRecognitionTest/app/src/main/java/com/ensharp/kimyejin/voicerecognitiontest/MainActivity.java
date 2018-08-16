package com.ensharp.kimyejin.voicerecognitiontest;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.TextView;

import com.kakao.sdk.newtoneapi.SpeechRecognizeListener;
import com.kakao.sdk.newtoneapi.SpeechRecognizerClient;
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager;
import com.kakao.sdk.newtoneapi.TextToSpeechClient;
import com.kakao.sdk.newtoneapi.TextToSpeechListener;
import com.kakao.sdk.newtoneapi.TextToSpeechManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // for kakao
//    private SpeechRecognizerClient client;
//    private TextToSpeechClient ttsClient;
//    private boolean isSpeechMode;
//    private String serviceType = SpeechRecognizerClient.SERVICE_TYPE_DICTATION;
//    private String speechMode = TextToSpeechClient.NEWTONE_TALK_2;
//    private String voiceType = TextToSpeechClient.VOICE_MAN_DIALOG_BRIGHT;
//    private double speechSpeed = 0.5D;

    private SoundManager soundManager;
    private Analyzer analyzer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        isSpeechMode = false;

        SpeechRecognizerManager.getInstance().initializeLibrary(this);
        TextToSpeechManager.getInstance().initializeLibrary(getApplicationContext());

        findViewById(R.id.inputButton).setOnClickListener(this);
        findViewById(R.id.outputButton).setOnClickListener(this);
        findViewById(R.id.lineButton).setOnClickListener(this);

        soundManager = new SoundManager(this);
        analyzer = new Analyzer(this);
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
                break;
            case R.id.outputButton:
                soundManager.onOutputButtonClick();
                break;
            case R.id.lineButton:
                onLineButtonClick();
                break;
        }
    }

    private void onLineButtonClick() {
        String text = ((TextView)findViewById(R.id.inputText)).getText().toString();
        analyzer.analyzeLine(text);
    }

//    public void onInputButtonClick() {
//        Log.e("SPEECH_MODE", "speechStart");
//        isSpeechMode = true;
//        SpeechRecognizerClient.Builder builder = new SpeechRecognizerClient.Builder().setServiceType(serviceType);
//        client = builder.build();
//        client.setSpeechRecognizeListener(this);
//        client.startRecording(true);
//    }

//    public void onOutputButtonClick() {
//        if (ttsClient != null && ttsClient.isPlaying()) {
//            ttsClient.stop();
//            return;
//        }
//        String output = ((TextView)findViewById(R.id.inputText)).getText().toString();
//
//        ttsClient = new TextToSpeechClient.Builder()
//                .setSpeechMode(speechMode)
//                .setSpeechSpeed(speechSpeed)
//                .setSpeechVoice(voiceType)
//                .setListener(MainActivity.this)
//                .build();
//
//        if (ttsClient.play(output))
//            Log.e("LISTEN_MODE", "읽을 메시지:"+output);
//    }

//    @Override
//    public void onReady() {
//
//    }
//
//    @Override
//    public void onBeginningOfSpeech() {
//
//    }
//
//    @Override
//    public void onEndOfSpeech() {
//
//    }
//
//    @Override
//    public void onError(int errorCode, String errorMsg) {
//        if (isSpeechMode) {
//            Log.e("SPEECH_MODE", "ERROR:" + errorMsg);
//            client = null;
//            isSpeechMode = false;
//        }
//        else {
//            handleError(errorCode);
//            ttsClient = null;
//        }
//    }
//
//    @Override
//    public void onPartialResult(String partialResult) {
//
//    }
//
//    @Override
//    public void onResults(Bundle results) {
//        // result of user input
//        String result = results.getStringArrayList(SpeechRecognizerClient.KEY_RECOGNITION_RESULTS).get(0);
//        ((TextView)findViewById(R.id.inputText)).setText(result);
//        Log.e("SPEECH_MODE", "OnResults");
//
//        client = null;
//    }
//
//    @Override
//    public void onAudioLevel(float audioLevel) {
//
//    }
//
//    @Override
//    public void onFinished() {
//        if (isSpeechMode) {
//            Log.e("SPEECH_MODE", "onFinished");
//            isSpeechMode = false;
//        }
//        else {
//            int intSentSize = ttsClient.getSentDataSize();      //세션 중에 전송한 데이터 사이즈
//            int intRecvSize = ttsClient.getReceivedDataSize();  //세션 중에 전송받은 데이터 사이즈
//
//            final String strInacctiveText = "handleFinished() SentSize : " + intSentSize + "  RecvSize : " + intRecvSize;
//
//            Log.e("LISTEN_MODE", strInacctiveText);
//            Log.e("LISTEN_MODE", "listenOnFinished");
//            ttsClient = null;
//        }
//    }
//
//    private void handleError(int errorCode) {
//        String errorText;
//        switch (errorCode) {
//            case TextToSpeechClient.ERROR_NETWORK:
//                errorText = "네트워크 오류";
//                break;
//            case TextToSpeechClient.ERROR_NETWORK_TIMEOUT:
//                errorText = "네트워크 지연";
//                break;
//            case TextToSpeechClient.ERROR_CLIENT_INETRNAL:
//                errorText = "음성합성 클라이언트 내부 오류";
//                break;
//            case TextToSpeechClient.ERROR_SERVER_INTERNAL:
//                errorText = "음성합성 서버 내부 오류";
//                break;
//            case TextToSpeechClient.ERROR_SERVER_TIMEOUT:
//                errorText = "음성합성 서버 최대 접속시간 초과";
//                break;
//            case TextToSpeechClient.ERROR_SERVER_AUTHENTICATION:
//                errorText = "음성합성 인증 실패";
//                break;
//            case TextToSpeechClient.ERROR_SERVER_SPEECH_TEXT_BAD:
//                errorText = "음성합성 텍스트 오류";
//                break;
//            case TextToSpeechClient.ERROR_SERVER_SPEECH_TEXT_EXCESS:
//                errorText = "음성합성 텍스트 허용 길이 초과";
//                break;
//            case TextToSpeechClient.ERROR_SERVER_UNSUPPORTED_SERVICE:
//                errorText = "음성합성 서비스 모드 오류";
//                break;
//            case TextToSpeechClient.ERROR_SERVER_ALLOWED_REQUESTS_EXCESS:
//                errorText = "허용 횟수 초과";
//                break;
//            default:
//                errorText = "정의하지 않은 오류";
//                break;
//        }
//
//        final String statusMessage = errorText + " (" + errorCode + ")";
//
//        Log.e("LISTEN_MODE", "ERROR:" + statusMessage);
//    }
}

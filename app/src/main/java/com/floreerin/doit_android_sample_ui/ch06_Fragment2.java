package com.floreerin.doit_android_sample_ui;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ch06_Fragment2 extends Fragment {
    EditText FromAddressText;
    EditText MessageContentText;
    EditText DateText;
    Context brcontext;
    BroadcastReceiver br;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if(grantResults.length > 0){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getContext(),"SMS 수신권한을 사용자가 승인함.",Toast.LENGTH_LONG).show();

                }else if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                    Toast.makeText(getContext(),"SMS 수신권한을 사용자가 거부함",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getContext(),"SMS 수신 권한을 부여 받지 못함",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.ch06_fragment2,container,false);

        // 브로드캐스트 리시버 기능 시작
        FromAddressText = rootView.findViewById(R.id.fromAddress);
        MessageContentText = rootView.findViewById(R.id.messageContent);
        DateText = rootView.findViewById(R.id.receiveDate);

        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECEIVE_SMS);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getContext(), "SMS 수신권한 주어져있음",Toast.LENGTH_LONG).show();
         } else{
            Toast.makeText(getContext(), "SMS 수신권한 없음",Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.RECEIVE_SMS)){
                Toast.makeText(getContext(), "SMS 권한이 필요합니다.",Toast.LENGTH_LONG).show();
            } else{
                ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.RECEIVE_SMS},1);
            }
        }

        messageSetup(); // 메세지를 받는 브로드캐스트 리시버 기능 실행

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        brcontext = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        brcontext.unregisterReceiver(br); // 프래그먼트 기능이 중단되면 해당 브로드캐스트 리시버의 기능도 중단
    }

    private void messageSetup() {
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 메세지를 받았을 경우 onReceive 실행
                if (intent.getAction().equals(SMS_RECEIVED)){ // 메세지를 받았을 경우

                    //Bundle 객체를 이용해 메세지 내용을 가져옴
                    Bundle bundle = intent.getExtras();
                    SmsMessage[] messages = parseSmsMessage(bundle);

                    // 메세지 내용을 String 변수 값에 각각 넣기
                    if (messages != null){
                        String FromAddress = messages[0].getOriginatingAddress();
                        String MessageContent = messages[0].getMessageBody();
                        Date receiveDate = new Date(messages[0].getTimestampMillis());

                        // 각 EditText 객체에 문자를 받은 내용 표시
                        FromAddressText.setText(FromAddress);
                        MessageContentText.setText(MessageContent);
                        DateText.setText(format.format(receiveDate));
                    }
                }
            }
        };
        // IntentFilter 를 Fragment 에서 androidManifest.xml 에 직접 할 수 없기 때문에 receive 를 암시적으로 표현
        brcontext.registerReceiver(br, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for (int i = 0; i < smsCount; i++){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else{
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}

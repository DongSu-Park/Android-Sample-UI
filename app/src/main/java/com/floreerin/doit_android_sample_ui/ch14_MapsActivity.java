package com.floreerin.doit_android_sample_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class ch14_MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Geocoder geocoder;
    private EditText edit_address;
    private Button btn_search;
    private long backkeyClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch14_activity_maps);

        edit_address = findViewById(R.id.edit_address);
        btn_search = findViewById(R.id.btn_search);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(this);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edit_address.getText().toString(); // 입력한 주소를 String으로 반환
                List<Address> addressList = null;
                try{ // 지오코딩 설정
                    addressList = geocoder.getFromLocationName(str, 10);
                } catch (IOException e){
                    e.printStackTrace();
                }

                try {
                    System.out.println(addressList.get(0).toString()); // 지오코딩에서 얻은 주소 내용 확인

                    // ","를 기준으로 자르기
                    String splitStr[] = addressList.get(0).toString().split(",");

                    // 주소 내용 (지오코딩 내용)
                    String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2);
                    System.out.println(address);

                    // 위도 (지오코딩 내용)
                    String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1);

                    // 경도 (지오코딩 내용)
                    String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1);

                    // 위도 경도 확인 및 생성
                    System.out.println(latitude);
                    System.out.println(longitude);

                    LatLng latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

                    MarkerOptions markerOptions2 = new MarkerOptions();
                    markerOptions2.title(str);
                    markerOptions2.snippet(address);
                    markerOptions2.position(latLng);

                    // 지오코딩으로 검색된 것 마커 추가
                    mMap.addMarker(markerOptions2);

                    // 해당 좌표로 화면으로 보임
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),"정확한 주소를 입력하세요", Toast.LENGTH_LONG).show();
                }
            }
        });

        // 디폴트 지도 보여줌
        MarkerOptions markerOptionsDefault = new MarkerOptions();
        LatLng seoul = new LatLng(37.573,126.97); // 서울시 중앙에 위치한 것
        markerOptionsDefault.title("Welcome To The Seoul");
        markerOptionsDefault.position(seoul);

        mMap.addMarker(markerOptionsDefault);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul,15));
    }

    @Override
    public void onBackPressed() {
        if( System.currentTimeMillis() > backkeyClickTime + 2000){
            backkeyClickTime = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 가기 버튼을 누르면 메인화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        }
        else if(System.currentTimeMillis() <= backkeyClickTime + 2000){
            finish();
        }
    }
}



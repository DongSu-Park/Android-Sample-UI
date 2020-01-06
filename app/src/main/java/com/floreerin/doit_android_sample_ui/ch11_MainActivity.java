package com.floreerin.doit_android_sample_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ch11_MainActivity extends AppCompatActivity {
    private long backkeyClickTime = 0;

    EditText db_movie_id, db_movie_title, db_movie_date, db_movie_time, db_movie_rate;
    Button db_select, db_insert, db_update, db_delete;
    TextView db_show;

    // 각 속성들의 이름 설정
    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_TITLE = "movie_title";
    public static final String MOVIE_DATE = "movie_date";
    public static final String MOVIE_TIME = "movie_time";
    public static final String MOVIE_RATE = "movie_rate";

    private static final String DATABASE_NAME = "movie.db"; // 생성해주는 db 파일
    private static final int DATABASE_VERSION = 1; // db 버전 (기본값인 1로 설정)

    ch11_DatabaseHelper dbHelper; // SQLite를 사용하는 Helper 클래스
    SQLiteDatabase database; // 쿼리문을 실행시키는 SQLite 클래스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch11_activity_main);

        // 사용자가 입력한 EditText 내용
        db_movie_id = findViewById(R.id.db_movie_id);
        db_movie_title = findViewById(R.id.db_movie_title);
        db_movie_date = findViewById(R.id.db_movie_date);
        db_movie_time = findViewById(R.id.db_movie_time);
        db_movie_rate = findViewById(R.id.db_movie_rate);

        // 각각 버튼
        db_select = findViewById(R.id.db_select);
        db_insert = findViewById(R.id.db_insert);
        db_update = findViewById(R.id.db_update);
        db_delete = findViewById(R.id.db_delete);

        db_show = findViewById(R.id.db_show); // db 결과 내용을 보여주는 textview

        createDatabase(); // 데이터베이스 파일을 생성함
        createTable(); // 데이터베이스 테이블을 생성함

        // 각 버튼들 클릭 시 실행되는 함수들
        db_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeSelect();
            }
        });

        db_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeInsert();
            }
        });

        db_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeUpdate();
            }
        });

        db_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executedelete();
            }
        });

    }

    private void createDatabase(){ // 데이터베이스를 생성
        dbHelper = new ch11_DatabaseHelper(this, DATABASE_NAME, null, DATABASE_VERSION);
        database = dbHelper.getWritableDatabase();

        Toast.makeText(this, "데이터베이스 생성이 완료되었습니다.", Toast.LENGTH_LONG).show();
    }

    private void createTable() { // 테이블을 생성함
        if (database == null) {
            Toast.makeText(this, "데이터베이스 생성이 안됬거나 알수없는 오류가 발생하였습니다.", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            database.execSQL("create table if not exists movie" + "(" +
                    MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MOVIE_TITLE + " TEXT, " +
                    MOVIE_DATE + " TEXT, " +
                    MOVIE_TIME + " INTEGER, " +
                    MOVIE_RATE + " NUMERIC " +
                    ")");
            Toast.makeText(this, "테이블 생성이 완료되었습니다.", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            println("테이블 생성 SQL 오류가 발생하였습니다.");
        }
    }

    /* 각 DDL 버튼 실행 코드 */

    private void executeSelect() { // 데이터베이스 조회

        if(db_show.getText() != null){ // 조회시 보여주는 화면에서 이미 글자가 보여진 경우
            db_show.setText(null); // null로 초기화해 없애준다.
        }
        database = dbHelper.getReadableDatabase(); // 데이터베이스를 초기화
        String sql = "SELECT * FROM movie ";
        Cursor cursor = database.rawQuery(sql, null);
        int recordCount = cursor.getCount();

        for(int i = 0; i < recordCount; i++){
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String date = cursor.getString(2);
            int time = cursor.getInt(3);
            float rate = cursor.getFloat(4);

            println("레코드 #" + i + " : " + id + ", " + title + ", " + date + ", " + time + ", " + rate);
        }
        cursor.close();

    }

    private void executedelete() { // 데이터베이스 삭제
        int movie_id = Integer.parseInt(db_movie_id.getText().toString());

        try {
            database.execSQL("delete from movie where movie_id = " + movie_id);
        } catch (SQLException e){
            Toast.makeText(this, "삭제 오류가 발생하였습니다", Toast.LENGTH_LONG).toString();
        }
    }

    private void executeUpdate() { // 데이터베이스 수정
        int movie_id = Integer.parseInt(db_movie_id.getText().toString());
        String movie_title = db_movie_title.getText().toString();
        String movie_date = db_movie_date.getText().toString();
        int movie_time = Integer.parseInt(db_movie_time.getText().toString());
        float movie_rate = Float.parseFloat(db_movie_rate.getText().toString());

        try {
            database.execSQL("update movie set movie_title = " + "'" + movie_title + "'" + ","
                    + "movie_date = " + "'" + movie_date + "'" + ","
                    + "movie_time = " + movie_time + ","
                    + "movie_rate = " + movie_rate +
                    " WHERE movie_id = " + movie_id);
        }catch (SQLException e){
            Toast.makeText(this, "수정 오류가 발생하였습니다", Toast.LENGTH_LONG).toString();
        }
    }

    private void executeInsert() { // 데이터베이스 삽입
        int movie_id = Integer.parseInt(db_movie_id.getText().toString());
        String movie_title = db_movie_title.getText().toString();
        String movie_date = db_movie_date.getText().toString();
        int movie_time = Integer.parseInt(db_movie_time.getText().toString());
        float movie_rate = Float.parseFloat(db_movie_rate.getText().toString());
        try {
            database.execSQL("insert into movie values " + "("
                    + movie_id + "," +
                    "'" + movie_title + "'" + "," +
                    "'" + movie_date + "'"+ ","
                    + movie_time + ","
                    + movie_rate + ")");

        } catch (SQLException e){
            Toast.makeText(this, "추가 오류가 발생하였습니다", Toast.LENGTH_LONG).toString();
        }
    }

    public void println(String data) { // 결과창에 보여주는 함수
        db_show.append(data + "\n");
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

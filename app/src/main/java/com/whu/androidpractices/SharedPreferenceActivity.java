package com.whu.androidpractices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SharedPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        //当Activity需要多个shard preference文件时
        SharedPreferences sharedPref = this.getSharedPreferences("com.whu.androidpractices.test", Context.MODE_PRIVATE);

        TextView txtHighScore = findViewById(R.id.txt_high_score);

        findViewById(R.id.btn_chg_sp).setOnClickListener(view -> {
            //当Activity只需要一个shared preference文件时，使用此方法
            SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("high_score", (int) (Math.random() * 100));
            editor.commit();

            int highScore = sharedPreferences.getInt("high_score", 0);
            txtHighScore.setText(highScore + "分");
        });


    }
}

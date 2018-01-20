package com.whu.androidpractices;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        findViewById(R.id.btn_send_data_to_other_apps).setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "The is my text to send.");
//        sendIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "打开..."));
        });

        findViewById(R.id.btn_send_multiple).setOnClickListener(v -> {
            ArrayList<Uri> imageUris = new ArrayList<>();
            imageUris.add(Uri.EMPTY);
            imageUris.add(Uri.EMPTY);

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, "分享图片..."));
        });
    }
}

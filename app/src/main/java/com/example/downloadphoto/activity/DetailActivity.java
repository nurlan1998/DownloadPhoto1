package com.example.downloadphoto.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.downloadphoto.R;
import com.example.downloadphoto.utils.Util;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {

    TextView userTextView;
    TextView typeTextView;
    TextView imageWidthTextView;
    TextView imageHeightTextView;
    TextView downloadsTextView;
    TextView viewsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        userTextView = findViewById(R.id.userTextView);
        typeTextView = findViewById(R.id.typeTextView);
        imageWidthTextView = findViewById(R.id.imageWidthTextView);
        imageHeightTextView = findViewById(R.id.imageHeightTextView);
        downloadsTextView = findViewById(R.id.downloadsTextView);
        viewsTextView = findViewById(R.id.viewsTextView);

        Intent intent = getIntent();
        String user = intent.getStringExtra(Util.KEY_USER);
        String type = intent.getStringExtra(Util.KEY_TYPE);
        String imageWidth = intent.getStringExtra(Util.KEY_IMAGE_WIDTH);
        String imageHeight = intent.getStringExtra(Util.KEY_IMAGE_HEIGHT);
        String downloads = intent.getStringExtra(Util.KEY_DOWNLOADS);
        String views = intent.getStringExtra(Util.KEY_VIEWS);


        userTextView.setText(user);
        typeTextView.setText(type);
        imageWidthTextView.setText(imageWidth);
        imageHeightTextView.setText(imageHeight);
        downloadsTextView.setText(downloads);
        viewsTextView.setText(views);
    }
}

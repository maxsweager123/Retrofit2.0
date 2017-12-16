package com.paung.popularmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    ImageView imgPoster;
    TextView txtTitle;
    TextView txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        imgPoster = findViewById(R.id.imgPoster);
        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        Glide.with(this).load(getIntent().getStringExtra("gambar")).into(imgPoster);
        txtTitle.setText(getIntent().getStringExtra("title"));
        txtDescription.setText(getIntent().getStringExtra("deskripsi"));
    }
}

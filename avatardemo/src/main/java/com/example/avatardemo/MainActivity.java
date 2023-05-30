package com.example.avatardemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.avatardemo.widget.AvatarView;
import com.example.avatardemo.widget.MixtureAvatarView;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private MixtureAvatarView mMixtureAvatarView;


    private AppCompatImageView iv_00, iv_01, iv_02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMixtureAvatarView = findViewById(R.id.mMixtureAvatarView);

        iv_00 = findViewById(R.id.iv_00);
        iv_01 = findViewById(R.id.iv_01);
        iv_02 = findViewById(R.id.iv_02);

        mMixtureAvatarView.setBackgroundColor(Color.parseColor("#f34e32"));
        mMixtureAvatarView.getIvBackground().setImageResource(R.drawable._01);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_test);
        mMixtureAvatarView.postDelayed(() -> mMixtureAvatarView.getIvAvatar().setBitmap(bitmap), 50);
    }

    public void onClickShape(View view) {
        switch (view.getId()) {
            case R.id.tv_shape_01:
                mMixtureAvatarView.getIvAvatar().setShape(AvatarView.Shape.CIRCLE);
                break;
            case R.id.tv_shape_02:
                mMixtureAvatarView.getIvAvatar().setShape(AvatarView.Shape.RECTANGLE);
                break;
            case R.id.tv_shape_03:
                mMixtureAvatarView.getIvAvatar().setShape(AvatarView.Shape.TRIANGLE);
                break;
        }
    }

    public void onClickBgRes(View view) {
        switch (view.getId()) {
            case R.id.iv_00:
                mMixtureAvatarView.getIvBackground().setImageResource(0);
                break;
            case R.id.iv_01:
                mMixtureAvatarView.getIvBackground().setImageResource(R.drawable._01);
                break;
            case R.id.iv_02:
                mMixtureAvatarView.getIvBackground().setImageResource(R.drawable._02);
        }
    }

    public void onClickBgColor(View view) {
        int color = Color.TRANSPARENT;
        switch (view.getId()) {
            case R.id.v_01:
                color = Color.parseColor("#F4F8F0");
                break;
            case R.id.v_02:
                color = Color.parseColor("#F2EDE3");
                break;
            case R.id.v_03:
                color = Color.parseColor("#C9D6D9");
                break;
            case R.id.v_04:
                color = Color.parseColor("#9BACB2");
                break;
            case R.id.v_05:
                color = Color.parseColor("#9AC2BE");
                break;
        }

        mMixtureAvatarView.setBackgroundColor(color);
        iv_00.setBackgroundColor(color);
        iv_01.setBackgroundColor(color);
        iv_02.setBackgroundColor(color);

    }
}
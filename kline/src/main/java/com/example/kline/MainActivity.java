package com.example.kline;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kline.bean.KData;
import com.google.gson.Gson;

/**
 * @author yang
 * @date 2025/3/11
 * @desc
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String dataJson = AssetUtil.readAsset(this, "kdata.json");
        KData kData = new Gson().fromJson(dataJson, KData.class);
        Log.e("MainActivity", "y size:" + kData.y.size());
        Log.e("MainActivity", "liq size:" + kData.liq.size());
        Log.e("MainActivity", "prices size:" + kData.prices.size());
    }
}

package com.example.kline;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kline.bean.KData;
import com.example.kline.core.KLineAdapter;
import com.example.kline.core.HeatMapView;
import com.example.kline.utils.JsonUtil;

/**
 * @author yang
 * @date 2025/3/11
 * @desc
 */
public class MainActivity extends AppCompatActivity {
    private HeatMapView mapView;
    private final KLineAdapter adapter = new KLineAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String dataJson = AssetUtil.readAsset(this, "kdata.json");
        KData kData = JsonUtil.parse(dataJson);

        mapView = findViewById(R.id.mHeatMapView);
        mapView.setAdapter(adapter);

        adapter.setData(kData);
    }
}

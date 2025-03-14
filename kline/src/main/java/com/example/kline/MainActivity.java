package com.example.kline;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kline.bean.KData;
import com.example.kline.core.KLineAdapter;
import com.example.kline.core.KLineView;
import com.example.kline.core.LiqView;
import com.example.kline.utils.JsonUtil;

/**
 * @author yang
 * @date 2025/3/11
 * @desc
 */
public class MainActivity extends AppCompatActivity {
    private KLineView mKLineView;
    private final KLineAdapter adapter = new KLineAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String dataJson = AssetUtil.readAsset(this, "kdata.json");
        KData kData = JsonUtil.parse(dataJson);

        mKLineView = findViewById(R.id.mKLineView);
        mKLineView.setAdapter(adapter);

        adapter.setData(kData);
    }
}

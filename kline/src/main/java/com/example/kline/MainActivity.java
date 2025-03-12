package com.example.kline;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.kline.bean.KData;
import com.example.kline.utils.JsonUtil;
import com.example.kline.view.KLineView;

/**
 * @author yang
 * @date 2025/3/11
 * @desc
 */
public class MainActivity extends AppCompatActivity {
    private KLineView mKLineView;
    private AppCompatImageView ivTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String dataJson = AssetUtil.readAsset(this, "kdata.json");
        KData kData = JsonUtil.parse(dataJson);
        Log.e("MainActivity", "y size:" + kData.y.size());
        Log.e("MainActivity", "prices size:" + kData.prices.size());
        Log.e("MainActivity", "liq size:" + kData.liq.size());
        ivTest = findViewById(R.id.iv_test);


        mKLineView = findViewById(R.id.mKLineView);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mKLineView.getLayoutParams();
        params.dimensionRatio = kData.prices.size() + ":" + kData.y.size();
        Log.e("MainActivity", params.dimensionRatio);
        mKLineView.setLayoutParams(params);

        ConstraintLayout.LayoutParams ivParams = (ConstraintLayout.LayoutParams) ivTest.getLayoutParams();
        ivParams.dimensionRatio = kData.prices.size() + ":" + kData.y.size();
        ivTest.setLayoutParams(ivParams);
        ivTest.setImageBitmap(createTest(kData));
    }

    private Bitmap createTest(KData data) {
        int width = data.prices.size();
        int height = data.y.size();
        LiqColorInterpolatorUtils utils = new LiqColorInterpolatorUtils(0, data.maxLiqValue);
        int[] intArr = new int[width * height];
        data.liq.forEach((it) -> {
            int x = it.get(0).intValue();
            int y = it.get(1).intValue();
            double value = it.get(2).doubleValue();
            intArr[x + y * width] = utils.getColor(value);
        });

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(intArr, 0, width, 0, 0, width, height);
        return bitmap;
    }
}

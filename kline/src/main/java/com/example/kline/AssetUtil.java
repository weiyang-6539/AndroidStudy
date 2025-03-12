package com.example.kline;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author yang
 * @date 2025/3/11
 * @desc
 */
public class AssetUtil {

    public static String readAsset(Context context, String filename) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(filename);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            br.close();
        } catch (IOException ignored) {
        }
        return sb.toString();
    }
}

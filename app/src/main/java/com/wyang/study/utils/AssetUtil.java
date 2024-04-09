package com.wyang.study.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wyang.study.TreeNode;
import com.wyang.study.ui.util.TreeHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by weiyang on 2019-09-22.
 */
public class AssetUtil {
    private static String readAsset(Context context, String filename) {
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

    public static TreeHelper buildAddressTree(Context context) throws JSONException {
        Log.e("Idea", Thread.currentThread().getName());
        TreeHelper treeHelper = new TreeHelper();
        String pJson = readAsset(context, "provinces.json");

        //遍历省json
        TreeNode pNode;
        JSONArray pArr = new JSONArray(pJson);
        for (int i = 0; i < pArr.length(); i++) {
            JSONObject pObj = pArr.getJSONObject(i);

            TreeNode parent = treeHelper.rootSeeker().firstResult();
            pNode = new TreeNode(parent);
            pNode.put("code", pObj.getString("code"));
            pNode.put("name", pObj.getString("name"));
            pNode.put("level", 1);
        }

        String cJson = readAsset(context, "cities.json");

        //遍历城市json
        TreeNode cNode;
        JSONArray cArr = new JSONArray(cJson);
        for (int i = 0; i < cArr.length(); i++) {
            JSONObject cObj = cArr.getJSONObject(i);

            String parentCode = cObj.getString("provinceCode");
            TreeNode parent = treeHelper.getProvince(parentCode);
            cNode = new TreeNode(parent);
            cNode.put("code", cObj.getString("code"));
            cNode.put("name", cObj.getString("name"));
            cNode.put("level", 2);
        }

        String aJson = readAsset(context, "area.json");

        //遍历地级市json
        TreeNode aNode;
        JSONArray aArr = new JSONArray(aJson);
        for (int i = 0; i < aArr.length(); i++) {
            JSONObject aObj = aArr.getJSONObject(i);

            String parentCode = aObj.getString("cityCode");
            TreeNode parent = treeHelper.getCity(parentCode);
            aNode = new TreeNode(parent);
            aNode.put("code", aObj.getString("code"));
            aNode.put("name", aObj.getString("name"));
            aNode.put("level", 3);

            treeHelper.putAreaNode(aNode);
        }

        String sJson = readAsset(context, "streets.json");

        //遍历乡镇json
        TreeNode sNode;
        JsonArray sArr = new JsonParser().parse(sJson).getAsJsonArray();
        for (int i = 0; i < sArr.size(); i++) {
            JsonObject sObj = sArr.get(i).getAsJsonObject();

            String parentCode = sObj.get("areaCode").getAsString();
            TreeNode parent = treeHelper.getAreaNode(parentCode);
            sNode = new TreeNode(parent);
            sNode.put("code", sObj.get("code").getAsString());
            sNode.put("name", sObj.get("name").getAsString());
            sNode.put("level", 4);
        }
        return treeHelper;
    }
}

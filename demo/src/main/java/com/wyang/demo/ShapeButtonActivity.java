package com.wyang.demo;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.wyang.common.widget.extend.ShapeButton;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by weiyang on 2019-10-12.
 *
 * @url https://www.jb51.net/article/145309.htm
 */
public class ShapeButtonActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shape_button;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "ShapeButton", true);

        et_nickname.setText("");
    }

    @BindView(R.id.et_nickname)
    EditText et_nickname;
    @BindView(R.id.sb_complete)
    ShapeButton sb_complete;

    @OnTextChanged(R.id.et_nickname)
    public void onNicknameTextChange() {
        if (TextUtils.isEmpty(et_nickname.getText()))
            sb_complete.setEnabled(false);
        else
            sb_complete.setEnabled(true);
    }

    @OnClick(R.id.sb_complete)
    public void onClickComplete() {
        String str = "保存昵称：" + et_nickname.getText().toString();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        et_nickname.setText("");
    }
}

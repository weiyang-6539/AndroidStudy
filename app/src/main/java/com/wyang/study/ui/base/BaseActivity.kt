package com.wyang.study.ui.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    protected open var mBinding: T? = null
    protected val mTag = this.javaClass.simpleName;

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewBinding()
        setContentView(mBinding?.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        initialize()
    }

    abstract fun getViewBinding(): T

    open fun initialize() {

    }
    open fun initToolBar(toolbar: Toolbar, name: String?, showHomeAsUp: Boolean) {
        toolbar.title = name
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(showHomeAsUp)
            supportActionBar!!.setHomeButtonEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
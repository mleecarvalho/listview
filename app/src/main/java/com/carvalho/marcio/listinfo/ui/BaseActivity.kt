package com.carvalho.marcio.listinfo.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding


abstract class BaseActivity : AppCompatActivity() {

    abstract fun getViewModel(): BaseViewModel

    abstract fun getBinding(): ViewDataBinding
}
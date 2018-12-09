package com.carvalho.marcio.listinfo.ui.features.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.carvalho.marcio.listinfo.R
import com.carvalho.marcio.listinfo.databinding.ActivityLogInBinding
import com.carvalho.marcio.listinfo.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInActivity : BaseActivity() {

    private lateinit var binding: ActivityLogInBinding
    private val logInViewModel: LogInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in)
    }

    override fun getViewModel() = logInViewModel

    override fun getBinding() = binding
}

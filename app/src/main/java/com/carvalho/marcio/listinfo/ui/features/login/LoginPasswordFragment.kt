package com.carvalho.marcio.listinfo.ui.features.login


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.carvalho.marcio.listinfo.databinding.FragmentLogInPasswordBinding
import com.carvalho.marcio.listinfo.ui.BaseFragment
import com.carvalho.marcio.listinfo.ui.extensions.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginPasswordFragment : BaseFragment() {

    private lateinit var binding: FragmentLogInPasswordBinding
    private lateinit var cpf: String

    private val logInViewModel: LogInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInPasswordBinding.inflate(inflater, container, false)

        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cpf = LoginPasswordFragmentArgs.fromBundle(arguments).cpf
        binding.apply {
            toolbar.title = cpf
            buttonGetIn.setOnClickListener {
                if (validatePassword()) {
                    root.hideKeyboard()
                    logInViewModel.logIn(
                        cpf = cpf,
                        password = inputPassword.text.toString()
                    )
                }
            }
            inputPassword.requestFocus()
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        logInViewModel.user.observe(this, Observer {
            it?.let {
                Log.d("LogIn response", "sucesso!")
            }
        })
    }

    private fun validatePassword() = true

    override fun getViewModel() = logInViewModel

    override fun getBinding() = binding

}

package com.carvalho.marcio.listinfo.ui.features.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.carvalho.marcio.listinfo.databinding.FragmentLogInPasswordBinding
import com.carvalho.marcio.listinfo.ui.BaseFragment
import com.carvalho.marcio.listinfo.ui.extensions.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginPasswordFragment : BaseFragment() {

    private lateinit var binding: FragmentLogInPasswordBinding
    private lateinit var cpf: String

    private val signInViewModel: LogInViewModel by viewModel()

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
                }
            }
            inputPassword.requestFocus()
        }
    }

    private fun validatePassword() = false

    private fun togglePasswordError(visible: Boolean) {
        binding.textPasswordError.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    override fun getViewModel() = signInViewModel

    override fun getBinding() = binding

}

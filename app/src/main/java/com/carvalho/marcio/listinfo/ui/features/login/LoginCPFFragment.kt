package com.carvalho.marcio.listinfo.ui.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.carvalho.marcio.listinfo.data.validator.LengthValidator
import com.carvalho.marcio.listinfo.databinding.FragmentLogInCpfBinding
import com.carvalho.marcio.listinfo.ui.BaseFragment
import com.carvalho.marcio.listinfo.ui.extensions.onChange
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginCPFFragment : BaseFragment() {
    private lateinit var binding: FragmentLogInCpfBinding

    private val signInViewModel: LogInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInCpfBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonNext.setOnClickListener {
                val action = LoginCPFFragmentDirections
                    .actionCpfToPassword(inputCpf.text.toString())
                NavHostFragment.findNavController(this).navigate(action)
            }
            inputCpf.onChange {
                if (inputCpf.length() == LengthValidator.VALID_CPF_SIZE) enableButton()

                if (inputCpf.length() == LengthValidator.INVALID_CPF_SIZE || inputCpf.length() == LengthValidator.EMPTY) {
                    buttonNext.isEnabled = false
                    toggleCpfError(false)
                }
            }
        }

    }

    private fun enableButton() {
        binding.buttonNext.isEnabled = validateCpf()
    }

    private fun validateCpf() = true

    private fun toggleCpfError(visible: Boolean) {
        binding.textCpfError.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    override fun getViewModel() = signInViewModel

    override fun getBinding() = binding
}

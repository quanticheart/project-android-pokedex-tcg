package com.quanticheart.tcg.presentation.login.login

import androidx.navigation.fragment.findNavController
import com.quanticheart.core.base.BaseFragment
import com.quanticheart.core.dialog.msgDialog
import com.quanticheart.tcg.R
import com.quanticheart.tcg.databinding.FragmentLoginBinding
import com.quanticheart.tcg.goHome
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment :
    BaseFragment<LoginViewModel, FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override val viewModel: LoginViewModel by viewModel()

    override fun view(binding: FragmentLoginBinding): Unit = binding.run {
        tvResetPassword.setOnClickListener {
            viewModel.doLogin(email.text, password.text)
        }

        tvNewAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        btLogin.setOnClickListener {
            viewModel.doLogin(email.text, password.text)
        }
    }

    override fun viewModel(viewModel: LoginViewModel): Unit = viewModel.run {
        loading.observe(viewLifecycleOwner) {
            if (it) showLoading() else hideLoading()
        }

        error.observe(viewLifecycleOwner) {
            it?.let {
                activity?.msgDialog(it)
                viewModel.consumedError()
            }
        }

        success.observe(viewLifecycleOwner) {
            activity?.goHome()
            finish()
        }
    }
}




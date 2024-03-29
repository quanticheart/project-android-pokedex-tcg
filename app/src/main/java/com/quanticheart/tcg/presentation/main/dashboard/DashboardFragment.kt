package com.quanticheart.tcg.presentation.main.dashboard

import android.view.View
import com.quanticheart.core.base.fragment.BaseFragment
import com.quanticheart.core.dialog.actionDialog
import com.quanticheart.core.dialog.msgDialog
import com.quanticheart.core.extentions.openLink
import com.quanticheart.core.extentions.toast
import com.quanticheart.domain.model.ViewState
import com.quanticheart.tcg.BuildConfig
import com.quanticheart.tcg.R
import com.quanticheart.tcg.databinding.FragmentDashboardBinding
import com.quanticheart.tcg.goAbout
import com.quanticheart.tcg.goLogin
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment :
    BaseFragment<DashboardViewModel, FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    override val viewModel: DashboardViewModel by viewModel()

    override fun view(binding: FragmentDashboardBinding): Unit = binding.run {
        btnAbout.setOnClickListener {
            activity?.goAbout()
        }

        btnExit.setOnClickListener {
            activity?.actionDialog(getString(R.string.msg_logout)) {
                viewModel.logout()
            }
        }

        binding.banner.setOnClickListener {
            activity?.openLink("https://www.fiap.com.br/")
        }

        if (BuildConfig.FLAVOR == "pro") {
            binding.banner.visibility = View.GONE
        }
    }

    override fun viewModel(viewModel: DashboardViewModel): Unit = viewModel.run {
        logout.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    activity?.goLogin()
                    finish()
                }
                is ViewState.Loading -> {}
                is ViewState.Failure -> activity?.toast(
                    it.throwable.message ?: "Tente novamente mais tarde"
                )
            }
        }

        session.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    binding.run {
                        textName.text = it.data.name
                        textEmail.text = it.data.email
                    }
                }
                is ViewState.Loading -> {
                    binding.run {
                        textName.text = "Carregando"
                        textEmail.text = "..."
                    }
                }
                is ViewState.Failure -> {
                    binding.run {
                        textName.text = "Erro"
                        textEmail.text = ""
                    }
                    activity?.toast(it.throwable.message ?: "Erro ao carregar sessão")
                }
            }
        }
    }
}

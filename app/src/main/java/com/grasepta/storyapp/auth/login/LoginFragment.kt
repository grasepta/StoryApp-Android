package com.grasepta.storyapp.auth.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.grasepta.storyapp.core.MainActivity
import com.grasepta.storyapp.base.helper.getTexts
import com.grasepta.storyapp.base.helper.observe
import com.grasepta.storyapp.databinding.FragmentLoginBinding
import com.grasepta.storyeapp.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun initView() {
        playAnimation()

        bind.edLoginEmail.apply {
            setTextInputLayout(bind.textInputLayoutUsername)
            setMaxLength(20)
            maxLines = 1
        }

        bind.edLoginPassword.apply {
            setTextInputLayout(bind.textInputLayoutPassword)
            setMaxLength(20)
            setMinLength(8)
            maxLines = 1
        }


    }

    override fun initListener() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as? MainActivity)?.handleBackPress()
            }
        })


        bind.tvRegister.setOnClickListener {
            gooTo(LoginFragmentDirections.toRegister())
        }

        bind.btnLogin.setOnClickListener {
            viewModel.doLogin(bind.edLoginEmail.getTexts(), bind.edLoginPassword.getTexts())
        }
    }

    override fun initObserver() {

        observe(viewModel.login) { data ->
            data?.data?.let { gooTo(LoginFragmentDirections.toHome()) }
            data?.message?.let { displayInfoMessage(it) }
            data?.loading?.let { showLoadingDialog(it) }
            data?.specialMessage?.forceLogout()
        }

    }

    private fun playAnimation() {
        val bookImg = ObjectAnimator.ofFloat(bind.ivBook, View.ALPHA, 1f).setDuration(2500)

        val emailLayout = ObjectAnimator.ofFloat(bind.textInputLayoutUsername, View.ALPHA, 1f).setDuration(500)
        val emailEt = ObjectAnimator.ofFloat(bind.edLoginEmail, View.ALPHA, 1f).setDuration(500)

        val passLayout = ObjectAnimator.ofFloat(bind.textInputLayoutPassword, View.ALPHA, 1f).setDuration(500)
        val passEt = ObjectAnimator.ofFloat(bind.edLoginPassword, View.ALPHA, 1f).setDuration(500)

        val registerTv = ObjectAnimator.ofFloat(bind.tvRegister, View.ALPHA, 1f).setDuration(500)

        val loginBtn = ObjectAnimator.ofFloat(bind.btnLogin, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(bookImg, emailLayout, emailEt, passLayout, passEt, registerTv, loginBtn)
            start()
        }
    }
}
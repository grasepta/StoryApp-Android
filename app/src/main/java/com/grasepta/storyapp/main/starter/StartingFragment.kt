package com.grasepta.storyapp.main.starter

import androidx.fragment.app.viewModels
import com.grasepta.storyapp.databinding.FragmentStartingBinding
import com.grasepta.storyeapp.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartingFragment : BaseFragment<FragmentStartingBinding>(FragmentStartingBinding::inflate) {

    private val viewModel: StartingViewModel by viewModels()

    override fun initView() {
        if (viewModel.isLogin()) gooTo(StartingFragmentDirections.toHome()) else gooTo(StartingFragmentDirections.toLogin())
    }

}
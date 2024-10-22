package com.grasepta.storyapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grasepta.storyapp.R
import com.grasepta.storyapp.base.helper.observe
import com.grasepta.storyapp.databinding.DialogCommonCoreBinding

class InfoDialog : BaseDialogFragment<DialogCommonCoreBinding>(DialogCommonCoreBinding::inflate) {

    private val codes = ArrayList<String>()
    private val messages = ArrayList<String>()

    private var title = MutableLiveData<String>()
    private val mTitle: LiveData<String> = title

    var buttonText = MutableLiveData<String>()
    private val mButtonText: LiveData<String> = buttonText

    var icon = MutableLiveData<Int>()
    private val mIcon: LiveData<Int> = icon

    var listener: (() -> Unit)? = null

    override fun initListener() {
        bind.btnCommonOk.listener {
            title.value = ""
            codes.clear()
            messages.clear()
            listener?.invoke()
        }
    }

    override fun initObserver() {
        observe(mTitle) {
            bind.tvCommonTitle.text = it.orEmpty()
        }
        observe(mButtonText) {
            bind.btnCommonOk.text = it.orEmpty()
        }
        observe(mIcon) {
            bind.imgCommon.setImageResource(it ?: R.drawable.img_no_image)
        }
    }

    fun addMessage(data: String) {
        title.value = data
    }

}
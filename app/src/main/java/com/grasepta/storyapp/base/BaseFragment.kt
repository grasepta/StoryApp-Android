package com.grasepta.storyeapp.base

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.grasepta.storyapp.R
import com.grasepta.storyapp.base.BaseActivity
import com.grasepta.storyapp.base.BaseCommonFunction
import com.grasepta.storyapp.base.FragmentLifecycleAware
import com.grasepta.storyapp.base.InfoDialog
import com.grasepta.storyapp.base.UIConstant
import com.grasepta.storyapp.util.Inflate

abstract class BaseFragment<out VB: ViewBinding>(private val inflate: Inflate<VB>): Fragment(), BaseCommonFunction {

    private var _binding: VB? = null
    protected val bind get() = _binding!!

    protected val mainScope by lazy { FragmentLifecycleAware() }
    private var messageDialog: InfoDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(mainScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    override fun onPause() {
        super.onPause()
        loadingDialog.dismiss()
    }

    override fun onDestroyView() {
        showLoadingDialog(false)
        messageDialog?.dismiss()
        loadingDialog.dismiss()
        messageDialog = null
        _binding = null
        super.onDestroyView()
    }


    override fun initView() {}

    override fun initListener() {}

    override fun initObserver() {}

    protected fun gooTo(directions: NavDirections) = findNavController().navigate(directions)

    protected fun setStatusBarColor(color: Int, isLightStatusBars: Boolean){
        val view = requireActivity().window
        view.statusBarColor = ContextCompat.getColor(requireContext(), color)
        WindowInsetsControllerCompat(view, view.decorView).isAppearanceLightStatusBars = isLightStatusBars
    }

    private val loadingDialog: Dialog by lazy {
        Dialog(requireContext()).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.custom_loading, null)
            setContentView(view)
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window!!.attributes = WindowManager.LayoutParams().apply {
                copyFrom(window!!.attributes)
                width = WindowManager.LayoutParams.WRAP_CONTENT
                height = WindowManager.LayoutParams.WRAP_CONTENT
                gravity = Gravity.CENTER
            }
            setCancelable(false)
        }
    }

    protected fun showLoadingDialog(status: Boolean) {
        if (status) loadingDialog.show() else loadingDialog.dismiss()
    }


    fun displayInfoMessage(message: String) =
        displayMessage(message, UIConstant.OK, R.drawable.img_fail)


    fun displaySuccessInfoMessage(message: String) =
        displayMessage(message, UIConstant.OK, R.drawable.ic_check)

    protected fun displayMessage(
        title: String,
        button: String? = UIConstant.OK,
        icon: Int? = R.drawable.img_fail,
        ok: (() -> Unit)? = null,
    ) = (activity as BaseActivity<*>).displayMessage(
        title = title,
        button = button,
        icon = icon,
        ok = ok
    )

    fun String.forceLogout() = displayMessage(this, "Login", R.drawable.img_fail) {
        requireActivity().startActivity(
            Intent().setClassName(
            requireActivity().packageName, "com.grasepta.storyapp.core.MainActivity"
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        })
    }
}
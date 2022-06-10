package com.ikopon.ikopon.presentation.view

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.ikopon.ikopon.R
import com.ikopon.ikopon.databinding.ToolbarViewBinding


class ToolbarView : Toolbar, View.OnClickListener {

    private var showBack = false
    private var title: String? = null

    private var _binding: ToolbarViewBinding? = null
    private val binding
    get() = _binding!!

    init { // inflate binding and add as view
        _binding = ToolbarViewBinding.inflate(LayoutInflater.from(context), this)
    }

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(
        context: Context, attrs: AttributeSet
    ) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(
        context: Context, attrs: AttributeSet, defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        elevation = 0f
        setPadding(0, 0, 0, 0)
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        setContentInsetsAbsolute(0, 0)
        setContentInsetsRelative(0, 0)

        // Load attributes
        val ta = getContext().obtainStyledAttributes(attrs, R.styleable.ToolbarView, defStyleAttr, 0)
        title = ta.getString(R.styleable.ToolbarView_tv_title)
        showBack = ta.getBoolean(R.styleable.ToolbarView_tv_show_back, false)
        ta.recycle()

        //-----------------------------------

        showBack(showBack)
        setTitle(title)

        binding.imgBack.setOnClickListener(this)
    }

    fun setTitle(title: String?) {
        title?.let {
            binding.txtTitle.text = it
            binding.txtTitle.visibility = if (it.isNotBlank()) VISIBLE else INVISIBLE
        }
    }

    fun showBack(showBack: Boolean) {
        binding.imgBack.visibility = if (showBack) VISIBLE else GONE
    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.img_back -> {
                    scanForActivity(context)?.finish()
                }
            }
        }
    }

    fun getBackBtn() = binding.imgBack

    private fun scanForActivity(cont: Context?): Activity? {
        return when (cont) {
            null -> null
            is Activity -> cont
            is ContextWrapper -> scanForActivity(
                cont.baseContext
            )
            else -> null
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

    override fun onViewRemoved(child: View?) {
        super.onViewRemoved(child)
        _binding = null
    }
}
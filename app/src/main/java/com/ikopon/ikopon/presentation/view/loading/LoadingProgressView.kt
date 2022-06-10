package com.ikopon.ikopon.presentation.view.loading


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import com.ikopon.ikopon.R
import com.ikopon.ikopon.databinding.LoadingProgressBinding


class LoadingProgressView : LinearLayout {
    private var onNetworkClickListener: OnNetworkClickListener? = null

    private var _binding: LoadingProgressBinding? = null
    private val binding
        get() = _binding!!

    init {
        _binding = LoadingProgressBinding.inflate(LayoutInflater.from(context), this)
    }

    interface OnNetworkClickListener {
        fun onNetworkClickListener()
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
//        View.inflate(context, R.layout.loading_progress, this)
        binding.internetConnection.setOnClickListener(OnClickListener {
            onNetworkClickListener?.onNetworkClickListener()
        })
        if (attrs != null) {
            val a = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingProgressView)
            try {
                val n = a.indexCount
                for (i in 0 until n) {
                    val attr = a.getIndex(i)
                    when (attr) {
                        R.styleable.LoadingProgressView_prog_color -> {
                            val text = a.getResourceId(attr, 0)
                            setProgressColor(text)
                        }
                        else -> {
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                a.recycle()
            }
        }
    }

    fun setOnNetworkListener(onLoadingClickListener: OnNetworkClickListener?) {
        onNetworkClickListener = onLoadingClickListener
    }

    private fun setProgressVisibility(visibility: Int) {
        binding.progressWheel.visibility = visibility
        setGonePanel()
    }

    private fun setNoItemVisibility(visibility: Int) {
        binding.noItem.visibility = visibility
        setGonePanel()
    }

    private fun networkConnection(visibility: Int) {
        binding.internetConnection.visibility = visibility
        setGonePanel()
    }

    fun setErrorText(text: String?) {
        binding.noItem.text = text
    }

    private fun setGonePanel() {
        if (binding.noItem.visibility == View.GONE && binding.progressWheel.visibility == View.GONE && binding.internetConnection.visibility == View.GONE) binding.prgLoadMore.visibility =
            View.GONE else binding.prgLoadMore.visibility = View.VISIBLE
    }

    fun error() {
        setNoItemVisibility(View.VISIBLE)
        setProgressVisibility(View.GONE)
    }

    fun init() {
        setNoItemVisibility(View.GONE)
        setProgressVisibility(View.GONE)
        networkConnection(View.GONE)
    }

    fun loaded() {
        setNoItemVisibility(View.GONE)
        setProgressVisibility(View.GONE)
        networkConnection(View.GONE)
    }

    fun loading() {
        setProgressVisibility(View.VISIBLE)
        setNoItemVisibility(View.GONE)
        networkConnection(View.GONE)
    }

    fun hideProgress() {
        setNoItemVisibility(View.GONE)
        setProgressVisibility(View.GONE)
    }

    fun noNetwork() {
        networkConnection(View.VISIBLE)
        setErrorText("عدم اتصال به اینترنت!")
        error()
    }

    fun hasNetwork() {
        networkConnection(View.GONE)
    }

    fun setProgressColor(color: Int) { //        progress_wheel.setBarColor(color);
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


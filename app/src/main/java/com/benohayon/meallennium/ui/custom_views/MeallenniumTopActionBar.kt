package com.benohayon.meallennium.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.benohayon.meallennium.R
import com.benohayon.meallennium.ui.custom_views.stylable.StylableTextView

class MeallenniumTopActionBar : LinearLayout {

    var centerText: CharSequence
        get() = centerTextView.text
        set(value) {
            centerTextView.text = value
        }

    private lateinit var leftButton: ImageView
    private lateinit var rightButton: ImageView
    private lateinit var centerTextView: StylableTextView

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context?) {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_top_action_bar, this)
        initUI()
    }

    private fun initUI() {
        leftButton = findViewById(R.id.topActionBarLeftButton)
        rightButton = findViewById(R.id.topActionBarRightButton)
        centerTextView = findViewById(R.id.topActionBarCenterText)
    }

    fun setLeftButtonResource(resource: Int) {
        leftButton.setImageResource(resource)
    }

    fun setRightButtonResource(resource: Int) {
        rightButton.setImageResource(resource)
    }

    fun setLeftButtonOnClickListener(onClick: (View) -> Unit) {
        leftButton.setOnClickListener(onClick)
    }

    fun setRightButtonOnClickListener(onClick: (View) -> Unit) {
        rightButton.setOnClickListener(onClick)
    }
}
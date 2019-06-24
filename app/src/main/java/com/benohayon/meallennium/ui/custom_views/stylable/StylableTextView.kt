package com.benohayon.meallennium.ui.custom_views.stylable

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class StylableTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        CustomFontGenerator.setDefaultFont(this)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        CustomFontGenerator.setCustomFont(context, attrs, this)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        CustomFontGenerator.setCustomFont(context, attrs, this)
    }
}

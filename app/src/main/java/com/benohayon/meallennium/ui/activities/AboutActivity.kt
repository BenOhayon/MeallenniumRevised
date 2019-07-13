package com.benohayon.meallennium.ui.activities

import android.os.Bundle
import com.benohayon.meallennium.BuildConfig
import com.benohayon.meallennium.R
import com.benohayon.meallennium.ui.activities.abs.BaseActivity
import com.benohayon.meallennium.ui.custom_views.MeallenniumTopActionBar
import com.benohayon.meallennium.ui.custom_views.stylable.StylableTextView

class AboutActivity : BaseActivity() {

    override val layoutResource: Int
        get() = R.layout.activity_about

    private lateinit var versionText: StylableTextView
    private lateinit var topActionBar: MeallenniumTopActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
    }

    private fun initUI() {
        versionText = findViewById(R.id.aboutScreenVersionValueText)
        topActionBar = findViewById(R.id.aboutScreenTopActionBar)

        topActionBar.setLeftButtonResource(R.mipmap.back_icon)
        topActionBar.setLeftButtonOnClickListener { finish() }
        topActionBar.centerText = getString(R.string.about_screen_top_action_bar_center_text)

        versionText.text = " ${BuildConfig.VERSION_NAME}"
    }
}

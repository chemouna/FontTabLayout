package com.mounacheikhna.fonttablayoutapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mounacheikhna.fonttablayout.CustomTabLayout

/**
 * Created by mounacheikhna on 06/09/2017.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customTabLayout = findViewById<CustomTabLayout>(R.id.custom_tab_layout)

        val tabTitles = arrayOf("Title 1", "Title 2", "Title 3", "Title 4")
        for (title in tabTitles) {
            val tab = customTabLayout.newTab()
            tab.text = title
            customTabLayout.addTab(tab)
        }

    }
}

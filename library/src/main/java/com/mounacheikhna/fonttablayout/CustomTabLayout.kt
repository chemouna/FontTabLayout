package com.mounacheikhna.fonttablayout

import android.content.Context
import android.support.design.widget.TabLayout
import android.util.AttributeSet
import android.util.Log
import android.graphics.Typeface
import android.widget.TextView
import android.view.ViewGroup
import android.text.TextUtils


/**
 * Created by mounacheikhna on 05/09/2017.
 */
class CustomTabLayout : TabLayout {

    private var tabSelectedTextAppearance: Int = 0
    private var selectedFontFamily: String? = null

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val customAttr = context.obtainStyledAttributes(attrs, R.styleable.CustomTabLayout)
        tabSelectedTextAppearance = customAttr.getResourceId(R.styleable.CustomTabLayout_tabSelectedTextAppearance,
                R.style.TextAppearance_Design_Tab)
        customAttr.recycle()

        val textAttrs = intArrayOf(android.R.attr.fontFamily)
        val selectedAppearance = context.obtainStyledAttributes(tabSelectedTextAppearance, textAttrs)

        try {
            selectedFontFamily = selectedAppearance.getString(0)
            Log.d("TEST", "selectedFontFamily = " + selectedFontFamily)
        } catch (e: Exception) {
            Log.d("TEST", "SwiftKeyTabLayout: ")
        }

        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (TextUtils.isEmpty(selectedFontFamily) && tab == null) return
                val tabsParentView = getChildAt(0) as ViewGroup
                val tabView = tabsParentView.getChildAt(tab!!.position) as ViewGroup

                // At index 1 we get the TextView of the tab.
                val textViewTabChild = tabView.getChildAt(1)
                // Check if it is a TextView to fail gracefully in case TabLayout changes this positioning
                if (textViewTabChild is TextView) {
                    // TODO: bold temp here -> should instead take the one from style
                    textViewTabChild.typeface = Typeface.create(selectedFontFamily, Typeface.BOLD)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab == null) return
                val tabsParentView = getChildAt(0) as ViewGroup
                val tabView = tabsParentView.getChildAt(tab.position) as ViewGroup

                // At index 1 we get the TextView of the tab.
                val textViewTabChild = tabView.getChildAt(1)
                // Check if it is a TextView to fail gracefully in case TabLayout changes this positioning
                if (textViewTabChild is TextView) {
                    // TODO: hardcoded sans-serif is temp here -> use on from TextAppearance
                    textViewTabChild.typeface = Typeface.create("sans-serif", Typeface.NORMAL)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }


}
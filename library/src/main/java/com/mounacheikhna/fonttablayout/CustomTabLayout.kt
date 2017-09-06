package com.mounacheikhna.fonttablayout

import android.content.Context
import android.support.design.widget.TabLayout
import android.util.AttributeSet
import android.support.v4.widget.TextViewCompat
import android.widget.TextView
import android.view.ViewGroup


/**
 * Created by mounacheikhna on 05/09/2017.
 */
class CustomTabLayout : TabLayout {

    private var tabSelectedTextAppearance: Int = 0
    private var tabTextAppearance: Int = 0
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

        //TODO: TabLayout_tabTextAppearance is private -> find a way around it
        tabTextAppearance = customAttr.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab)

        customAttr.recycle()

        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //TODO: handle the case where tabSelectedTextAppearance is not provided
                updateTabTextSelectedState(tab, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                updateTabTextSelectedState(tab, false)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun updateTabTextSelectedState(tab: Tab?, isSelected: Boolean) {
        if (tab == null) return
        val tabsParentView = getChildAt(0) as ViewGroup
        val tabView = tabsParentView.getChildAt(tab.position) as ViewGroup

        // At index 1 we get the TextView of the tab.
        val textViewTabChild = tabView.getChildAt(1)
        if (textViewTabChild is TextView) {
            val textAppearanceResId = if(isSelected) tabSelectedTextAppearance else tabTextAppearance
            TextViewCompat.setTextAppearance(textViewTabChild, textAppearanceResId)
        }
    }


}
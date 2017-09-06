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

        //TODO: figure out how to avoid this private warning here
        tabTextAppearance = customAttr.getResourceId(R.styleable.TabLayout_tabTextAppearance,
                R.style.TextAppearance_Design_Tab)

        customAttr.recycle()

        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
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
            //TODO: figure out why tab.isSelected does not give the correct selected or not
            var textAppearanceResId = tabTextAppearance
            if(isSelected && tabSelectedTextAppearance != 0) {
                textAppearanceResId = tabSelectedTextAppearance
            }
            TextViewCompat.setTextAppearance(textViewTabChild, textAppearanceResId)
        }
    }

}
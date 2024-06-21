package com.example.search.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.core.content.withStyledAttributes
import com.example.search.R
import com.example.search.databinding.ItemFilterBinding


internal class ItemFilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr){

    var title: String? = null
    @DrawableRes var background: Int? = null
    @DrawableRes var icon: Int? = null
    init {
        context.applicationContext.applicationContext.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.ItemFilterView,
            defStyleAttr = defStyleAttr
        ) {
            title = getString(R.styleable.ItemFilterView_filter_title)
            background = getResourceId(R.styleable.ItemFilterView_filter_background, R.drawable.background_filter_1)
            icon = getResourceId(R.styleable.ItemFilterView_filter_icon, com.example.ui.R.drawable.ic_diff_way)
        }

        val binding = ItemFilterBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

        binding.filterTitle.text = title
        binding.filterBackground.setBackgroundResource(background!!)
        binding.filterIcon.setImageResource(icon!!)
    }
}
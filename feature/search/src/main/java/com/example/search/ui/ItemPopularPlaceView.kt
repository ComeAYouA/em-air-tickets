package com.example.search.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.core.content.withStyledAttributes
import com.example.search.R
import com.example.search.databinding.ItemFilterBinding
import com.example.search.databinding.ItemPopularPlacesBinding


class ItemPopularPlaceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr){

    var title: String? = null
    @DrawableRes var icon: Int? = null
    init {
        context.applicationContext.applicationContext.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.ItemPopularPlaceView,
            defStyleAttr = defStyleAttr
        ) {
            title = getString(R.styleable.ItemPopularPlaceView_place_title)
            icon = getResourceId(R.styleable.ItemPopularPlaceView_place_icon, com.example.ui.R.drawable.img_sochi)
        }

        val binding = ItemPopularPlacesBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

        Log.d("myTag", "$title, $icon")

        binding.textViewCity.text = title
        binding.iconCity.setImageResource(icon!!)
    }
}
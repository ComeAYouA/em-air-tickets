package com.example.offer.ui.model

import android.icu.util.Calendar
import androidx.annotation.DrawableRes
import com.example.model.toDayOfWeek
import com.example.model.toMonth
import com.example.ui.R

data class Property(
    @DrawableRes var icon: Int? = null,
    var title: String? = null,
    var subtitle: String? = null,
)
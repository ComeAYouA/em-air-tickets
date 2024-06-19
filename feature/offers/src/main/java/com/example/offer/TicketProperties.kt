package com.example.offer

import androidx.annotation.DrawableRes
import com.example.ui.R

data class Property(
    @DrawableRes var icon: Int?,
    var title: String?,
    var subtitle: String?
)

val properties = listOf(
    Property(
        R.drawable.ic_plus,
        "обратно",
        null
    ),
    Property(
        null,
        "24 фев",
        ", сб"
    ),
    Property(
        R.drawable.ic_member,
        "1, эконом",
        null
    ),
    Property(
        R.drawable.ic_settings,
        "фильтры",
        null
    )
)
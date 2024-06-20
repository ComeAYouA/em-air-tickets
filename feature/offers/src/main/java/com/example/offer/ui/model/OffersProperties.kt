package com.example.offer.ui.model

import android.icu.util.Calendar
import androidx.annotation.DrawableRes
import com.example.ui.R

data class Property(
    @DrawableRes var icon: Int? = null,
    var title: String? = null,
    var subtitle: String? = null,
    var action: (() -> Unit)? = null
)

val properties = listOf(
    with(Calendar.getInstance()){
        Property(
            title = "${get(Calendar.DAY_OF_MONTH)} ${(get(Calendar.MONTH) + 1).toMonth().title}",
            subtitle = ", ${get(Calendar.DAY_OF_WEEK - 1).toDayOfWeek().title}"
        )
    },
    Property(
        icon = R.drawable.ic_member,
        title = "1, эконом",
    ),
    Property(
        icon = R.drawable.ic_settings,
        title = "фильтры",
    )
)
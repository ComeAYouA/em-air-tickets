package com.example.home.rv

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListItemsDecorations(
    private val innerDivider: Int,
    private val outerDivider: Int,
): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val adapter = parent.adapter ?: return
        val currentPosition = parent.getChildAdapterPosition(view).takeIf { it != RecyclerView.NO_POSITION } ?: return

        val isPrevTargetView = adapter.isPrevTargetView(currentPosition)
        val isNextTargetView = adapter.isNextTargetView(currentPosition)

        val oneSideInnerDivider = innerDivider / 2

        with(outRect) {
            left = if (isPrevTargetView) oneSideInnerDivider else outerDivider
            right = if (isNextTargetView) oneSideInnerDivider else outerDivider
        }
    }

    private fun RecyclerView.Adapter<*>.isPrevTargetView(
        currentPosition: Int,
    ) = currentPosition != 0

    private fun RecyclerView.Adapter<*>.isNextTargetView(
        currentPosition: Int,
    ): Boolean {
        val lastIndex = itemCount - 1
        return currentPosition != lastIndex
    }

}
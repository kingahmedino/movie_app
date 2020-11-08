package com.app.movieapp.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CenterScaleLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {
    private val mShrinkAmount = 0.15F
    private val mShrinkDistance = 0.9F

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val orientation = orientation
        if (orientation == HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)

            val midPoint = width / 2F
            val d0 = 0F
            val d1 = mShrinkDistance * midPoint
            val s0 = 1F
            val s1 = 1F - mShrinkAmount
            for (i in 0 until childCount) {
                val child = getChildAt(i) as View
                val childMidPoint =
                    (getDecoratedRight(child) + getDecoratedLeft(child)) / 2F
                val d = Math.min(d1, Math.abs(midPoint - childMidPoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.scaleX = scale
                child.scaleY = scale
            }
            return scrolled
        } else
            return 0

    }
}
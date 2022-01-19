package www.androiddarknessbot.dashboard.presentation.recycler

import android.content.Context
import www.androiddarknessbot.R


class ScreenMeasurer(context: Context) {

    private val screenParams = context.resources.displayMetrics
    private val screenWidth = screenParams.widthPixels
    private val screenHeight = screenParams.heightPixels

    private val dashboardItemMargin =
        context.resources.getDimensionPixelSize(R.dimen.dashboard_card_margin)
    private val totalMargin = dashboardItemMargin * (OBJECTS_IN_ROW * 2)

    fun getDashboardItemWidth() = (screenWidth - totalMargin) / OBJECTS_IN_ROW

    fun getDashboardItemHeight() = screenHeight / OBJECTS_IN_COL

    companion object {
        const val OBJECTS_IN_COL = 3
        private const val OBJECTS_IN_ROW = 2
    }

}
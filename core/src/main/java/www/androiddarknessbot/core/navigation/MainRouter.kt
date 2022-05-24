package www.androiddarknessbot.core.navigation

import android.content.Context
import android.content.Intent

/**
 * @author t.shkolnik
 */
interface MainRouter {

    fun createDashboardIntent(context: Context): Intent

    fun createIntent(context: Context, policyId: String): Intent
}

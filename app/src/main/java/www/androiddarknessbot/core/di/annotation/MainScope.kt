package www.androiddarknessbot.core.di.annotation

import javax.inject.Scope

/**
 * @author t.shkolnik
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
@Scope
annotation class MainScope

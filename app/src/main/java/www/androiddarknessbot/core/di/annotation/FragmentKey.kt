package www.androiddarknessbot.core.di.annotation

import androidx.fragment.app.Fragment
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * @author t.shkolnik
 */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class FragmentKey(val value: KClass<out Fragment>)

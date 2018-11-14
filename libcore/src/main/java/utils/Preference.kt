package utils


import android.content.Context
import android.content.SharedPreferences
import corebase.CoreBase
import kotlin.reflect.KProperty

/**
 * Author: yangweichao
 * Date:   2018/11/14 11:59 AM
 * Description:  blog:https://www.jianshu.com/p/861c17162039
 */


class Preference<T>(val name: String, val default: T) {

    //     lateinit 和 lazy 是 Kotlin 中的两种不同的延迟初始化的实现,
// lateinit 只用于变量 var，而 lazy 只用于常量 val
//    lazy 应用于单例模式(if-null-then-init-else-return)，而且当且仅当变量被第一次调用的时候，委托方法才会执行。

    val preference = null

    val prefs: SharedPreferences by lazy {
        CoreBase.context.getSharedPreferences("Normal_SP", Context.MODE_PRIVATE)
    }


    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = getSharedPreferences(name, default)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putSharedPreferences(name, value)



    private fun putSharedPreferences(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Int -> putInt(name, value)
            is Float -> putFloat(name, value)
            is Long -> putLong(name, value)
            is Boolean -> putBoolean(name, value)
            is String -> putString(name, value)
            else -> throw IllegalArgumentException("SharedPreference can't be save this type")
        }.apply()
    }

    private fun getSharedPreferences(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Int -> getInt(name, default)
            is Float -> getFloat(name, default)
            is Long -> getLong(name, default)
            is Boolean -> getBoolean(name, default)
            is String -> getString(name, default)
            else -> throw IllegalArgumentException("SharedPreference can't be get this type")
        }
        return res as T
    }


}

package origin.kit.ext

import android.content.Context
import android.content.SharedPreferences

private inline fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    editor.apply()
}

fun Context.sp(name: String = packageName, mode: Int = Context.MODE_PRIVATE): SharedPreferences =
    getSharedPreferences(name, mode)


fun SharedPreferences.putValue(key: String, value: Any) = edit {
    when (value) {
        is Long -> putLong(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Boolean -> putBoolean(key, value)
        is Float -> putFloat(key, value)
        else -> throw RuntimeException()
    }
}

fun < T> SharedPreferences.getValue(key: String, default: T?): T {
    val result = when (default) {
        is Long -> getLong(key, default)
        is String? -> getString(key, default)
        is Int -> getInt(key, default)
        is Boolean -> getBoolean(key, default)
        is Float -> getFloat(key, default)
        else -> throw RuntimeException()
    }
    return result as T
}


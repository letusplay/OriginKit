package origin.kit.ext

import android.animation.TypeEvaluator
import android.content.Context
import android.view.View
import android.widget.TextView

private fun conversion(value: Float, context: Context): Float {
    val scale = context.resources.displayMetrics.density
    return value * scale + 0.5f
}
/// dp

fun Context.dp2px(value: Number): Int = conversion(value.toFloat(), this).toInt()

fun Number?.px(context: Context): Int = if (this != null) conversion(this.toFloat(), context).toInt() else 0

/// padding

fun View.setLeftPadding(dp: Int) {
    setPadding(dp.px(context), paddingTop, paddingRight, paddingBottom)
}

fun View.setTopPadding(dp: Int) {
    setPadding(paddingLeft, dp.px(context), paddingRight, paddingBottom)
}

fun View.setRightPadding(dp: Int) {
    setPadding(paddingLeft, paddingTop, dp.px(context), paddingBottom)
}

fun View.setBottomPadding(dp: Int) {
    setPadding(paddingLeft, paddingTop, paddingRight, dp.px(context))
}

fun View.setPadding(dp: Int) {
    val value = dp.px(context)
    setPadding(value, value, value, value)
}

/// visibility

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toInvisible() {
    visibility = View.INVISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) = if (value) toVisible() else toGone()

var View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) = if (value) toInvisible() else toVisible()

var View.isGone: Boolean
    get() = visibility == View.GONE
    set(value) = if (value) toGone() else toVisible()

/// textView

fun TextView.notEmpty(t: TextView.() -> Unit, f: TextView.() -> Unit) {
    if (text.toString().isNotEmpty()) t() else f()
}

//// anim

class DoubleEvaluator : TypeEvaluator<Double> {
    override fun evaluate(fraction: Float, startValue: Double, endValue: Double) =
        startValue + fraction * (endValue - startValue)

}
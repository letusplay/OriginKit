package origin.kit.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> FragmentActivity.bindViewModel(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.bindViewModel(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}

inline fun <T> unsafeLazy(noinline initializer: () -> T) =
    lazy(LazyThreadSafetyMode.NONE, initializer)

// 懒加载方式
inline fun <reified T : ViewModel> FragmentActivity.lazyViewModel() =
    unsafeLazy {
        ViewModelProviders.of(this).get(T::class.java)
    }

inline fun <reified T : ViewModel> Fragment.lazyViewModel() =
    unsafeLazy {
        ViewModelProviders.of(this).get(T::class.java)
    }
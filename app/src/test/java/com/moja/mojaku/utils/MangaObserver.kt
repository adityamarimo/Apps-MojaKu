package com.moja.mojaku.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.reactivex.observers.TestObserver

class MangaObserver<T> : Observer<T> {
    var observedValue: T? = null

    override fun onChanged(t: T) {
        observedValue = t
    }
}

fun <T> LiveData<T>.mangaObserver() = MangaObserver<T>().also {
    observeForever(it)
}
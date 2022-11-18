package com.dicoding.nrahmatd.picodiploma.core.utils

import android.content.Context
import android.util.Log
import com.nrahmatdev.androidcontext.AndroidContext
import io.reactivex.disposables.CompositeDisposable
import isfaaghyth.app.notify.Notify
import isfaaghyth.app.notify.NotifyProvider
import java.io.*
import java.nio.charset.StandardCharsets

fun log(tag: String, message: String) {
    Log.d(tag, message)
}

fun sendNotify(TAG: String, response: Any?) {
    Notify.send(ResponseNotifyHelper(TAG, response))
}

fun getNotify(
    compositeDisposable: CompositeDisposable,
    notifyResponse: (notifyResponse: ResponseNotifyHelper) -> Unit
) {
    compositeDisposable.add(
        Notify.listen(ResponseNotifyHelper::class.java, NotifyProvider(), {
            notifyResponse(it)
        }, {
            log(GlobalVariable.NOTIFY_ERROR, it.message!!)
        })
    )
}

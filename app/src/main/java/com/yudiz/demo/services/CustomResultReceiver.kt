package com.yudiz.demo.services

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver


class CustomResultReceiver(
    handler: Handler?,
    private val appReceiver: AppReceiver?
) :
    ResultReceiver(handler) {
    override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
        appReceiver?.onReceiveResult(resultCode, resultData)
    }

    interface AppReceiver {
        fun onReceiveResult(resultCode: Int, resultData: Bundle)
    }
}
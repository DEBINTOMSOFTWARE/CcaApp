package com.debin.challengechip.framework.utils


interface ResponseListener {
    fun onLoading()
    fun onSuccess()
    fun onFailure(errorMessage : String)
}
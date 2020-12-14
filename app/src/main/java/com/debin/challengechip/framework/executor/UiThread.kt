package com.debin.challengechip.framework.executor

import com.challenger.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UiThread internal constructor() : PostExecutionThread{
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}
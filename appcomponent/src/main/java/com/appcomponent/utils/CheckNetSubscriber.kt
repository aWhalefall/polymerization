package com.appcomponent.utils

import android.content.Context
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

abstract class CheckNetSubscriber<T> : Subscriber<T> {
    private lateinit var context: Context;

    constructor(context: Context) {
        this.context = context
    }

    override fun onSubscribe(s: Subscription?) {

    }

}

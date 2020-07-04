package com.codedirect.pulmos.utils.common

import android.content.Context

class ResourcesProvider {
    private var mContext: Context? = null

    fun ResourceProvider(mContext: Context?) {
        this.mContext = mContext
    }

    fun getString(resId: Int): String? {
        return mContext?.getString(resId)
    }

    fun getString(resId: Int, value: String?): String? {
        return mContext?.getString(resId, value)
    }
}
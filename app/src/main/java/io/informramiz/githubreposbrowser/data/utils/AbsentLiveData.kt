package io.informramiz.githubreposbrowser.data.utils

import android.arch.lifecycle.LiveData


/**
 * Created by Ramiz Raja on 10/08/2018.
 * Generic class to hold data (loca/remote) with it's loading status
 */
class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        //this can be posted from any thread so using postValue instead of setValue
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}
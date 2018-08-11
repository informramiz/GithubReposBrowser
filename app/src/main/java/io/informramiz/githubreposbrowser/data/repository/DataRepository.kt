package io.informramiz.githubreposbrowser.data.repository

import android.arch.lifecycle.LiveData
import io.informramiz.githubreposbrowser.data.models.Repo
import io.informramiz.githubreposbrowser.data.utils.Resource


/**
 * Created by Ramiz Raja on 10/08/2018.
 */
interface DataRepository {
    fun search(query: String): LiveData<Resource<List<Repo>>>
}
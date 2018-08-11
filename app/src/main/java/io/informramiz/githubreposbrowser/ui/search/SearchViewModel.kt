package io.informramiz.githubreposbrowser.ui.search

import android.app.Application
import android.arch.lifecycle.*
import android.content.Context
import android.widget.Toast
import io.informramiz.githubreposbrowser.GithubReposBrowserApplication
import io.informramiz.githubreposbrowser.common.AppExecutors
import io.informramiz.githubreposbrowser.data.models.Repo
import io.informramiz.githubreposbrowser.data.repository.DataRepository
import io.informramiz.githubreposbrowser.data.utils.AbsentLiveData
import io.informramiz.githubreposbrowser.data.utils.Resource
import io.informramiz.githubreposbrowser.di.qualifiers.ApplicationContext
import javax.inject.Inject


/**
 * Created by Ramiz Raja on 07/08/2018.
 */
class SearchViewModel @Inject constructor(@ApplicationContext context: Context,
                                          val appExecutors: AppExecutors,
                                          val dataRepository: DataRepository): ViewModel() {
    private val query = MutableLiveData<String>()
    val results: LiveData<Resource<List<Repo>>> = Transformations
            .switchMap(query) { search ->
                if (search.isNullOrEmpty()) {
                    AbsentLiveData.create()
                } else {
                    dataRepository.search(search)
                }
            }

    fun setQuery(newQuery: String) {
        if (query.value == newQuery) {
            return
        }

        query.value = newQuery
    }
}
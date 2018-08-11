package io.informramiz.githubreposbrowser.ui.search


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.informramiz.githubreposbrowser.R
import io.informramiz.githubreposbrowser.base.BaseFragment
import io.informramiz.githubreposbrowser.common.AppExecutors
import io.informramiz.githubreposbrowser.data.datasources.remote.GithubApiService
import io.informramiz.githubreposbrowser.data.utils.Status
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchFragment : BaseFragment() {
    @Inject
    lateinit var githubApiService: GithubApiService

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        searchViewModel.results.observe(this, Observer { resource ->
            when(resource?.status) {
                Status.LOADING -> Timber.d("Repos are loading")
                Status.ERROR -> Timber.d("Repos loading failed")
                Status.SUCCESS -> Timber.d("Repos loaded successfully")
            }
        })

        searchViewModel.setQuery("android")
    }
}

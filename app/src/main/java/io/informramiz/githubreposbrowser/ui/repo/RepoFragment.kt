package io.informramiz.githubreposbrowser.ui.repo


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.informramiz.githubreposbrowser.R
import io.informramiz.githubreposbrowser.data.GithubApiService
import io.informramiz.githubreposbrowser.di.Injectable
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class RepoFragment : Fragment(), Injectable {
    @Inject
    lateinit var githubApiService: GithubApiService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo, container, false)
    }
}

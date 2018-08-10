package io.informramiz.githubreposbrowser.ui.search

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.widget.Toast
import io.informramiz.githubreposbrowser.common.AppExecutors
import io.informramiz.githubreposbrowser.di.qualifiers.ApplicationContext
import javax.inject.Inject


/**
 * Created by Ramiz Raja on 07/08/2018.
 */
class SearchViewModel @Inject constructor(@ApplicationContext context: Context, appExecutors: AppExecutors): ViewModel() {
    init {
        appExecutors.mainThread.execute {
            Toast.makeText(context, "${SearchViewModel::class.java.simpleName} injected", Toast.LENGTH_SHORT).show()
        }
    }
}
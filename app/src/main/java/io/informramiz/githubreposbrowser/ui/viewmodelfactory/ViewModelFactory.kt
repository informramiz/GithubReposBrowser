package io.informramiz.githubreposbrowser.ui.viewmodelfactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


/**
 * Created by Ramiz Raja on 08/08/2018.
 */
@Singleton
class ViewModelFactory @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            //let's check if there is given $modelClass is a super type of any type in map
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown model class $modelClass")

        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}
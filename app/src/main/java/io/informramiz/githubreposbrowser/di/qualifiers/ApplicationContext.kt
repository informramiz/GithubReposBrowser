package io.informramiz.githubreposbrowser.di.qualifiers

import javax.inject.Qualifier


/**
 * Created by Ramiz Raja on 10/08/2018.
 */
@MustBeDocumented
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
annotation class ApplicationContext
package io.informramiz.githubreposbrowser.base

import dagger.android.DaggerIntentService


/**
 * Created by Ramiz Raja on 09/08/2018.
 */

abstract class BaseIntentService(serviceName: String) : DaggerIntentService(serviceName)
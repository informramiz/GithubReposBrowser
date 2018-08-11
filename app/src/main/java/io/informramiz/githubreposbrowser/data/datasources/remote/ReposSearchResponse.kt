package io.informramiz.githubreposbrowser.data.datasources.remote

import com.google.gson.annotations.SerializedName
import io.informramiz.githubreposbrowser.data.models.Repo


/**
 * Created by Ramiz Raja on 12/08/2018.
 */
data class ReposSearchResponse(
        @SerializedName("total_count")
        val total: Int = 0,
        @SerializedName("items")
        val repos: List<Repo>
) {
    var nextPage: Int? = null
}
package org.dps.admin.network

import org.dps.admin.model.Suggestion
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/compassLocation/rest/address/autocomplete?queryString=airtel")
    fun setSuggestionsAsync(@Query("city") cityName: String): Deferred<Response<Suggestion>>

}
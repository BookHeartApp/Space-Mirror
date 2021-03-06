package com.bogomolov.spacemirror.data.removed.apod

import com.bogomolov.spacemirror.presentation.model.apod.APODServerResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APOD {

    @GET("planetary/apod")
    suspend fun getAPOD(
        @Query("date") date: String?,
        @Query("api_key") apiKey: String
    ): Response<APODServerResponseData>
}
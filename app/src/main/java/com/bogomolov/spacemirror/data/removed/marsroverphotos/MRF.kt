package com.bogomolov.spacemirror.data.removed.marsroverphotos

import com.bogomolov.spacemirror.presentation.model.mars.MRFServerResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MRF {

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getMRF(
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String
    ): Call<MRFServerResponseData>
}
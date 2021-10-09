package com.bogomolov.spacemirror.presentation.viewmodel.apod

import com.bogomolov.spacemirror.presentation.model.apod.APODServerResponseData

sealed class APODState {
    data class Success(val serverResponseData: APODServerResponseData) : APODState()
    data class Error(val error: Throwable) : APODState()
    data class Loading(val progress: Int?) : APODState()
}
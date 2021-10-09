package com.bogomolov.spacemirror.presentation.viewmodel.earth

import com.bogomolov.spacemirror.presentation.model.earth.EarthServerResponseData

sealed class EarthState {
    data class Success(val serverResponseData: EarthServerResponseData) : EarthState()
    data class Error(val error: Throwable) : EarthState()
    data class Loading(val progress: Int?) : EarthState()
}
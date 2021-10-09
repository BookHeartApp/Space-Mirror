package com.bogomolov.spacemirror.presentation.viewmodel.mars

import com.bogomolov.spacemirror.presentation.model.mars.MRFServerResponseData

sealed class MRFState {
    data class Success(val serverResponseData: MRFServerResponseData) : MRFState()
    data class Error(val error: Throwable) : MRFState()
    data class Loading(val progress: Int?) : MRFState()
}
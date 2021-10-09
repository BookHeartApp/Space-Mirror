package com.bogomolov.spacemirror.data.local.repository

import com.bogomolov.spacemirror.data.local.cases.ItemData

interface CasesRepository {
    fun getCases(): MutableList<ItemData>
}
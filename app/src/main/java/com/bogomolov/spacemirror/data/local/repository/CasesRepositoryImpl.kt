package com.bogomolov.spacemirror.data.local.repository

import com.bogomolov.spacemirror.data.local.cases.ComplexCase
import com.bogomolov.spacemirror.data.local.cases.ItemData
import com.bogomolov.spacemirror.data.local.cases.SimpleCase

class CasesRepositoryImpl : CasesRepository {

    override fun getCases(): MutableList<ItemData> = casesList

    private val casesList = mutableListOf(
        SimpleCase("Быстрая запись_1"),
        SimpleCase("Быстрая запись_2"),
        SimpleCase("Быстрая запись_3"),
        ComplexCase("Новое дело_1", "Описание_1"),
        ComplexCase("Новое дело_2", "Описание_2"),
    )
}
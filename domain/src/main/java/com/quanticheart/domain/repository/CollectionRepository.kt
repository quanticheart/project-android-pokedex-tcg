package com.quanticheart.domain.repository

import com.quanticheart.domain.model.pokemon.Card
import com.quanticheart.domain.model.pokemon.CardCollection

interface CollectionRepository {
    suspend fun addCollection(cardID: String, name: String): Result<Boolean>
    suspend fun deleteCollectionBy(cardID: String, name: String): Result<Boolean>
    suspend fun allCollection(): Result<List<Card>>
    suspend fun findCollectionBy(id: String): Result<CardCollection>
}
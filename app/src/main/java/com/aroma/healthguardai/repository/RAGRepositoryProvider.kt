package com.aroma.healthguardai.repository
import android.content.Context
object RAGRepositoryProvider
{
    private var repository: RAGRepository? = null

    fun get(context: Context): RAGRepository {

        if (repository == null) {
            repository = RAGRepository(
                context.applicationContext
            )
        }

        return repository!!
    }
}
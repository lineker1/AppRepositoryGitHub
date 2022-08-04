package me.dio.repositoryapp.data.repositories

import kotlinx.coroutines.flow.Flow
import me.dio.repositoryapp.data.model.Repo

interface RepoRepository {
    suspend fun listRepositories(user: String): Flow<List<Repo>>
}
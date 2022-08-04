package me.dio.repositoryapp.domain


import kotlinx.coroutines.flow.Flow
import me.dio.repositoryapp.core.UseCase
import me.dio.repositoryapp.data.model.Repo
import me.dio.repositoryapp.data.repositories.RepoRepository

class ListUserRepositoriesUseCase(
    private val repository: RepoRepository
) : UseCase<String, List<Repo>>() {

    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}
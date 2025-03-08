package ru.dekabrsky.consecutivepractice2025.di

import com.example.androidpractice.domain.repository.IMoviesRepository
import com.example.androidpractice.domain.repository.MoviesRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.DetailsViewModel
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.ListViewModel

val rootModule = module {
    single<IMoviesRepository> { MoviesRepository() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
}
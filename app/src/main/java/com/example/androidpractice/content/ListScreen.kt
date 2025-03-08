package com.example.androidpractice.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androidpractice.domain.model.Movie
import com.example.androidpractice.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navigation: NavHostController) {
    val viewModel = koinViewModel<ListViewModel> { parametersOf(navigation) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Фильмы:",
                        style = Typography.titleLarge
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            List(
                viewModel.loadMovies(),
                viewModel
            )
        }
    }


}

@Composable
fun List(list: List<Movie>, viewModel: ListViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(list) { movie ->
            MovieCard(movie, viewModel)
        }
    }
}

@Composable
fun MovieCard(movie: Movie, viewModel: ListViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(4.dp),
        onClick = { viewModel.onItemClicked(movie.id) }
    ) {
        Text(
            text = movie.name,
            style = Typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}
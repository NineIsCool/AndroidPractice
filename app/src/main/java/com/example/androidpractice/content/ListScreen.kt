package com.example.androidpractice.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.androidpractice.domain.model.MovieShort
import com.example.androidpractice.ui.theme.Spacing
import com.example.androidpractice.ui.theme.Typography
import com.example.androidpractice.viewModel.ListViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ListScreen(navigation: NavHostController) {
    val viewModel = koinViewModel<ListViewModel> { parametersOf(navigation) }
    val state = viewModel.viewState

    Scaffold(
        topBar = {
            Row(
                Modifier.padding(Spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = state.query,
                    onValueChange = {
                        viewModel.onQueryChanged(it)
                    },
                    label = { Text("Введите название фильма") },
                    modifier = Modifier
                        .width(360.dp)
                        .padding(vertical = 16.dp)
                )
                BadgedBox(
                    badge = { if (state.hasBadge) Badge() },
                    Modifier.padding(3.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "More",
                        modifier = Modifier
                            .clickable { viewModel.onFiltersClicked() }
                            .size(80.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        if (state.showTypesDialog) {
            SelectionDialog(
                onDismissRequest = { viewModel.onSelectionDialogDismissed() },
                onConfirmation = { viewModel.onFiltersConfirmed() },
                title = "Тип",
                variants = state.typesVariants,
                selectedVariants = state.selectedTypes
            ) { variant, isSelected ->
                viewModel.onSelectedVariantChanged(variant, isSelected)
            }
        }

        if (state.isLoading) {
            FullscreenLoading()
            return@Scaffold
        }

        state.error?.let {
            FullscreenMessage(msg = it)
            return@Scaffold
        }

        if (state.isEmpty) {
            FullscreenMessage("По запросу нет результатов")
            return@Scaffold
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            List(
                viewModel.viewState.items,
                viewModel
            )
        }
    }
}

@Composable
fun List(list: List<MovieShort>, viewModel: ListViewModel) {
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
fun MovieCard(movie: MovieShort, viewModel: ListViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(4.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { viewModel.onItemClicked(movie.id) },
                    onDoubleTap = { viewModel.onItemDoubleClicked(movie) }
                )
            },
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = movie.poster.previewUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    text = movie.name,
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = "Тип: ${movie.type}",
                    style = Typography.labelSmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = "Жанр: ${movie.genres.joinToString(", ") { it.displayName }}",
                    style = Typography.labelSmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun FullscreenMessage(msg: String) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(Spacing.medium), contentAlignment = Alignment.Center
    ) {
        Text(text = msg)
    }
}

@Composable
fun FullscreenLoading() {
    Box(
        Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
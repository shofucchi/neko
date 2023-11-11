package io.github.shofucchi.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("Cats")
        }
    )
}

@Composable
fun HomeScreen(modifier: Modifier) {
    Column(modifier) {
        HomeList()
    }
}

@Composable
private fun HomeList() {
    val gridState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier.fillMaxSize(),
        state = gridState,
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 16.dp
        )
    ) {
        items(30) {
            HomeListItem()
        }
    }
}

@Composable
private fun HomeListItem() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.padding(
            start = 12.dp,
            end = 12.dp,
            bottom = 26.dp
        ),
    ) {
        HomeListItemContent()
    }
}

@Composable
private fun HomeListItemContent() {
    val url =
        "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1MjE4NDh8MHwxfHNlYXJjaHwxfHxjYXR8ZW58MHx8fHwxNjk5Njk3OTk3fDA&ixlib=rb-4.0.3&q=80&w=1080"
    Column(Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(url).crossfade(true).build(),
            contentDescription = "cat staring at somewhere",
            modifier = Modifier.fillMaxSize(),// 344x194
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Cat",
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 12.dp)
                .align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun HomeListPreview() {
    HomeList()
}
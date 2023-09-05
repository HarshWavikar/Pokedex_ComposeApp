package com.harshcode.pokemon_composeapp.pokemonListScreen

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.harshcode.pokemon_composeapp.R
import com.harshcode.pokemon_composeapp.data.model.PokemonListEntry
import com.harshcode.pokemon_composeapp.data.remote.response.PokemonList
import com.harshcode.pokemon_composeapp.ui.theme.RobotoCondensed

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.pokemon_master_logo),
                contentDescription = "Pokemon logo",
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            SearchBarLayout()
            Spacer(modifier = Modifier.height(15.dp))
            PokemonList(navController)

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarLayout(
   viewModel: PokemonListViewModel = hiltViewModel()
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) } // This represent the active state of the SearchBar, default value is false.
    val items = remember {
        mutableStateListOf("Charmender", "Charizard", "Bulbasaur")
    }

    Box {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            query = text,
            onQueryChange = {
                text = it        // when we type something in our Searchbar, then we are updating the actual text
                viewModel.searchPokemonList(text)
            },
            onSearch = {
                items.add(text)
                active = false   // This onSearch Lambda will be triggered whenever we press the search ime action on the keyboard.here i am changing the active state to false, so that we could actually close the dropdown of searchbar.
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = {
                if (!active) {
                    Text(text = "Search...")
                } else {
                    Text(text = text)

                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search_icon"
                )
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text  = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear_icon"
                    )
                }
            }
        ) {
            items.forEach {
                Row(modifier = Modifier.padding(25.dp)) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.History,
                        contentDescription = "history_icon"
                    )
                    Text(text = it)
                }
            }
        }
    }
}

//    @Composable
//    fun SearchBar(
//        modifier: Modifier = Modifier,
//        hint: String = "",
//        onSearch: (String) -> Unit = {}
//    ) {
//        var text by remember {
//            mutableStateOf("")
//        }
//        var isHintDisplayed by remember {
//            mutableStateOf(hint != "")
//        }
//
//        Box(modifier = modifier) {
//            OutlinedTextField(
//                value = text,
//                onValueChange = {
//                    text = it
//                    onSearch(it)
//                },
//                maxLines = 1,
//                singleLine = true,
//                textStyle = (Color.Black),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .shadow(5.dp, CircleShape)
//                    .background(Color.White, CircleShape)
//                    .padding(horizontal = 20.dp, vertical = 12.dp)
//                    .onFocusChanged {
//                        isHintDisplayed = it != FocusState && text.isNotEmpty()
//                    }
//            )
//            if(isHintDisplayed) {
//                Text(
//                    text = hint,
//                    color = Color.LightGray,
//                    modifier = Modifier
//                        .padding(horizontal = 20.dp, vertical = 12.dp)
//                )
//            }
//        }
//    }

@Composable
fun PokemonList(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonList by remember { viewModel.pokemonList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    val isSearching by remember { viewModel.isSearching}

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = if (pokemonList.size % 2 == 0) {
            pokemonList.size / 2
        } else {
            pokemonList.size / 2 + 1
        }
        items(itemCount) {
            if (it >= itemCount - 1 && !endReached && !isLoading && !isSearching) {
                viewModel.loadPokemonPaginated()
            }
            PokedexRow(
                rowIndex = it,
                entries = pokemonList,
                navController = navController
            )
        }
    }

    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        else if (loadError.isNotEmpty()) {
            RetrySection(
                error = loadError,
                onRetry = {
                    viewModel.loadPokemonPaginated()
                }
            )
        }
    }
}


@Composable
fun PokedexEntry(
    entry: PokemonListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(1.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navController.navigate("pokemon_detail_screen/${dominantColor.toArgb()}/${entry.pokemonName}")
            },
    ) {
        CardContent(
            imageUrl = entry.imageUrl,
            pokemonName = entry.pokemonName,
            backgroundColorModifierAction = { drawable ->
                viewModel.calcDominantColor(drawable) { color ->
                    dominantColor = color
                }
            })
    }
}



@Composable
private fun CardContent(
    imageUrl: String,
    pokemonName: String,
    backgroundColorModifierAction: (Drawable) -> Unit
) {
    Column {
        PokemonImage(
            imageUrl = imageUrl,
            pokemonName = pokemonName,
            backgroundColorModifierAction = backgroundColorModifierAction
        )
        PokemonTitle(pokemonName = pokemonName)
    }
}

@Composable
private fun ColumnScope.PokemonImage(
    imageUrl: String,
    pokemonName: String,
    backgroundColorModifierAction: (Drawable) -> Unit
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
//        data = imageUrl,
//        builder = {
//            ImageRequest.Builder(context)
//        }

        model = ImageRequest.Builder(context)
            .data(imageUrl)
//            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )
    Box(
        modifier = Modifier.align(CenterHorizontally),
        contentAlignment = Center
    ) {
        Image(
            modifier = Modifier
                .size(120.dp),
            painter = painter,
            contentDescription = pokemonName
        )
        (painter.state as? AsyncImagePainter.State.Success)?.let { successState ->
            LaunchedEffect(key1 = Unit) {
                val drawable = successState.result.drawable
                backgroundColorModifierAction(drawable)
            }
        } ?: CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}


@Composable
private fun PokemonTitle(pokemonName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = pokemonName,
        fontFamily = RobotoCondensed,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}

////////////////////////////////////////////////////////////////////////////////////////////////////


@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<PokemonListEntry>,
    navController: NavController
) {
    Column {
        Row {
            PokedexEntry(
                entry = entries[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Log.d("PokedexRow", entries.toString())
            Spacer(modifier = Modifier.width(16.dp))
            if (entries.size >= rowIndex * 2 + 2) {
                PokedexEntry(
                    entry = entries[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            error,
            color = Color.Red,
            fontSize = 18.sp,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            colors = ButtonDefaults.buttonColors(Color.Gray),
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth(0.5f)
        ) {
            Text(
                text = "Retry",
                fontSize = 25.sp,
                fontFamily = RobotoCondensed
            )
        }
    }
}


















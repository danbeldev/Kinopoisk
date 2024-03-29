package com.example.feature_sorting.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_network_domain.model.movie.Countrie
import com.example.core_network_domain.model.movie.Filter
import com.example.core_network_domain.model.movie.Genre
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.decodeFromString
import com.example.core_utils.common.encodeToString
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.mainNavGraph.MainScreenRoute
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenRoute
import com.example.core_utils.state.OrderTabData
import com.example.core_utils.state.TypeTabData
import com.example.feature_sorting.validate.ratingSorting
import com.example.feature_sorting.validate.yearSorting
import com.example.feature_sorting.viewModel.SortingViewModel
import kotlinx.coroutines.flow.onEach
import java.util.ArrayList

@Composable
fun SortingScreen(
    navController: NavController,
    sortingViewModel: SortingViewModel,
    genreString:String = "All",
    countriesString: String = "All"
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current

    val genreId = remember { mutableStateOf(ArrayList<Int>()) }
    val countriesId = remember { mutableStateOf(ArrayList<Int>()) }
    val genre = if (genreString == "All") listOf() else decodeFromString<List<Genre>>(genreString)
    val countries = if (countriesString == "All") listOf() else decodeFromString<List<Countrie>>(countriesString)
    val typeTabIndex = remember { mutableStateOf(0) }
    val orderTabIndex = remember { mutableStateOf(0) }
    val ratingFrom = remember { mutableStateOf("1") }
    val ratingTo = remember { mutableStateOf("10") }
    val yearFrom = remember { mutableStateOf("1800") }
    val yearTo = remember { mutableStateOf("2022") }
    val filter = remember { mutableStateOf(Filter()) }

    sortingViewModel.getFilter()
    sortingViewModel.responseFilter.onEach {
        filter.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    LaunchedEffect(key1 = Unit, block = {
        genre.forEach {
            genreId.value.add(it.id!!)
        }
    })

    LaunchedEffect(key1 = Unit, block = {
        countries.forEach {
            countriesId.value.add(it.id!!)
        }
    })

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                title = { Text(text = "Sorting")},
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(MainScreenRoute.MainRoute.Films.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = secondaryBackground
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                Column {
                    TabRow(
                        backgroundColor = primaryBackground,
                        contentColor = secondaryBackground,
                        selectedTabIndex = typeTabIndex.value,
                        tabs = {
                            TypeTabData.values().forEachIndexed { index, tabData ->
                                Tab(
                                    selected = typeTabIndex.value == index,
                                    onClick = { typeTabIndex.value = index },
                                    text = { Text(text = tabData.name) }
                                )
                            }
                        }
                    )

                    Text(
                        text = "Рейтинг",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Center,
                        color = secondaryBackground,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        OutlinedTextField(
                            value = ratingFrom.value,
                            onValueChange = { ratingFrom.value = it },
                            modifier = Modifier
                                .padding(10.dp)
                                .width(170.dp),
                            shape = AbsoluteRoundedCornerShape(20.dp),
                            label = { Text(text = "минимальный рейтинг")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = secondaryBackground,
                                backgroundColor = primaryBackground,
                                cursorColor = secondaryBackground,
                                focusedLabelColor = secondaryBackground
                            )
                        )
                        OutlinedTextField(
                            value = ratingTo.value,
                            onValueChange = { ratingTo.value = it },
                            modifier = Modifier
                                .padding(10.dp)
                                .width(170.dp),
                            shape = AbsoluteRoundedCornerShape(20.dp),
                            label = { Text(text = "максимальный рейтинг")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = secondaryBackground,
                                backgroundColor = primaryBackground,
                                cursorColor = secondaryBackground,
                                focusedLabelColor = secondaryBackground
                            )
                        )
                    }

                    Text(
                        text = "Год",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Center,
                        color = secondaryBackground,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        OutlinedTextField(
                            value = yearFrom.value,
                            onValueChange = { yearFrom.value = it },
                            modifier = Modifier
                                .padding(10.dp)
                                .width(170.dp),
                            shape = AbsoluteRoundedCornerShape(20.dp),
                            label = { Text(text = "минимальный год")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = secondaryBackground,
                                backgroundColor = primaryBackground,
                                cursorColor = secondaryBackground,
                                focusedLabelColor = secondaryBackground
                            )
                        )
                        OutlinedTextField(
                            value = yearTo.value,
                            onValueChange = { yearTo.value = it },
                            modifier = Modifier
                                .padding(10.dp)
                                .width(170.dp),
                            shape = AbsoluteRoundedCornerShape(20.dp),
                            label = { Text(text = "максимальный год")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = secondaryBackground,
                                backgroundColor = primaryBackground,
                                cursorColor = secondaryBackground,
                                focusedLabelColor = secondaryBackground
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(SortingScreenRoute.Genre.route)
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Genre:",
                                modifier = Modifier.padding(5.dp),
                                color = secondaryBackground,
                                fontWeight = FontWeight.Bold
                            )

                            if(genreString == "All"){
                                Text(
                                    text = genreString,
                                    modifier = Modifier.padding(5.dp)
                                )
                            }else{
                                LazyRow(
                                    modifier = Modifier.padding(5.dp),
                                    content = {
                                        items(genre){item ->
                                            Text(
                                                text = item.genre,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(SortingScreenRoute.Countries.route)
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Countries:",
                                modifier = Modifier.padding(5.dp),
                                color = secondaryBackground,
                                fontWeight = FontWeight.Bold
                            )

                            if(countriesString == "All"){
                                Text(
                                    text = countriesString,
                                    modifier = Modifier.padding(5.dp)
                                )
                            }else{
                                LazyRow(
                                    modifier = Modifier.padding(5.dp),
                                    content = {
                                        items(countries){item ->
                                            Text(
                                                text = item.country,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }

                    Text(
                        text = "Сортировать:",
                        modifier = Modifier.padding(10.dp),
                        color = secondaryBackground,
                        fontWeight = FontWeight.Bold
                    )

                    TabRow(
                        backgroundColor = primaryBackground,
                        contentColor = secondaryBackground,
                        selectedTabIndex = orderTabIndex.value,
                        tabs = {
                            OrderTabData.values().forEachIndexed { index, orderTabData ->
                                Tab(
                                    selected = index == orderTabIndex.value,
                                    onClick = { orderTabIndex.value = index },
                                    text = { Text(text = orderTabData.name) }
                                )
                            }
                        }
                    )

                    OutlinedButton(
                        onClick = {
                            if (ratingSorting(
                                  min = ratingFrom.value,
                                  max = ratingTo.value,
                                  context = context
                            )&& yearSorting(
                                    min = yearFrom.value,
                                    max = yearTo.value,
                                    context = context
                            )){
                                navController.navigate(SortingScreenRoute.ResultSorting.base(
                                    ratingTo = ratingTo.value,
                                    ratingFrom = ratingFrom.value,
                                    yearTo = yearTo.value,
                                    yearFrom = yearFrom.value,
                                    type = when(typeTabIndex.value){
                                        0 -> TypeTabData.ALL.name
                                        1 -> TypeTabData.FILM.name
                                        2 -> TypeTabData.TV_SHOW.name
                                        else -> TypeTabData.ALL.name
                                    },
                                    order = when(orderTabIndex.value){
                                        0 -> OrderTabData.RATING.name
                                        1 -> OrderTabData.NUM_VOTE.name
                                        2 -> OrderTabData.YEAR.name
                                        else -> OrderTabData.RATING.name
                                    },
                                    genreId = encodeToString(genreId.value),
                                    countriesId = encodeToString(countriesId.value)
                                ))
                            }
                        },
                        modifier = Modifier
                            .padding(
                                vertical = 10.dp,
                                horizontal = 12.dp
                            )
                            .height(60.dp)
                            .fillMaxWidth(),
                        shape = AbsoluteRoundedCornerShape(20.dp)
                    ) {
                        Text(text = "Search")
                    }
                }
            }
        }
    )
}
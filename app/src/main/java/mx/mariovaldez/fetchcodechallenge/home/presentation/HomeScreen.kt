package mx.mariovaldez.fetchcodechallenge.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.mariovaldez.fetchcodechallenge.home.presentation.models.HiringUI
import mx.mariovaldez.fetchcodechallenge.modules.Indicator
import mx.mariovaldez.fetchcodechallenge.navigation.NavigationActions

@Composable
internal fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    when (state) {
        HomeViewModel.HomeState.Default -> {
            // here is the default state
        }
        HomeViewModel.HomeState.Error -> {
            DisplayAlert(viewModel)
        }

        HomeViewModel.HomeState.Loading -> {
            ShowProgress()
        }

        is HomeViewModel.HomeState.Success -> {
            CircularProgressIndicator()
            ListItem(
                navController = navController,
                values = (state as HomeViewModel.HomeState.Success).hiring
            )
        }
    }
}

@Composable
fun ShowProgress() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Indicator()
        }
    }
}

@Composable
fun ListItem(
    navController: NavController,
    values: Map<Int, List<HiringUI>>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Text(
                text = "List of Hiring",
                modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                color = colorScheme.primary,
                fontWeight = FontWeight.Bold,
                style = typography.titleLarge
            )
        }
        values.forEach {
            item {
                Item(it.key, it.value, navController)
            }
        }
    }
}

@Composable
fun Item(
    key: Int,
    value: List<HiringUI>,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                onClick = {
                    NavigationActions(navController).navigateToDetails(value)
                }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = key.toString(),
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    style = typography.labelLarge
                )
            }
        }
    }
}

@Composable
internal fun DisplayAlert(viewModel: HomeViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White)
                .align(Alignment.CenterVertically)
        ) {
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                text = "One problem occured, please try again",
                textAlign = TextAlign.Center,
                color = colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    viewModel.fetchData()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Try Again", color = Color.White)
            }
        }
    }
}

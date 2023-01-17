package agency.five.codebase.android.five_project.ui.home

import agency.five.codebase.android.five_project.R
import agency.five.codebase.android.five_project.ui.components.CompetitionCard
import agency.five.codebase.android.five_project.ui.components.SearchBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreenRoute(
    homeViewModel: HomeViewModel = viewModel(),
    onCompetitionCardClick: (Int) -> Unit,
    onSearchButtonClick: (Int?) -> Unit,
    modifier: Modifier = Modifier
) {
    val competitionsViewState: HomeScreenListViewState by homeViewModel.homeScreenViewState.collectAsState()
    HomeScreen(
        modifier = modifier,
        competitions = competitionsViewState,
        onCompetitionCardClick = onCompetitionCardClick,
        onFollowButtonClick = homeViewModel::toggleFollowed,
        onSearchButtonClick = onSearchButtonClick,
    )
}

@Composable
fun HomeScreen(
    competitions: HomeScreenListViewState,
    onCompetitionCardClick: (Int) -> Unit,
    onFollowButtonClick: (Int) -> Unit,
    onSearchButtonClick: (Int?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchBar(modifier = Modifier.height(65.dp), onSearchButtonClick = { name ->
            val search =
                competitions.competitionViewStates.filter { it.competitionCardViewState.name == name }
            onSearchButtonClick(
                if (search.isNotEmpty()) {
                    search.first().id
                } else {
                    null
                }
            )
        })
        Text(
            text = stringResource(id = R.string.competitions),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Start,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(competitions.competitionViewStates) { competition ->
                CompetitionCard(
                    competition = competition.competitionCardViewState,
                    onCompetitionCardClick = { onCompetitionCardClick(competition.id) },
                    onFollowButtonClick = { onFollowButtonClick(competition.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .height(70.dp)
                )
            }
        }
    }
}

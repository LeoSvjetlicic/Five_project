package agency.five.codebase.android.five_project.ui.home

import agency.five.codebase.android.five_project.R
import agency.five.codebase.android.five_project.mock.Mock
import agency.five.codebase.android.five_project.ui.components.CompetitionCard
import agency.five.codebase.android.five_project.ui.components.SearchBar
import agency.five.codebase.android.five_project.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.five_project.ui.home.mapper.HomeScreenMapperImpl
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
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
    modifier: Modifier = Modifier
) {
    val competitionsViewState: HomeScreenListViewState by homeViewModel.homeScreenViewState.collectAsState()
    HomeScreen(
        modifier = modifier,
        competitions = competitionsViewState,
        onCompetitionCardClick = onCompetitionCardClick,
        onFollowButtonClick = homeViewModel::toggleFollowed,
        onSearchButtonClick = {}
    )
}

@Composable
fun HomeScreen(
    competitions: HomeScreenListViewState,
    onCompetitionCardClick: (Int) -> Unit,
    onFollowButtonClick: (Int) -> Unit,
    onSearchButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.competitions),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clip(RoundedCornerShape(60.dp))
                .background(colorResource(id = R.color.light_blue))
                .padding(start = 10.dp, end = 10.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        //SearchBar(modifier = Modifier.height(65.dp), onSearchButtonClick = onSearchButtonClick)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(competitions.competitionViewStates) { competition ->
                CompetitionCard(
                    competition = competition.competitionCardViewState,
                    onCompetitionCardClick = { onCompetitionCardClick(competition.id) },
                    onFollowButtonClick = { onFollowButtonClick(competition.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                )
            }
        }
    }
}

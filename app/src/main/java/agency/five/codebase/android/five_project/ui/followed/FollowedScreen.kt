package agency.five.codebase.android.five_project.ui.followed

import agency.five.codebase.android.five_project.R
import agency.five.codebase.android.five_project.ui.components.CompetitionCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FollowedScreenRoute(
    followedScreenViewModel: FollowedScreenViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onCompetitionCardClick: (Int) -> Unit,
) {
    val followed by followedScreenViewModel.favoriteScreenViewState.collectAsState()
    FollowedScreen(
        modifier = modifier,
        followedViewState = followed,
        onCompetitionCardClick = onCompetitionCardClick,
        onFollowButtonCLick = followedScreenViewModel::toggleFollowed
    )
}

@Composable
fun FollowedScreen(
    followedViewState: FollowedViewState,
    onCompetitionCardClick: (Int) -> Unit,
    onFollowButtonCLick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.followed_screen),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clip(RoundedCornerShape(60.dp))
                .background(colorResource(id = R.color.light_blue))
                .padding(start = 10.dp, end = 10.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(followedViewState.competitionCardViewStates) {
                CompetitionCard(
                    competition = it.competitionViewState,
                    onCompetitionCardClick = { onCompetitionCardClick(it.id) },
                    onFollowButtonClick = { onFollowButtonCLick(it.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                )
            }
        }
    }
}

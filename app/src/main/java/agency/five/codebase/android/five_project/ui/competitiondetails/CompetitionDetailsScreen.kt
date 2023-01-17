package agency.five.codebase.android.five_project.ui.competitiondetails

import agency.five.codebase.android.five_project.R
import agency.five.codebase.android.five_project.ui.components.FollowButton
import agency.five.codebase.android.five_project.ui.components.TeamCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CompetitionDetailsRoute(
    viewModel: CompetitionDetailsViewModel,
    onTeamCardClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val competitionDetailsViewState: CompetitionDetailsViewState by viewModel.competitionDetailsViewState.collectAsState()
    CompetitionDetailsScreen(
        competitionDetailsViewState = competitionDetailsViewState,
        onTeamCardClick = onTeamCardClick,
        onFollowButtonCLick = viewModel::toggleFollowed,
        modifier = modifier
    )
}

@Composable
fun CompetitionDetailsScreen(
    competitionDetailsViewState: CompetitionDetailsViewState,
    onTeamCardClick: (Int) -> Unit = {},
    onFollowButtonCLick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            AsyncImage(
                model = competitionDetailsViewState.competitionCardViewState.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .height(250.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                colorResource(id = R.color.dark_blue),
                                colorResource(id = R.color.light_blue)
                            ),
                        ),
                    ),
            )
            Row(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                colorResource(id = R.color.dark_blue),
                                colorResource(id = R.color.light_blue)
                            )
                        )
                    )
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = competitionDetailsViewState.competitionCardViewState.name,
                    fontSize = 25.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                )
                FollowButton(
                    isFollowed = competitionDetailsViewState.competitionCardViewState.isFollowed,
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterVertically),
                    onFollowButtonClick = { onFollowButtonCLick(competitionDetailsViewState.id) }
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.position),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(id = R.string.team),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(id = R.string.points),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        items(competitionDetailsViewState.teamViewStates) {
            TeamCard(
                team = it,
                onTeamCardClick = { onTeamCardClick(it.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, start = 16.dp, end = 16.dp)
                    .height(70.dp)
            )
        }
    }
}


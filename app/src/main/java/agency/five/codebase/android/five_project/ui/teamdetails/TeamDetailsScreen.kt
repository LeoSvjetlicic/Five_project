package agency.five.codebase.android.five_project.ui.teamdetails

import agency.five.codebase.android.five_project.R
import agency.five.codebase.android.five_project.ui.components.MemberCard
import agency.five.codebase.android.five_project.ui.components.MemberCardViewState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TeamDetailsRoute(
    teamDetailsViewModel: TeamDetailsViewModel = viewModel(),
    modifier: Modifier = Modifier,

    ) {
    val teamDetailsViewState by teamDetailsViewModel.teamDetailsViewState.collectAsState()
    TeamDetailScreen(
        teamDetailsViewState = teamDetailsViewState,
        modifier = modifier
    )
}

@Composable
fun TeamDetailScreen(
    teamDetailsViewState: TeamDetailsViewState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            AsyncImage(
                model = teamDetailsViewState.teamCardViewState.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                colorResource(id = R.color.dark_blue),
                                colorResource(id = R.color.light_blue)
                            )
                        )
                    ),
            ) {
                Text(
                    text = teamDetailsViewState.teamCardViewState.name,
                    fontSize = 25.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.description),
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(5.dp)
                        .padding(start = 5.dp)
                        .align(Alignment.Start),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = teamDetailsViewState.teamCardViewState.description,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Start
                )
            }
        }
        item {
            Text(
                text = stringResource(id = R.string.members),
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
        }
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(500.dp)
            ) {
                items(teamDetailsViewState.memberViewStates) { member ->
                    MemberCard(
                        member = MemberCardViewState(
                            id = member.id,
                            name = member.name,
                            imageUrl = member.imageUrl,
                            number = member.number,
                            isRightFooted = member.isRightFooted
                        ),
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .height(100.dp),
                    )
                }
            }
        }
    }
}

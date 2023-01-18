package agency.five.codebase.android.five_project.ui.components

import agency.five.codebase.android.five_project.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class CompetitionCardViewState(
    val imageUrl: String,
    val isFollowed: Boolean,
    val name: String
)

@Composable
fun CompetitionCard(
    competition: CompetitionCardViewState,
    onCompetitionCardClick: () -> Unit,
    onFollowButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .clickable { onCompetitionCardClick() }
            .height(70.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp)),
        border = BorderStroke(
            2.dp,
            brush = Brush.horizontalGradient(
                colors = listOf(
                    colorResource(id = R.color.dark_blue),
                    colorResource(id = R.color.light_blue)
                )
            )
        )
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = competition.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(3.dp)),
                contentScale = ContentScale.Fit,
            )
            Text(
                modifier = Modifier,
                text = competition.name,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
            )
            FollowButton(
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 10.dp),
                isFollowed = competition.isFollowed,
                onFollowButtonClick = onFollowButtonClick
            )
        }
    }
}

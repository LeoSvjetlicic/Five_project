package agency.five.codebase.android.five_project.ui.components

import agency.five.codebase.android.five_project.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class TeamCardViewState(
    val id: Int,
    val position: Int,
    val imageUrl: String?,
    val description: String = "",
    val name: String,
    val points: Int = 0
)

@Composable
fun TeamCard(
    team: TeamCardViewState,
    onTeamCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .padding(4.dp)
            .clickable { onTeamCardClick() }
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
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.width(40.dp),
                text = team.position.toString() + ".",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            AsyncImage(
                model = team.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(50.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.FillHeight,
            )
            Text(
                modifier = Modifier.width(200.dp),
                text = team.name,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.width(40.dp),
                text = team.points.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

package agency.five.codebase.android.five_project.ui.components

import agency.five.codebase.android.five_project.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class MemberCardViewState(
    val id: Int,
    val name: String,
    val number: Int,
    val imageUrl: String?,
    val isRightFooted: Boolean
)

@Composable
fun MemberCard(
    member: MemberCardViewState,
    modifier: Modifier,
    onImageClick: () -> Unit
) {
    Card(
        modifier = modifier,
        border = BorderStroke(
            4.dp,
            brush = Brush.horizontalGradient(
                colors = listOf(
                    colorResource(id = R.color.dark_blue),
                    colorResource(id = R.color.light_blue)
                )
            )
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.End
        ) {
            AsyncImage(
                model = member.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onImageClick() }
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxHeight()
                    .width(90.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                Text(
                    text = member.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
                NumberedKit(
                    number = member.number,
                    modifier = Modifier.size(45.dp)
                )
                Image(
                    painter = painterResource(
                        id = if (member.isRightFooted) {
                            R.drawable.right_footed
                        } else R.drawable.left_footed
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}
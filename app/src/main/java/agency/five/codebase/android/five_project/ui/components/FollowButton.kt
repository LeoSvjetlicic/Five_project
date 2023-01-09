package agency.five.codebase.android.five_project.ui.components

import agency.five.codebase.android.five_project.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FollowButton(
    modifier: Modifier = Modifier,
    isFollowed: Boolean,
    onFollowButtonClick: () -> Unit,
) {
    Image(
        painter = painterResource(
            id = if (isFollowed) {
                R.drawable.follow_button_filled
            } else R.drawable.follow_button_empty
        ),
        contentDescription = null,
        modifier = modifier
            .padding(5.dp)
            .clickable { onFollowButtonClick() }
    )
}

@Preview
@Composable
fun FollowButtonPreview() {
    var isFollowed = remember { mutableStateOf(false) }
    FollowButton(modifier = Modifier.size(50.dp), isFollowed = isFollowed.value) {
        isFollowed.value = isFollowed.value.not()
    }
}

package agency.five.codebase.android.five_project.ui.components

import agency.five.codebase.android.five_project.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberedKit(number: Int, modifier: Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.kit),
            contentDescription = null,
            modifier.fillMaxSize()
        )
        Text(
            text = number.toString(),
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center),
            color = Color.White
        )
    }
}

@Preview
@Composable
fun NumberedKitPreview(){
    NumberedKit(number = 77, modifier = Modifier.size(50.dp))
}

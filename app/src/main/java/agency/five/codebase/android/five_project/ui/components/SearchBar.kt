package agency.five.codebase.android.five_project.ui.components

import agency.five.codebase.android.five_project.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    modifier: Modifier,
    onSearchButtonClick: (String) -> Unit
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                modifier = Modifier.padding(end = 10.dp),
                value = query,
                onValueChange = {
                    query = it
                },
                label = { Text(text = "Search") },
                shape = CircleShape,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = colorResource(id = R.color.light_blue),
                    unfocusedIndicatorColor = colorResource(id = R.color.light_blue),
                    focusedLabelColor = colorResource(id = R.color.light_blue),
                    unfocusedLabelColor = colorResource(id = R.color.light_blue),
                    cursorColor = colorResource(id = R.color.light_blue)
                )
            )
            Button(
                onClick = { onSearchButtonClick(query.text) },
                shape = CircleShape,
                modifier = Modifier.padding(top = 7.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.light_blue)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = null,
                )

            }
        }
    }
}

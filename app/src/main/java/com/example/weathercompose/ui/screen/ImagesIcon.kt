package com.example.weathercompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.weathercompose.R
import com.example.weathercompose.ui.theme.Pink80

@Composable
fun WeatherSelectIcon(icon: String) {
    val svgLink = "https://icons.qweather.com/assets/icons/${icon}.svg"
    val context = LocalContext.current
    val imageLoader = ImageRequest.Builder(context)
        .data(svgLink)
        .crossfade(true)
        .decoderFactory(SvgDecoder.Factory())
        .build()
    AsyncImage(
        model = imageLoader,
        contentDescription = null,
        modifier = Modifier
            .size(35.dp)
            .padding(top = 3.dp, end = 8.dp),
        filterQuality = DefaultFilterQuality,
        colorFilter = ColorFilter.tint(Pink80),
    )
}

@Composable
fun DialogSearch(
    dialogState: MutableState<Boolean>,
    onSubmit:(String)-> Unit
) {
    val dialogText = remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = {
            dialogState.value = false
        },
        confirmButton = {
            TextButton(onClick = {
                onSubmit(dialogText.value)
                dialogState.value = false
            }) {
                Text(text = stringResource(id = R.string.OK))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dialogState.value = false
            }) {
                Text(text = stringResource(id = R.string.Cance))
            }
        },
        title = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.input_city_name))
                TextField(value = dialogText.value, onValueChange = {
                    dialogText.value = it
                })
            }
        }
    )
}
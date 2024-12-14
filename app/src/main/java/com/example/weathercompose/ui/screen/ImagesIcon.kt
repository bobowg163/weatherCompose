package com.example.weathercompose.ui.screen

import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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


/**
 * 动画图标
 *
 * 这块添加了无限重复动画，如果是晴天，也就是小太阳则旋转，如果不是则进行左右平移操作
 */

@Composable
fun WeatherSelectIcon(icon: String, animation: Boolean = false) {

    //动画
    val infiniteTransition = rememberInfiniteTransition(label = "动画")
    val modifier = if (icon == "100") {
        val rotate by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(3500, easing = LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "动画"
        )
        Modifier.rotate(rotate)
    } else {
        val offsetX by infiniteTransition.animateValue(
            initialValue = (-35).dp, // 初始值
            targetValue = 0.dp, // 目标值
            typeConverter = TwoWayConverter(
                { AnimationVector1D(it.value) },
                { it.value.dp }), // 类型转换
            animationSpec = infiniteRepeatable(  // 动画规格!!!
                animation = tween(3500, easing = LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "动画"
        )
        Modifier.offset(x = offsetX)
    }

    val svgLink = "https://icons.qweather.com/assets/icons/${icon}.svg"
    val context = LocalContext.current
    val imageLoader = ImageRequest.Builder(context)
        .data(svgLink)
        .crossfade(true)
        .decoderFactory(SvgDecoder.Factory())
        .build()
    when (animation) {
        true -> {
            AsyncImage(
                model = imageLoader,
                contentDescription = null,
                modifier = modifier
                    .size(35.dp)
                    .padding(top = 3.dp, end = 8.dp),
                filterQuality = DefaultFilterQuality,
                colorFilter = ColorFilter.tint(Pink80),
            )

        }

        false -> {
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
    }
}

@Composable
fun DialogSearch(
    dialogState: MutableState<Boolean>,
    onSubmit: (String) -> Unit
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



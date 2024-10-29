package com.example.weathercompose.ui.widget

import android.content.Context
import android.widget.ImageButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.Button
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.weathercompose.MainActivity
import com.example.weathercompose.R
import com.example.weathercompose.data.getNowData
import com.example.weathercompose.data.now
import java.sql.Ref


class WeatherWidgetReceiver : GlanceAppWidgetReceiver() {

    // Let MyAppWidgetReceiver know which GlanceAppWidget to use
    override val glanceAppWidget: GlanceAppWidget = WeatherWidget()
}

val countKey = intPreferencesKey("count")

class WeatherWidget : GlanceAppWidget() {
    override val sizeMode: SizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                WeatherNow()
            }
        }
    }


    @Composable
    fun WeatherNow(
    ) {
        val nowList = remember {
            mutableStateOf(now("", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
        }
        val context = LocalContext.current
        getNowData(
            context = context,
            now = nowList,
        )
        Column(
            modifier = GlanceModifier.fillMaxSize().background(ImageProvider(R.drawable.night_bg))
                .clickable(
                    onClick = actionStartActivity<MainActivity>()
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = GlanceModifier.fillMaxWidth().padding(start = 8.dp, top = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "三亚",
                        style = TextStyle(
                            color = ColorProvider(Color.White),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "${nowList.value.temp}°C",
                        style = TextStyle(
                            color = ColorProvider(Color.White),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Text(
                    text = nowList.value.text,
                    style = TextStyle(
                        color = ColorProvider(Color.White),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Image(
                    contentDescription = context.getString(R.string.app_name),
                    provider = ImageProvider(R.drawable.ic_launcher_foreground)
                )
                Button(text = "刷新", onClick = {
                    actionRunCallback<RefreshAction>()
                })
            }
        }
    }

}


class RefreshAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { prsf ->
            prsf.clear()
        }
        WeatherWidget().update(context, glanceId)

    }
}

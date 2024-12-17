package com.example.weathercompose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathercompose.data.DailyIndex
import com.example.weathercompose.data.daily
import com.example.weathercompose.data.hourly
import com.example.weathercompose.ui.theme.BlueLight
import com.example.weathercompose.uitl.toTime


@Composable
fun HorlyList(item: hourly) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp),
        colors = CardDefaults.cardColors(containerColor = BlueLight),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(start = 8.dp, top = 5.dp, bottom = 5.dp),
            ) {
                Text(text = toTime(item.fxTime), color = Color.White)
                Text(
                    text = "${item.text} ${item.windDir} ${item.windScale}级 ",
                    color = Color.White
                )
                Text(text = "风速：${item.windSpeed} 公里/小时", color = Color.White)
            }

            Text(text = "${item.temp}°C", color = Color.White, style = TextStyle(fontSize = 25.sp))
            WeatherSelectIcon(icon = item.icon)

        }
    }
}


@Composable
fun DailyItem(item: daily) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp),
        colors = CardDefaults.cardColors(containerColor = BlueLight),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 5.dp, bottom = 5.dp)
                    .size(height = 168.dp, width = 190.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = item.fxDate, color = Color.White, fontSize = 15.sp)
                Text(
                    text = "白天:${item.textDay}",
                    color = Color.White,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "日出：${item.sunrise} 日落：${item.sunset}",
                    color = Color.White,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "月升：${item.moonrise} 月落：${item.moonset.ifEmpty { "未知" }}",
                    color = Color.White,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "夜晚:${item.textNight} ${item.moonPhase}",
                    color = Color.White,
                    fontSize = 15.sp
                )
            }

            Text(
                text = "${item.tempMin}°C-${item.tempMax}°C",
                color = Color.White,
                style = TextStyle(fontSize = 20.sp)
            )
            Column {
                WeatherSelectIcon(icon = item.iconDay)
                WeatherSelectIcon(icon = item.iconNight)
            }

        }
    }
}


@Composable
fun getDailyIndexItem(item: DailyIndex) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp),
        colors = CardDefaults.cardColors(containerColor = BlueLight),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 5.dp, bottom = 5.dp)
                    .size(height = 168.dp, width = 190.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = item.text, color = Color.White, fontSize = 15.sp)
                Text(
                    text = "白天:${item.type}",
                    color = Color.White,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "日出：${item.date} 日落：${item.name}",
                    color = Color.White,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "月升：${item.level} 月落：${item.category}",
                    color = Color.White,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}
package com.example.weathercompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathercompose.R
import com.example.weathercompose.data.DailyIndex
import com.example.weathercompose.data.daily
import com.example.weathercompose.data.hourly
import com.example.weathercompose.data.now
import com.example.weathercompose.ui.theme.BlueLight
import com.example.weathercompose.ui.theme.WeatherComposeTheme
import com.example.weathercompose.uitl.ImageCard
import com.example.weathercompose.uitl.textColor
import com.example.weathercompose.uitl.toTime
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@Composable
fun MainCard(
    nowList: MutableState<now>,
    onClickSync: () -> Unit,
    onClickSearch: () -> Unit,
    title: String = ""
) {
    Column(
        modifier = Modifier
            .padding(5.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
                .background(color = BlueLight, shape = RoundedCornerShape(10.dp)),
            colors = CardDefaults.cardColors(containerColor = BlueLight),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Box(modifier = Modifier.height(320.dp)) {
                if (nowList.value.text != "") {
                    ImageCard(text = nowList.value.icon)
                }
                //拉渐变
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 300f//数据越大黑色越少
                            )
                        )
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                                text = toTime(nowList.value.obsTime),
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.White
                            )

                            WeatherSelectIcon(nowList.value.icon, animation = true)
                        }
                        Text(
                            text = title.ifEmpty { "三亚" },
                            style = TextStyle(
                                fontSize = 25.sp, fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )
                        Text(
                            text = "${nowList.value.temp}°C",
                            style = TextStyle(fontSize = 65.sp, fontWeight = FontWeight.Bold),
                            color = Color.White
                        )

                        Text(
                            text = nowList.value.text,
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                            color = if (nowList.value.text.contains("雨")) textColor() else Color.White
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "风速:${nowList.value.windSpeed} 公里/小时",
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                                color = Color.White,
                                modifier = Modifier.padding(top = 5.dp, start = 15.dp)
                            )

                            Text(
                                text = "湿度:${nowList.value.humidity}%，能见度:${nowList.value.vis}公里",
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                                color = Color.White,
                                modifier = Modifier.padding(top = 5.dp, start = 15.dp)
                            )

                            Text(
                                text = "降水量:${nowList.value.precip} 毫米，压强:${nowList.value.pressure}百帕",
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                                color = Color.White,
                                modifier = Modifier.padding(top = 5.dp, start = 15.dp)
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(onClick = {
                                onClickSearch.invoke()
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = stringResource(id = R.string.search),
                                    tint = Color.White
                                )

                            }

                            Text(
                                modifier = Modifier.padding(top = 8.dp),
                                text = "体感温度:${nowList.value.feelsLike}°C 风向：${nowList.value.windDir} ${nowList.value.windScale}级",
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                                color = Color.White
                            )

                            IconButton(onClick = { onClickSync.invoke() }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_location),
                                    contentDescription = stringResource(id = R.string.refresh),
                                    tint = Color.White
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TabLayout(
    getDailyIndexList: MutableState<List<DailyIndex>>,
    daysList: MutableState<List<daily>>,
    hourlyList: MutableState<List<hourly>>,
) {
    val tabList = listOf( "24小时", "7天","天气指数")
    val pageState = rememberPagerState()
    val tabIndex = pageState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
            .clip(RoundedCornerShape(5.dp))
            .alpha(0.8f),
    ) {
        PrimaryTabRow(
            modifier = Modifier
                .fillMaxWidth(),
            selectedTabIndex = tabIndex,
            containerColor = BlueLight,
            contentColor = Color.White,
            divider = {
                HorizontalDivider()
            }
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            pageState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = text, color = Color.White) },
                )
            }
        }
        HorizontalPager(
            count = tabList.size,
            state = pageState,
            modifier = Modifier.weight(1.0f)
        ) { index ->
            when (index) {
                0 -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(
                            hourlyList.value
                        ) { _, item ->
                            HorlyList(item)
                        }
                    }
                }

                1 -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(
                            daysList.value
                        ) { _, item ->
                            DailyItem(item)
                        }
                    }
                }

                2 -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(
                            getDailyIndexList.value
                        ) { _, item ->
                            getDailyIndexItem(item)
                        }
                    }
                }

                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(
                            hourlyList.value
                        ) { _, item ->
                            HorlyList(item)
                        }
                    }
                }
            }

        }
    }
}


@Preview
@Composable
private fun MainCardPreview() {
    WeatherComposeTheme {
        val now = remember {
            mutableStateOf(
                now(
                    "2024-05-12 12:00",
                    "19",
                    "25",
                    "100",
                    "晴", "", "", "", "", "", "",
                    "", "", "", ""
                )
            )
        }
        MainCard(nowList = now, onClickSync = {}, onClickSearch = {})
    }
}

@Preview
@Composable
private fun TabLayoutPreview() {
    WeatherComposeTheme {
        val daysList = remember {
            mutableStateOf(listOf<daily>())
        }
        val hourlyList = remember {
            mutableStateOf(
                listOf(
                    hourly(
                        "2014-12-14",
                        "23",
                        "100",
                        "晴",
                        "25",
                        "sade",
                        "aekae",
                        "15"
                    ),
                    hourly(
                        "2014-12-14",
                        "23",
                        "100",
                        "晴",
                        "25",
                        "sade",
                        "aekae",
                        "15"
                    ),
                    hourly(
                        "2014-12-14",
                        "23",
                        "100",
                        "晴",
                        "25",
                        "sade",
                        "aekae",
                        "15"
                    ),
                    hourly(
                        "2014-12-14",
                        "23",
                        "100",
                        "晴",
                        "25",
                        "sade",
                        "aekae",
                        "15"
                    ),
                )
            )
        }
        val getDailyIndexList = remember {
            mutableStateOf(
                listOf(
                    DailyIndex(
                        "较不宜",
                        "2014-12-15",
                        "运动指数",
                        "天气较好，但考虑天气寒冷，推荐您进行室内运动，户外运动时请注意保暖并做好准备活动",
                        "3",
                        "1"
                    ),
                    DailyIndex(
                        "较不宜",
                        "2014-12-15",
                        "运动指数",
                        "天气较好，但考虑天气寒冷，推荐您进行室内运动，户外运动时请注意保暖并做好准备活动",
                        "3",
                        "2"
                    )
                )
            )
        }
        TabLayout(
            getDailyIndexList = getDailyIndexList,
            daysList = daysList,
            hourlyList = hourlyList
        )
    }
}
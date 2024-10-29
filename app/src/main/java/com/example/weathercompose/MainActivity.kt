package com.example.weathercompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.weathercompose.data.daily
import com.example.weathercompose.data.get24HourlyData
import com.example.weathercompose.data.get7DaysData
import com.example.weathercompose.data.getCityData
import com.example.weathercompose.data.getNowData
import com.example.weathercompose.data.hourly
import com.example.weathercompose.data.location
import com.example.weathercompose.data.now
import com.example.weathercompose.ui.screen.DialogSearch
import com.example.weathercompose.ui.screen.MainCard
import com.example.weathercompose.ui.screen.TabLayout
import com.example.weathercompose.ui.theme.WeatherComposeTheme
import com.example.weathercompose.uitl.ImageReslut
import com.example.weathercompose.uitl.toText
import getCurrentLocation

class MainActivity : ComponentActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private var isLocationPermissionGranted = false
    private var isStoragePermissionGranted = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(
                    android.Manifest.permission.ACCESS_FINE_LOCATION, true
                ) -> {
                    isLocationPermissionGranted =
                        permissions[android.Manifest.permission.ACCESS_FINE_LOCATION]
                            ?: isLocationPermissionGranted
                }

                permissions.getOrDefault(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION, true
                ) -> {
                    isStoragePermissionGranted =
                        permissions[android.Manifest.permission.READ_EXTERNAL_STORAGE]
                            ?: isStoragePermissionGranted
                }

                else -> {
                    Toast.makeText(
                        this,
                        "你没有开启地理共享权限，请开启地理权限！",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        setContent {

            WeatherComposeTheme {
                val daysList = remember {
                    mutableStateOf(listOf<daily>())
                }
                val hourlyList = remember {
                    mutableStateOf(listOf<hourly>())
                }
                val nowList = remember {
                    mutableStateOf(now("", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
                }
                val citylist = remember {
                    mutableStateOf(location("", "", "", ""))
                }
                val dialogState = remember { mutableStateOf(false) }

                if (dialogState.value) {
                    DialogSearch(dialogState, onSubmit = {
                        getCityData(it, this, citylist)
                    })
                } else {
                    if (citylist.value.id.isNotEmpty()) {
                        getNowData(
                            context = this, now = nowList, id = citylist.value.id
                        )
                        get24HourlyData(this, hourlyList, id = citylist.value.id)
                        get7DaysData(
                            context = this, daysList = daysList, id = citylist.value.id
                        )
                    } else {
                        get24HourlyData(this, hourlyList)
                        get7DaysData(this, daysList)
                        getNowData(context = this, now = nowList)
                    }
                }
                Image(
                    painter = painterResource(id = ImageReslut()),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainCard(nowList, onClickSync = {
                        val location = getCurrentLocation(this@MainActivity)
                        getCityData(location.toText(), this@MainActivity, citylist)
                        getNowData(
                            context = this@MainActivity,
                            now = nowList,
                            id = location.toText()
                        )
                        get7DaysData(
                            context = this@MainActivity,
                            daysList = daysList,
                            id = location.toText()
                        )
                        get24HourlyData(
                            context = this@MainActivity,
                            daysList = hourlyList,
                            id = location.toText()
                        )
                    }, onClickSearch = {
                        dialogState.value = true
                    }, title = citylist.value.name
                    )
                    TabLayout(daysList, hourlyList)
                }
            }
        }
    }
}






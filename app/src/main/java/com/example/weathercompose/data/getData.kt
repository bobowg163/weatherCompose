package com.example.weathercompose.data

import android.content.Context
import android.util.Log

import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weathercompose.BuildConfig
import org.json.JSONObject


const val API_KEY = BuildConfig.API_KEY

fun getNowData(context: Context, now: MutableState<now>, id: String = "101310218") {
    if (id == "Unknown location") {
        val url = "https://devapi.qweather.com/v7/weather/now?location=101310218&key=${API_KEY}"
        val queue = Volley.newRequestQueue(context)
        val sRequest = StringRequest(Request.Method.GET, url, { response ->
            val list = getWeather(response)
            now.value = list
        }, {
            Log.d("mylog", "VolleyError:$it")
        })
        queue.add(sRequest)
    } else {
        val url = "https://devapi.qweather.com/v7/weather/now?location=${id}" + "&key=${API_KEY}"
        val queue = Volley.newRequestQueue(context)
        val sRequest = StringRequest(Request.Method.GET, url, { response ->
            val list = getWeather(response)
            now.value = list
        }, {
            Log.d("mylog", "VolleyError:$it")
        })
        queue.add(sRequest)
    }

}

fun getWeather(response: String): now {
    val mainObject = JSONObject(response)
    val item = mainObject.getJSONObject("now")
    return now(
        obsTime = item.getString("obsTime"),
        temp = item.getString("temp"),
        feelsLike = item.getString("feelsLike"),
        icon = item.getString("icon"),
        text = item.getString("text"),
        wind360 = item.getString("wind360"),
        windDir = item.getString("windDir"),
        windScale = item.getString("windScale"),
        windSpeed = item.getString("windSpeed"),
        humidity = item.getString("humidity"),
        precip = item.getString("precip"),
        pressure = item.getString("pressure"),
        vis = item.getString("vis"),
        cloud = item.getString("cloud"),
        dew = item.getString("dew")
    )


}

fun get7DaysData(context: Context, daysList: MutableState<List<daily>>, id: String = "101310218") {
    if (id == "Unknown location") {
        val url = "https://devapi.qweather.com/v7/weather/7d?location=101310218&key=$API_KEY"
        val queue = Volley.newRequestQueue(context)
        val sRequest = StringRequest(Request.Method.GET, url, { response ->
            val list = getWeatherBy7Days(response)
            daysList.value = list
        }, {
            Log.d("mylog", "VolleyError:$it")
        })
        queue.add(sRequest)
    } else {
        val url = "https://devapi.qweather.com/v7/weather/7d?location=${id}" + "&key=$API_KEY"
        val queue = Volley.newRequestQueue(context)
        val sRequest = StringRequest(Request.Method.GET, url, { response ->
            val list = getWeatherBy7Days(response)
            daysList.value = list
        }, {
            Log.d("mylog", "VolleyError:$it")
        })
        queue.add(sRequest)
    }


}


fun getWeatherBy7Days(response: String): List<daily> {
    if (response.isEmpty()) {
        return listOf()
    } else {
        val list = ArrayList<daily>()
        val mainObject = JSONObject(response)
        val days = mainObject.getJSONArray("daily")
        for (i in 0 until days.length()) {
            val item = days[i] as JSONObject
            list.add(
                daily(
                    fxDate = item.getString("fxDate"),
                    sunrise = item.getString("sunrise"),
                    sunset = item.getString("sunset"),
                    moonrise = item.getString("moonrise"),
                    moonset = item.getString("moonset"),
                    moonPhase = item.getString("moonPhase"),
                    moonPhaseIcon = item.getString("moonPhaseIcon"),
                    tempMax = item.getString("tempMax"),
                    tempMin = item.getString("tempMin"),
                    iconDay = item.getString("iconDay"),
                    textDay = item.getString("textDay"),
                    iconNight = item.getString("iconNight"),
                    textNight = item.getString("textNight"),
                    wind360Day = item.getString("wind360Day"),
                    windDirDay = item.getString("windDirDay"),
                    windScaleDay = item.getString("windScaleDay"),
                    windSpeedDay = item.getString("windSpeedDay"),
                    wind360Night = item.getString("wind360Night"),
                    windDirNight = item.getString("windDirNight"),
                    windScaleNight = item.getString("windScaleNight"),
                    windSpeedNight = item.getString("windSpeedNight"),
                    humidity = item.getString("humidity"),
                    precip = item.getString("precip"),
                    pressure = item.getString("pressure"),
                    vis = item.getString("vis"),
                    cloud = item.getString("cloud"),
                    uvIndex = item.getString("uvIndex"),
                )
            )
        }
        return list
    }
}

fun get24HourlyData(
    context: Context, daysList: MutableState<List<hourly>>, id: String = "101310218"
) {
    if (id == "Unknown location") {
        val url = "https://devapi.qweather.com/v7/weather/24h?location=101310218&key=$API_KEY"
        val queue = Volley.newRequestQueue(context)
        val sRequest = StringRequest(Request.Method.GET, url, { response ->
            val list = getWeatherBy24Hourly(response)
            daysList.value = list
        }, {
            Log.d("mylog", "VolleyError:$it")
        })
        queue.add(sRequest)
    } else {
        val url = "https://devapi.qweather.com/v7/weather/24h?location=${id}" + "&key=$API_KEY"
        val queue = Volley.newRequestQueue(context)
        val sRequest = StringRequest(Request.Method.GET, url, { response ->
            val list = getWeatherBy24Hourly(response)
            daysList.value = list
        }, {
            Log.d("mylog", "VolleyError:$it")
        })
        queue.add(sRequest)
    }
}

fun getWeatherBy24Hourly(response: String): List<hourly> {

    if (response.isEmpty()) {
        return listOf()
    } else {
        val list = ArrayList<hourly>()
        val mainObject = JSONObject(response)
        val hourlys = mainObject.getJSONArray("hourly")
        for (i in 0 until hourlys.length()) {
            val item = hourlys[i] as JSONObject
            list.add(
                hourly(
                    fxTime = item.getString("fxTime"),
                    temp = item.getString("temp"),
                    icon = item.getString("icon"),
                    text = item.getString("text"),
                    wind360 = item.getString("wind360"),
                    windDir = item.getString("windDir"),
                    windScale = item.getString("windScale"),
                    windSpeed = item.getString("windSpeed")
                )
            )
        }
        return list
    }
}

fun getCityData(city: String, context: Context, cityList: MutableState<location>) {

    if (city == "Unknown location") {
        val url = "https://geoapi.qweather.com/v2/city/lookup?location=101310218&key=$API_KEY"
        val queue = Volley.newRequestQueue(context)
        val sRequest = StringRequest(Request.Method.GET, url, { response ->
            val list = getWeatherByCity(response)
            cityList.value = list
        }, {
            Log.d("mylog", "VolleyError:$it")
        })
        queue.add(sRequest)
    } else {
        val url = "https://geoapi.qweather.com/v2/city/lookup?location=${city}" + "&key=$API_KEY"
        val queue = Volley.newRequestQueue(context)
        val sRequest = StringRequest(Request.Method.GET, url, { response ->
            val list = getWeatherByCity(response)
            cityList.value = list
        }, {
            Log.d("mylog", "VolleyError:$it")
        })
        queue.add(sRequest)
    }

}

fun getWeatherByCity(response: String): location {
    if (response.isEmpty()) return location("", "", "", "")
    val list = ArrayList<location>()
    val mainObject = JSONObject(response)
    val hourlys = mainObject.getJSONArray("location")
    for (i in 0 until hourlys.length()) {
        val item = hourlys[i] as JSONObject
        list.add(
            location(
                name = item.getString("name"),
                id = item.getString("id"),
                lat = item.getString("lat"),
                lon = item.getString("lon")
            )
        )
    }
    return list[0]
}
package com.example.weathercompose.data

import com.google.gson.annotations.SerializedName

data class now(
    @SerializedName("obsTime")
    val obsTime:String,
    @SerializedName("temp")
    val temp: String,
    @SerializedName("feelsLike")
    val feelsLike: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val text:String,
    @SerializedName("wind360")
    val wind360: String,
    @SerializedName("windDir")
    val windDir:String,
    @SerializedName("windScale")
    val windScale:String,
    @SerializedName("windSpeed")
    val windSpeed:String,
    @SerializedName("humidity")
    val humidity:String,
    @SerializedName("precip")
    val precip:String,
    @SerializedName("pressure")
    val pressure:String,
    @SerializedName("vis")
    val vis:String,
    @SerializedName("cloud")
    val cloud:String,
    @SerializedName("dew")
    val dew:String,
)
data class daily(
    @SerializedName("fxDate")
    val fxDate:String,
    @SerializedName("sunrise")
    val sunrise:String,
    @SerializedName("sunset")
    val sunset:String,
    @SerializedName("moonrise")
    val moonrise:String,
    @SerializedName("moonset")
    val moonset:String,
    @SerializedName("moonPhase")
    val moonPhase:String,
    @SerializedName("moonPhaseIcon")
    val moonPhaseIcon:String,
    @SerializedName("tempMax")
    val tempMax:String,
    @SerializedName("tempMin")
    val tempMin:String,
    @SerializedName("iconDay")
    val iconDay:String,
    @SerializedName("textDay")
    val textDay:String,
    @SerializedName("iconNight")
    val iconNight:String,
    @SerializedName("textNight")
    val textNight:String,
    @SerializedName("wind360Day")
    val wind360Day:String,
    @SerializedName("windDirDay")
    val windDirDay:String,
    @SerializedName("windScaleDay")
    val windScaleDay:String,
    @SerializedName("windSpeedDay")
    val windSpeedDay:String,
    @SerializedName("wind360Night")
    val wind360Night:String,
    @SerializedName("windDirNight")
    val windDirNight:String,
    @SerializedName("windScaleNight")
    val windScaleNight:String,
    @SerializedName("windSpeedNight")
    val windSpeedNight:String,
    @SerializedName("humidity")
    val humidity:String,
    @SerializedName("precip")
    val precip:String,
    @SerializedName("pressure")
    val pressure:String,
    @SerializedName("vis")
    val vis:String,
    @SerializedName("cloud")
    val cloud:String,
    @SerializedName("uvIndex")
    val uvIndex:String,
)

data class hourly(
    val fxTime:String,
    val temp:String,
    val icon:String,
    val text:String,
    val wind360:String,
    val windDir:String,
    val windScale:String,
    val windSpeed:String,
)

data class location(
    val name:String,
    val id:String,
    val lat:String,
    val lon:String
)
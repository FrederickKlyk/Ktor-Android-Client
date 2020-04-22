package de.klyk.ktorclient.ui.main.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.klyk.ktorclient.domain.weather.WeatherInteractor
import de.klyk.ktorclient.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class WeatherFragmentViewModel(
    private val weatherInteractor: WeatherInteractor
) : BaseViewModel() {

    /** Wetterdaten LiveData Attribute*/
    val rawResult = MutableLiveData(RAW_TEXT)
    val latText = MutableLiveData(LAT_TEXT)
    val lonText = MutableLiveData(LON_TEXT)

    val tempText = MutableLiveData(TEMP_TEXT)
    val tempMinText = MutableLiveData(TEMP_MIN_TEXT)
    val tempMaxText = MutableLiveData(TEMP_MAX_TEXT)

    val luftdruckText = MutableLiveData(LD_TEXT)
    val geschwindigkeitText = MutableLiveData(GES_TEXT)

    /**
     * Erfasse die Wetterdaten aus London und zeige sie in der View an.
     */
    fun initialize() = viewModelScope.launch {
        //Wechsele für den API Call in den IO Dispatcher
        withContext(Dispatchers.IO) {
            weatherInteractor.fetchWeatherFromLondon()
        }.fold({
            with(it) {
                rawResult.value = "$RAW_TEXT$this"
                latText.value = "$LAT_TEXT${coord.lat}"
                lonText.value = "$LON_TEXT${coord.lon}"

                tempText.value = "$TEMP_TEXT${main.temp}"
                tempMinText.value = "$TEMP_MIN_TEXT${main.temp_min}"
                tempMaxText.value = "$TEMP_MAX_TEXT${main.temp_max}"

                luftdruckText.value = "$LD_TEXT${main.pressure}"
                geschwindigkeitText.value = "$GES_TEXT${wind.speed}"
            }
        }, {
            Timber.e("Fehlermeldung: ${it.message}")
        })
    }

    companion object{
        private const val RAW_TEXT = "Raw Ergebnis: "
        private const val LAT_TEXT = "Lat: "
        private const val LON_TEXT = "Lon: "

        private const val TEMP_TEXT = "Temp: "
        private const val TEMP_MIN_TEXT = "Temp min: "
        private const val TEMP_MAX_TEXT = "Temp max: "

        private const val LD_TEXT = "Luftdruck: "
        private const val GES_TEXT = "Geschwindigkeit: "
    }
}
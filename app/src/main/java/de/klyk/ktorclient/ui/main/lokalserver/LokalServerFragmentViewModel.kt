package de.klyk.ktorclient.ui.main.lokalserver

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.klyk.ktorclient.domain.lokalserver.LokalServerInteractor
import de.klyk.ktorclient.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LokalServerFragmentViewModel constructor(
    private val lokalServerInteractor: LokalServerInteractor
) : BaseViewModel() {

    val postDataText = MutableLiveData<String>()
    val postDataResponseText = MutableLiveData<String>()

    fun sendHTTPPost() = viewModelScope.launch {
        withContext(Dispatchers.IO) { lokalServerInteractor.sendTestPost(postDataText.value ?: "") }
            .fold({
                postDataResponseText.setValue(it.response)
            }, {
                if (it.isHttpStatusCode)
                    postDataResponseText.setValue("Fehler beim Laden mit Statuscode ${it.exceptionType}")
                else
                    postDataResponseText.setValue("${it.exceptionType}\n Exception Mesage:\n ${it.message}")
            })
    }
}
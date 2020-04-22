package de.klyk.ktorclient.ui.main.websocket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.klyk.ktorclient.ui.base.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.ws
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readBytes
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class WebsocketsFragmentViewModel(
    private val websocketClient: HttpClient
) : BaseViewModel() {

    val wsChatMessage = MutableLiveData("")

    val wsMessageSend = MutableLiveData("" to ChatType.SENDER.value)
    val wsResponse = MutableLiveData("" to ChatType.RESPONDER.value)

    @FlowPreview
    fun sendChatMessage() {
        viewModelScope.launch {
            websocketClient.ws(
                host = "10.0.2.2",
                port = 8080, path = "/chat"
            ) {
                // Send text frame.
                wsChatMessage.value?.apply {
                    wsMessageSend.value = "(Fred): $this" to ChatType.SENDER.value
                    send(Frame.Text(this))
                }
                wsChatMessage.value = ""

                // Receive frame.
                incoming.consumeAsFlow().collect { frame ->
                    when (frame) {
                        is Frame.Text -> {
                            println(frame.readText())
                            wsResponse.value = frame.readText() to ChatType.RESPONDER.value
                        }
                        is Frame.Binary -> println(frame.readBytes())
                    }
                }
            }
        }
    }
}

enum class ChatType(val value: Int){
    SENDER(0), RESPONDER(1)
}
package org.openy1.trainer.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class ConnectionState {
    object Disconnected : ConnectionState()
    object Connecting : ConnectionState()
    object Connected : ConnectionState()
    object Error : ConnectionState()
}

sealed class SessionState {
    object Idle : SessionState()
    object Running : SessionState()
    object Paused : SessionState()
}

class WatchViewModel : ViewModel() {
    val connectionState: StateFlow<ConnectionState> = MutableStateFlow(ConnectionState.Disconnected)
    val sessionState: StateFlow<SessionState> = MutableStateFlow(SessionState.Idle)

    fun connect() {}
    fun disconnect() {}
    fun startSession() {}
    fun pauseSession() {}
}
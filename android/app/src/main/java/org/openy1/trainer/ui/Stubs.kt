package org.openy1.trainer.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class ConnectionState {
    DISCONNECTED,
    SCANNING,
    CONNECTING,
    CONNECTED
}

enum class SessionState {
    IDLE,
    RUNNING,
    PAUSED,
    STOPPING
}

data class WatchUiState(
    val connectionState: ConnectionState = ConnectionState.DISCONNECTED,
    val sessionState: SessionState = SessionState.IDLE
)

class WatchViewModel : ViewModel() {
    val ui: StateFlow<WatchUiState> = MutableStateFlow(WatchUiState())
    val connectionState: StateFlow<ConnectionState> = MutableStateFlow(ConnectionState.DISCONNECTED)
    val sessionState: StateFlow<SessionState> = MutableStateFlow(SessionState.IDLE)

    fun connect() {}
    fun disconnect() {}
    fun start() {}
    fun stop() {}
    fun previousMode() {}
    fun nextMode() {}
}

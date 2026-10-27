package org.openy1.trainer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WatchApp(viewModel: WatchViewModel) {
    val state by viewModel.ui.collectAsState()
    val scrollState = rememberScrollState()

    // Deep dark theme optimized for AMOLED circular watch display
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        color = Color.Black
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Keeps content inside the 466x466 circular bounds
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // App Title
                Text(
                    text = "Open Y1",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Connection Status Indicator
                val statusText = when (state.connection) {
                    ConnectionState.DISCONNECTED -> "Disconnected"
                    ConnectionState.SCANNING -> "Scanning..."
                    ConnectionState.CONNECTING -> "Connecting..."
                    ConnectionState.CONNECTED -> "Connected"
                }
                val statusColor = if (state.connection == ConnectionState.CONNECTED) Color.Green else Color.Yellow

                Text(
                    text = statusText,
                    color = statusColor,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Connect / Disconnect Button
                if (state.connection == ConnectionState.DISCONNECTED) {
                    Button(
                        onClick = { viewModel.connect() },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text("Connect", fontSize = 12.sp)
                    }
                } else {
                    Button(
                        onClick = { viewModel.disconnect() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text("Disconnect", fontSize = 12.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Mode Selector
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = { viewModel.previousMode() },
                        modifier = Modifier.size(36.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("<", color = Color.White)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = state.selectedMode.label,
                        color = Color.Cyan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedButton(
                        onClick = { viewModel.nextMode() },
                        modifier = Modifier.size(36.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(">", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Start / Stop Action Button
                if (state.session == SessionState.IDLE || state.session == SessionState.STOPPING) {
                    Button(
                        onClick = { viewModel.start() },
                        enabled = state.connection == ConnectionState.CONNECTED,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(44.dp)
                    ) {
                        Text("START", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                } else {
                    Button(
                        onClick = { viewModel.stop() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828)),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(44.dp)
                    ) {
                        Text("STOP (${state.elapsedSeconds}s)", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}

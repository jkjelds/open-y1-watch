package org.openy1.trainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import org.openy1.trainer.ui.WatchApp
import org.openy1.trainer.ui.WatchViewModel

class WatchActivity : ComponentActivity() {
    private val viewModel: WatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchApp(viewModel = viewModel)
        }
    }
}

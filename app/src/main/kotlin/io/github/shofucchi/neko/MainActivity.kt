package io.github.shofucchi.neko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import io.github.shofucchi.home.HomeAppBar
import io.github.shofucchi.home.HomeScreen
import io.github.shofucchi.neko.ui.theme.NekoTheme
import io.github.shofucchi.network.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var retrofitService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            // TODO: show loading
            retrofitService.sync()

            setContent {
                NekoTheme {
                    NekoApp(Modifier.fillMaxSize())
                }
            }
        }

    }

    @Composable
    private fun NekoApp(modifier: Modifier) {
        Scaffold(
            modifier = modifier,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            topBar = { HomeAppBar() }
        ) {
            HomeScreen(modifier = Modifier.padding(it))
        }
    }
}

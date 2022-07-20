package com.wac.readcsv.csvreader.presentation


import android.app.Activity
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wac.readcsv.csvreader.presentation.components.HomeScreen
import com.wac.readcsv.csvreader.presentation.components.SampleScreen1
import com.wac.readcsv.csvreader.presentation.ui.theme.ReadCSVTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var openedUri: Uri? = null

    @OptIn(ExperimentalStdlibApi::class)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val downloadLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { results: ActivityResult ->
                if (results.resultCode == Activity.RESULT_OK) {
                    openedUri = results.data?.data
                    Toast.makeText(
                        this,
                        openedUri.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        setContent {
            val pickedFileUriState = remember { mutableStateOf<Uri?>(null) }
            val downloadLauncherCompose =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument()) { result ->
                    pickedFileUriState.value = result
                }

            val context = LocalContext.current
            val navController = rememberNavController()

            val imgUrl = remember {
                mutableStateOf("https://support.staffbase.com/hc/en-us/article_attachments/360009197031/username.csv")
            }

            ReadCSVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "Home") {
                        composable("Home") {
                            HomeScreen(
                                Modifier,
                                imgUrl,
                                context,
                                downloadLauncher,
                                pickedFileUriState,
                                navController,
                                downloadLauncherCompose
                            )
                        }
                        composable("sample1") { SampleScreen1(Modifier) }
                    }
                }
            }
        }
    }

}


package com.wac.readcsv.csvreader.presentation.components


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.wac.readcsv.csvreader.presentation.MainActivityViewModel
import com.wac.readcsv.util.const.CheckPemission
import com.wac.readcsv.util.download


@Composable
fun HomeScreen(
    modifier: Modifier,
    imgUrl: MutableState<String>,
    context: Context,
    downloadLauncher: ActivityResultLauncher<Intent>,
    pickedFileUriState: MutableState<Uri?>,
    navController: NavHostController,
    downloadLauncherCompose: ManagedActivityResultLauncher<Array<String>, Uri?>
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        val viewModel: MainActivityViewModel = hiltViewModel()

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(40.dp),
            onClick = {

                downloadFile(imgUrl.value,context)
            }) {
            Text(text = "Download File")
        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(40.dp),
            onClick = {
                chooseFile(context, downloadLauncher)
            }) {
            Text(text = "Open File")
        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(40.dp),
            onClick = {

                chooseFileCompose(context, downloadLauncherCompose)

            }) {
            Text(text = "Open File Compose")
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(40.dp),
            onClick = {
                navController.navigate("sample1")
            }) {
            Text(text = "Next Page")
        }

        pickedFileUriState.value?.let {

            if (it.toString().contains("jpg")||it.toString().contains("documents/document/image")) {
                Image(
                    painter = rememberAsyncImagePainter(model = it),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.CenterHorizontally)

                )
            } else {
                viewModel.saveData(it)
            }
        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(40.dp),
            onClick = {
                viewModel.getData()
            }) {
            Text(text = "View Details")
        }
        viewModel.details.value.details.let {
            LazyColumn {
                items(it) { item ->
                    Row(modifier = Modifier) {
                        Text(text = item.Industry_name_NZSIOC)
                        Text(text = "-" + item.Year)
                        Text(text = "-" + item.Variable_category)
                        Text(text = "-" + item.Variable_code)
                    }
                }
            }
        }
    }
}

fun chooseFile(
    context: Context,
    launcher: ActivityResultLauncher<Intent>
) {
    Log.i("Tag", "chooseFile")

    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
        if (CheckPemission.checkPermissionForReadWrite(context)) {
            openIntentChooser(launcher)
        } else {
            CheckPemission.requestPermissionForReadWrite(context)
        }

    } else {

        if (CheckPemission.checkPermissionForAML(context)) {
            openIntentChooser(launcher)
        } else {
            Log.i("Tag", "else chooseFile")

            CheckPemission.requestPermissionForAML(context)
        }


    }


}

fun chooseFileCompose(
    context: Context,
    launcher: ManagedActivityResultLauncher<Array<String>, Uri?>
) {
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
        if (CheckPemission.checkPermissionForReadWrite(context)) {
            launcher.launch(arrayOf("*/*"))
        } else {
            CheckPemission.requestPermissionForReadWrite(context)
        }


    } else {

        if (CheckPemission.checkPermissionForAML(context)) {
            launcher.launch(arrayOf("*/*"))
        } else {
            Log.i("Tag", "else chooseFile")

            CheckPemission.requestPermissionForAML(context)
        }


    }




}


fun openIntentChooser(launcher: ActivityResultLauncher<Intent>) {
    val intent = Intent(Intent.ACTION_GET_CONTENT)
    intent.type = "*/*"
    launcher.launch(intent)
}

private fun downloadFile(uRl: String, context: Context) {
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
        if (CheckPemission.checkPermissionForReadWrite(context)) {
            context.download(uRl)
        } else {
            CheckPemission.requestPermissionForReadWrite(context)
        }


    } else {
        context.download(uRl)
    }

}
//
//private fun download(uRl: String, mgr: DownloadManager, context: Context) {
//
//    val direct = File(
//        Environment.getExternalStorageDirectory()
//            .toString() + "/dhaval_files"
//    )
//
//    if (!direct.exists()) {
//        direct.mkdirs()
//    }
//
//    val downloadUri = Uri.parse(uRl)
//    val request = DownloadManager.Request(
//        downloadUri
//    )
//    request.setAllowedNetworkTypes(
//        DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
//    )
//        .setAllowedOverRoaming(false).setTitle("Demo")
//        .setDescription("Something useful. No, really.")
//        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "testFolder/doc.csv")
//
//    Toast.makeText(
//
//        context,
//        "Download successfully to ${downloadUri.path}",
//        Toast.LENGTH_LONG
//    ).show()
//
//    mgr.enqueue(request)
//}
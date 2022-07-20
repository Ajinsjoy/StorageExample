package com.wac.readcsv.util

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.wac.readcsv.csvreader.domain.data.Details
import io.blackmo18.kotlin.grass.dsl.grass
import java.io.InputStream

@OptIn(ExperimentalStdlibApi::class)
fun Context.convertCsvToDataObject(csvUri: Uri): List<Details>? {
    val inputStream: InputStream? = contentResolver.openInputStream(csvUri)

    val csvContents = inputStream?.let { dataStream ->
        csvReader().readAllWithHeader(
            dataStream
        )
    }

    val dataClasses = csvContents?.let { content ->
        grass<Details>().harvest(
            content
        )
    }
    return dataClasses
}


 fun Context.download(uRl: String) {
     val mgr = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//    val direct = File(
//        Environment.getExternalStorageDirectory()
//            .toString() + "/dhaval_files"
//    )
//
//    if (!direct.exists()) {
//        direct.mkdirs()
//    }

    val downloadUri = Uri.parse(uRl)
    val request = DownloadManager.Request(
        downloadUri
    )
    request.setAllowedNetworkTypes(
        DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
    )
        .setAllowedOverRoaming(false).setTitle("Demo")
        .setDescription("Something useful. No, really.")
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "testFolder/doc.csv")

    Toast.makeText(

        this,
        "Download successfully to ${downloadUri.path}",
        Toast.LENGTH_LONG
    ).show()

    mgr.enqueue(request)
}
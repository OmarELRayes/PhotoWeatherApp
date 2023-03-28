package com.example.photoweather.common

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File

object FileHandler {
    fun createImageFile(): File? {
        val timeStamp = System.currentTimeMillis()
        val imageFileName = "NAME_$timeStamp"
        val storageDir: File =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir, /* directory */
        )
    }

    fun getUri(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "com.example.photoweather.provider",
            file,
        )
    }
}

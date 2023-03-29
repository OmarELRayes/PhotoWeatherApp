package com.example.photoweather.common.extensions

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.photoweather.common.FileHandler
import kotlinx.coroutines.launch

private fun getBitmapFromView(view: View): Bitmap {
    val bitmap = Bitmap.createBitmap(
        view.width,
        view.height,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}

fun Fragment.shareImage(view: View) {
    lifecycleScope.launch {
        val bitmap = getBitmapFromView(view)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        shareIntent.putExtra(Intent.EXTRA_STREAM, FileHandler.getUri(requireContext(), bitmap))
        shareIntent.type = "image/png"
        startActivity(Intent.createChooser(shareIntent, "Share image"))
    }
}

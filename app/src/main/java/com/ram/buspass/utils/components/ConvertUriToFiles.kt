package com.ram.buspass.utils.components

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

object ConvertUriToFiles {


    fun convertUriToFile(context: Context, uri: Uri): File {

        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "temp_image.jpg")

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                val buffer = ByteArray(4 * 1024) // Adjust buffer size as needed
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
        }

        return file
    }
}
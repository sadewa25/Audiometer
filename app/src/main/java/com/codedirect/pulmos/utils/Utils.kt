package com.codedirect.pulmos.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.multidex.BuildConfig
import com.afollestad.materialdialogs.MaterialDialog
import com.codedirect.pulmos.R
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    fun changeFragment(fragmentManager: FragmentManager?, id: Int, frameDestination: Fragment) {
        fragmentManager?.beginTransaction()?.replace(id, frameDestination)?.commit()
    }

    fun changeFragmentBack(fragmentManager: FragmentManager?, id: Int, frameDestination: Fragment) {
        fragmentManager?.beginTransaction()?.replace(id, frameDestination)?.addToBackStack("")
            ?.commit()
    }

    fun toast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun changeToActivity(context: Context?, destinationClass: AppCompatActivity) {
        context?.startActivity(Intent(context, destinationClass::class.java))
    }

    fun showToolbar(tvTitle: TextView, text: String) {
        tvTitle.text = text
    }

    fun showLoading(loading: ProgressBar) {
        loading.visibility = View.VISIBLE
    }

    fun hideLoading(loading: ProgressBar) {
        loading.visibility = View.GONE
    }

    fun getFileName(idUser: String, status: Int): String {
        return "${Calendar.getInstance()
            .timeInMillis}_${idUser}_$status.jpg"
    }

    fun formDialog(
        context: Context,
        title: String,
        message: String,
        listenerPositive: (MaterialDialog) -> Unit
    ) {
        MaterialDialog(context).show {
            title(text = title)
            message(text = message)
            positiveButton(text = context.resources?.getString(R.string.yes_)) {
                listenerPositive(it)
            }
            negativeButton(text = context.resources?.getString(R.string.no_)) {
                it.dismiss()
            }
        }
    }

    fun saveImage(myBitmap: Bitmap, context: Context?, nameFile: String): String {
        val imagePath = ""
        val m = context?.packageManager
        var s = context?.packageName
        var p: PackageInfo? = null
        try {
            p = m?.getPackageInfo(s, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        s = p?.applicationInfo?.dataDir
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val wallpaperDirectory = File("${Environment.getExternalStorageDirectory()}$s")
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }

        try {
            var f = File(wallpaperDirectory, nameFile)
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(
                context,
                arrayOf(f.path),
                arrayOf("image/jpeg"), null
            )
            fo.close()
            f = File(f.absolutePath)
            return f.absolutePath
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return imagePath
    }

    fun getMultipartBody(filePath: String): MultipartBody.Part {
        val file = File(filePath)
        val mFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("file", file.name, mFile)
    }

    fun getRequestBody(filePath: String): RequestBody {
        val file = File(filePath)
        return RequestBody.create("text/plain".toMediaTypeOrNull(), file.name)
    }

    fun getDateNow(): String {
        val cal = Calendar.getInstance()
        val date = cal.time
        val dateFormats = SimpleDateFormat("yyyy-MM-dd")
        return dateFormats.format(date)
    }

    fun getDateTimeNow(): String {
        val today = Date()
        val dateFormats = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormats.format(today)
    }

    fun getVersiAplication(): String {
        return BuildConfig.VERSION_CODE.toString()
    }

    fun changeToActivityWithBundle(
        context: Context?,
        destinationClass: AppCompatActivity,
        bundle: Bundle
    ) {
        val intent = Intent(context, destinationClass::class.java)
        intent.putExtras(bundle)
        context?.startActivity(intent)
    }
}
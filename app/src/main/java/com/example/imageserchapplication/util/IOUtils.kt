package com.example.imageserchapplication.util

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.provider.Settings
import android.view.WindowManager.BadTokenException
import com.example.imageserchapplication.R

class IOUtils(context: Context?) {
    companion object {
        @JvmStatic
        fun getProgessDialog(context: Context?): ProgressDialog {
            val dialog = ProgressDialog(context)
            try {
                dialog.show()
            } catch (e: BadTokenException) {
            }
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(R.layout.progress_indicator)
            // dialog.setMessage(Message);
            return dialog
        }

        @JvmStatic
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        @JvmStatic
        fun setNetworkAlertForActivity(context: Context) {
            val device_language = "1"
            val builder = AlertDialog.Builder(context)
            // Add the buttons
            builder.setMessage("Please check network connection")
            builder.setPositiveButton("Yes") { dialog, id ->
                val dialogIntent = Intent(
                        Settings.ACTION_SETTINGS)
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(dialogIntent)
            }
            builder.setNegativeButton("No") { dialog, id -> dialog.dismiss() }
            // Set other dialog properties

            // Create the AlertDialog
            val dialog = builder.create()
            dialog.show()
        }
    }
}
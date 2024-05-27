package com.ram.buspass.interfaceUtils

import android.content.Context
import android.widget.Toast

class UserInterfaceUtil {

    companion object {
        fun showToast(context: Context, message: String){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

}
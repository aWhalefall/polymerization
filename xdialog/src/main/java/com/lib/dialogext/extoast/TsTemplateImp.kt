package com.lib.dialogext.extoast

import android.content.Context
import android.os.Handler
import android.widget.Toast

class TsTemplateImp(context: Context) : ToastTemplate {

    private val context: Context = context

    override fun show(msg: Any) {
        Handler(context.mainLooper).post {
            Toast.makeText(context, msg.toString(), Toast.LENGTH_SHORT).show()
        }

    }

}
package com.lib.dialogext.extoast

import android.content.Context
import android.widget.Toast

class TsTemplateImp(context: Context) : ToastTemplate {

    private val context: Context = context

    override fun show(msg: Any) {
        Toast.makeText(context,msg.toString(),Toast.LENGTH_SHORT).show()
    }

}
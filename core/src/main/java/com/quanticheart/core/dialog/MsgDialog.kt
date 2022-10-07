package com.quanticheart.core.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

class MsgDialog(private val mContext: Context, private val message: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(mContext)
        builder.setMessage(message)
            .setPositiveButton("ok") { dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false)

        return builder.create()
    }
}

fun Activity.msgDialog(message: String) {
    val d = MsgDialog(this, message)
    d.show((this as AppCompatActivity).supportFragmentManager, this.localClassName + "msg")
}
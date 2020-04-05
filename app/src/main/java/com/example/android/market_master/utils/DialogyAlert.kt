package com.example.android.market_master.utils


import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.Window
import android.widget.Button
import com.example.android.market_master.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView


class DialogyAlert {

    fun showDialog(activity: Activity?, msg: String?) {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialogy_alert)
        val text = dialog.findViewById<View>(R.id.dialogNameEt) as MaterialTextView
        text.text = msg
        val dialogButton: Button = dialog.findViewById<View>(R.id.dialogLoginBtn) as MaterialButton
        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}
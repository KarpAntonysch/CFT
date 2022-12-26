package com.example.cft.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf

class DialogFragment(
    private val title: Int,
    private val description: Int,
    private val posText: Int,
) : androidx.fragment.app.DialogFragment() {

    lateinit var alertDialog: AlertDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val listener = DialogInterface.OnClickListener { _, which ->
            parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_RESPONSE to which))
        }

        alertDialog = AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(posText, listener)
            .create()

        return alertDialog
    }

    companion object {
        @JvmStatic
        val TAG = DialogFragment::class.java.simpleName

        @JvmStatic
        val REQUEST_KEY = "$TAG:defaultRequestKey"

        @JvmStatic
        val KEY_RESPONSE = "RESPONSE"
    }
}
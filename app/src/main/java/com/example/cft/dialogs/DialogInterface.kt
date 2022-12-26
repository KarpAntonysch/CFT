package com.example.cft.dialogs

import androidx.fragment.app.FragmentManager

interface DialogInterface {
    fun showDialog(title: Int, description:Int, posText:Int,fragmentManager:FragmentManager){
val dialog = DialogFragment(title,description,posText)
        dialog.show(fragmentManager, DialogFragment.TAG)
    }
}
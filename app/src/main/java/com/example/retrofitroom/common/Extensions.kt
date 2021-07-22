package com.example.retrofitroom.common

import android.view.View
import com.google.android.material.snackbar.Snackbar

internal fun View.longSnackBar(message: Int, action: (Snackbar.() -> Unit)? = null) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let { snackBar.it() }
    snackBar.show()
}

internal fun Snackbar.action(message: Int, action: (View) -> Unit) {
    this.setAction(message, action)
}

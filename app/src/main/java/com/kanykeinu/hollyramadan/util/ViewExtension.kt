package com.kanykeinu.hollyramadan.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(text: String?) {
    text?.let { Snackbar.make(this, it, Snackbar.LENGTH_LONG).show() }
}
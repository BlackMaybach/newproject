package com.example.newproject.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.showToast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}
fun View.gone() {
    this.visibility = View.GONE
}
fun View.show() {
    this.visibility = View.VISIBLE
}
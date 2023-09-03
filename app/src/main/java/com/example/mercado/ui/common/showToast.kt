package com.example.mercado.ui.common

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, errorMessage: String) {
    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
}
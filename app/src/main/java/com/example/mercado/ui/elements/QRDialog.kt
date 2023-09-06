package com.example.mercado.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mercado.ui.common.generateQR
import com.google.zxing.qrcode.QRCodeWriter

@Composable
fun QRDialog(text: String, onDismissRequest: () -> Unit) {
    QRCodeWriter()

    Dialog(
        onDismissRequest = {
            // Dismiss the dialog when clicking outside or pressing back
            onDismissRequest()
        }
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1F)
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                generateQR(text).asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
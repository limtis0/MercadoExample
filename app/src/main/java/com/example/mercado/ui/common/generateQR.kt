package com.example.mercado.ui.common

import android.graphics.Bitmap
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.util.Hashtable


fun generateQR(inputValue: String): Bitmap {
    val hintMap = Hashtable<EncodeHintType, ErrorCorrectionLevel?>()
    hintMap[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H // H = 30% damage

    val writer = QRCodeWriter()

    val size = 256
    val bitMatrix: BitMatrix = writer.encode(inputValue, BarcodeFormat.QR_CODE, size, size)
    val width = bitMatrix.width
    val height = bitMatrix.height

    val pixels = IntArray(width * height)
    for (y in 0 until height) {
        val offset = y * width
        for (x in 0 until width) {
            pixels[offset + x] = if (bitMatrix[x, y]) BLACK else WHITE
        }
    }

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    bitmap.setPixels(pixels, 0, width, 0, 0, width, height)

    return bitmap
}
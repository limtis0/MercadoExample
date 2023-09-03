package com.example.mercado.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mercado.remote.data.pos.POS
import com.example.mercado.ui.common.showToast
import com.example.mercado.ui.screens.domain.MainScreenDomain
import com.example.mercado.ui.elements.ImageDialog
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val domain = MainScreenDomain()

    val buttonText = "Get QR"
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var pos by remember { mutableStateOf<POS?>(null) }
    var isPopupVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    BackHandler(enabled = isPopupVisible) {
        isPopupVisible = false
    }

    // Display the app icon as an image
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .size(128.dp),
                onClick = {
                    coroutineScope.launch {
                        try {
                            pos = domain.getTestPOS()
                            isPopupVisible = true
                        } catch (error: Exception) {
                            errorMessage = "Error loading image: ${error.message}"
                        }
                    }
                }
            ) {
                Text(
                    text = buttonText,
                    fontSize = 20.sp
                )
            }
        }

        if (isPopupVisible) {
            ImageDialog(
                url = pos!!.qr.image,
                onDismissRequest = {
                    isPopupVisible = false
                }
            )
        }
    }

    // Show a toast when an error occurs
    LaunchedEffect(errorMessage) {
        if (errorMessage != null) {
            showToast(context = context, errorMessage = errorMessage!!)
            errorMessage = null
        }
    }
}
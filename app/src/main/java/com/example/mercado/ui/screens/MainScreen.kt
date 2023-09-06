package com.example.mercado.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mercado.remote.data.orders.Order
import com.example.mercado.remote.data.qr_tramma.QRTramma
import com.example.mercado.ui.common.showToast
import com.example.mercado.ui.elements.OrderDialog
import com.example.mercado.ui.screens.domain.MainScreenViewModel
import com.example.mercado.ui.elements.QRDialog
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val viewModel: MainScreenViewModel = viewModel()

    val qrButtonText = "Get QR"
    val statusButtonText = "Status"

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var qr by remember { mutableStateOf<QRTramma?>(null) }
    var order by remember { mutableStateOf<Order?>(null) }

    var isQRPopupVisible by remember { mutableStateOf(false) }
    var isStatusPopupVisible by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf<String?>(null) }

    BackHandler(enabled = (isQRPopupVisible || isStatusPopupVisible)) {
        isQRPopupVisible = false
        isStatusPopupVisible = false
    }

    // Display the app icon as an image
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Status Button
                Button(
                    modifier = Modifier
                        .size(128.dp),
                    onClick = {
                        coroutineScope.launch {
                            try {
                                order = viewModel.getCurrentOrder()

                                if (order != null) {
                                    isStatusPopupVisible = true
                                } else {
                                    errorMessage = "Order not found. Please, try again after a few seconds"
                                }
                            } catch (error: Exception) {
                                errorMessage = "Error loading order: ${error.message}"
                            }
                        }
                    }
                ) {
                    Text(
                        text = statusButtonText,
                        fontSize = 20.sp
                    )
                }

                // QR Button
                Button(
                    modifier = Modifier
                        .size(128.dp),
                    onClick = {
                        coroutineScope.launch {
                            try {
                                qr = viewModel.createTestQRTramma()
                                isQRPopupVisible = true
                            } catch (error: Exception) {
                                errorMessage = "Error loading image: ${error.message}"
                            }
                        }
                    }
                ) {
                    Text(
                        text = qrButtonText,
                        fontSize = 20.sp
                    )
                }
            }
        }

        if (isQRPopupVisible) {
            QRDialog(
                text = qr!!.qr_data,
                onDismissRequest = {
                    isQRPopupVisible = false
                }
            )
        }

        if (isStatusPopupVisible) {
            OrderDialog(
                order = order!!,
                onDismissRequest = {
                    isStatusPopupVisible = false
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
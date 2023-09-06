package com.example.mercado.ui.elements

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mercado.remote.data.orders.Order

@Composable
fun OrderDialog(order: Order, onDismissRequest: () -> Unit) {
    return Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = Modifier
                .wrapContentWidth()
                .aspectRatio(1F)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = arrayOf(
                    "ID: ${order.id}",
                    "Status: ${order.status}",
                    "OrderStatus: ${order.order_status}",
                    "TotalAmount: ${order.total_amount}",
                    "DateCreated: ${order.date_created}",
                    "LastUpdated: ${order.last_updated}",
                    "Item[0].title: ${order.items[0].title}"
                ).joinToString("\n"),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}
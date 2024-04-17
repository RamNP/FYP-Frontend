package com.ram.buspass.testing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ram.buspass.utils.components.TextView
import com.ram.buspass.ui.theme.Purple

@Composable
fun abc() {
    Box(modifier = Modifier.fillMaxSize().padding(top= 50.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextView(text = "Login..Please Waiting ..")
            CircularProgressIndicator(1f, modifier = Modifier, color = Purple)
        }
    }

}

@Preview
@Composable
fun abPrieve() {
    abc()
}
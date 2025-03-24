package com.example.silentapp

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.silentapp.ui.theme.SilentAppTheme

class MainActivity : ComponentActivity() {

    // Mutable state to hold the permission status.
    private var isPermissionGrantedState = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SilentAppTheme {
                PermissionScreen(
                    isPermissionGranted = isPermissionGrantedState.value,
                    onRequestPermission = { openNotificationPolicySettings() }
                )
            }
        }
    }

    /**
     * Check permission state each time after resume.
     */
    override fun onResume() {
        super.onResume()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        isPermissionGrantedState.value = notificationManager.isNotificationPolicyAccessGranted
    }

    private fun openNotificationPolicySettings() {
        val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}

@Composable
fun PermissionScreen(
    isPermissionGranted: Boolean,
    onRequestPermission: () -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
        ) {
            Row(
                modifier = Modifier
                    .clickable { onRequestPermission() }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(5f)
                ) {
                    Text(
                        text = "Permission for changing the Notifications Status Mode",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Needed to control the Do Not Disturb mode. Tap to open settings.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Switch(
                    checked = isPermissionGranted,
                    onCheckedChange = { /* covered by the full row listener */ },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
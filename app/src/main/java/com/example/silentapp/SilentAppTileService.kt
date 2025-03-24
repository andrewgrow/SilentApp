package com.example.silentapp

import android.app.NotificationManager
import android.app.NotificationManager.*
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.provider.Settings
import android.service.quicksettings.Tile.STATE_ACTIVE
import android.service.quicksettings.Tile.STATE_INACTIVE
import android.service.quicksettings.TileService

class SilentAppTileService: TileService() {

    override fun onTileAdded() {
        println("onTileAdded(): Called when the user adds your tile")
    }

    override fun onStartListening() {
        println("onStartListening(): Called when your app can update your tile")
        val disturbStatus = getDoNotDisturbStatus(context = applicationContext)
        when (disturbStatus) {
            INTERRUPTION_FILTER_ALL -> {
                println("The Do Not Disturb mode is disabled. All notifications are granted.")
                deactivate()
            }
            INTERRUPTION_FILTER_PRIORITY -> {
                println("The Do Not Disturb mode is enabled. Only priority notifications are granted.")
                activate()
            }
            INTERRUPTION_FILTER_NONE -> {
                println("The Do Not Disturb mode is enabled. All notifications are disabled.")
                activate()
            }
            INTERRUPTION_FILTER_ALARMS -> {
                println("The Do Not Disturb mode is enabled. Only alarms granted.")
                activate()
            }
        }
        qsTile.updateTile()
    }

    private fun activate() {
        qsTile.state = STATE_ACTIVE
        qsTile.label = getString(R.string.silent_app_tile_label_on)
    }

    private fun deactivate() {
        qsTile.state = STATE_INACTIVE
        qsTile.label = getString(R.string.silent_app_tile_label_off)
    }

    override fun onStopListening() {
        println("onStopListening(): Called when your app can no longer update your tile")
    }

    override fun onClick() {
        super.onClick()
        println("onClick(): Called when the user taps on your tile in an active or inactive state")
        val disturbStatus = getDoNotDisturbStatus(context = applicationContext)
        when (disturbStatus) {
            INTERRUPTION_FILTER_ALL -> {
                println("The Do Not Disturb mode is disabled. Change to enabled as Priority.")
                changeDndMode(applicationContext, INTERRUPTION_FILTER_PRIORITY)
                activate()
            }
            INTERRUPTION_FILTER_PRIORITY -> {
                println("The Do Not Disturb mode is enabled. Change to disabled.")
                changeDndMode(applicationContext, INTERRUPTION_FILTER_ALL)
                deactivate()
            }
            INTERRUPTION_FILTER_NONE -> {
                println("The Do Not Disturb mode is enabled. Change to disabled.")
                changeDndMode(applicationContext, INTERRUPTION_FILTER_ALL)
                deactivate()
            }
            INTERRUPTION_FILTER_ALARMS -> {
                println("The Do Not Disturb mode is enabled. Change to disabled.")
                changeDndMode(applicationContext, INTERRUPTION_FILTER_ALL)
                deactivate()
            }
        }
        qsTile.updateTile()
    }

    override fun onTileRemoved() {
        super.onTileRemoved()
        println("onTileRemoved(): Called when the user removes your tile")
    }
}

private fun getDoNotDisturbStatus(context: Context): Int {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Check if the permission about DnD granted
    if (!notificationManager.isNotificationPolicyAccessGranted) {
        // Open Settings if the Application doesn't have a permission access
        println("The applications doesn't have access to the Notifications change permission")
        val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    // Detect current mode
    return notificationManager.currentInterruptionFilter
}

/**
 * Changes the Do Not Disturb mode to the specified mode.
 *
 * @param dndMode The mode to set. Supported values are:
 * - NotificationManager.INTERRUPTION_FILTER_ALL
 * - NotificationManager.INTERRUPTION_FILTER_PRIORITY
 * - NotificationManager.INTERRUPTION_FILTER_NONE
 * - NotificationManager.INTERRUPTION_FILTER_ALARMS
 */
fun changeDndMode(context: Context, dndMode: Int) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Check if permission to modify the notification policy is granted
    if (!notificationManager.isNotificationPolicyAccessGranted) {
        println("Access to notification policy is not granted. Please enable it in the settings.")
        val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
        return
    }

    // Define the set of allowed Do Not Disturb modes
    val allowedModes = setOf(
        INTERRUPTION_FILTER_ALL,
        INTERRUPTION_FILTER_PRIORITY,
        INTERRUPTION_FILTER_NONE,
        INTERRUPTION_FILTER_ALARMS
    )

    // If the provided mode is not allowed, do nothing
    if (dndMode !in allowedModes) {
        println("Invalid mode provided. No changes made.")
        return
    }

    // Set Do Not Disturb mode to the provided mode
    notificationManager.setInterruptionFilter(dndMode)
    println("Do Not Disturb mode has been changed to mode: $dndMode.")
}
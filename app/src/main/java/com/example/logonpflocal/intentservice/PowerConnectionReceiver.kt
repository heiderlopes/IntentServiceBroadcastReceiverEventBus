package com.example.logonpflocal.intentservice

import android.os.BatteryManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import org.greenrobot.eventbus.EventBus


class PowerConnectionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL

        val chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
        val usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
        val acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC

        when (intent.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                EventBus.getDefault().post(PowerStatus.CONECTADO)
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                EventBus.getDefault().post(PowerStatus.DESCONECTADO)
            }
        }
    }
}
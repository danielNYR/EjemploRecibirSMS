package com.example.ejemplorecibirsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

public class MensajesReceiver extends BroadcastReceiver {
    TextView etiqueta;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            /* Obtenemos los mensajes */
            Object[] mensajes = (Object[]) bundle.get("pdus");

            for (int indice = 0; indice < mensajes.length; indice++) {
                /* Analizamos y convertimos el mensaje */
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) mensajes[indice]);

                String numeroTelefono = smsMessage.getOriginatingAddress();
                String mensaje = smsMessage.getMessageBody().toString().trim();


                Toast.makeText(context, "LLegó: "+numeroTelefono + "-> " + mensaje, Toast.LENGTH_LONG).show();
            }
        }
    }
}

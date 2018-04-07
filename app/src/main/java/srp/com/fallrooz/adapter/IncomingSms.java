  // Copyright (c) 2016, iGap - www.iGap.im
// iGap is a Hybrid instant messaging service .
// RooyeKhat Media Co . - www.RooyeKhat.co
// All rights reserved.

package srp.com.fallrooz.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

  public class IncomingSms extends BroadcastReceiver {
	private OnComplet listener;

	public IncomingSms( ){
		super();
	}

	public IncomingSms(OnComplet listener) {
		
		this.listener=listener;
	}
	
	
	// Get the object of SmsManager
	final SmsManager sms = SmsManager.getDefault();

	
	@Override
	public void onReceive(Context context, Intent intent) {

		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();
		Log.e("smsmsmsms","sms omad");

		try {
			
			if (bundle != null) {

				final Object[] pdusObj = (Object[]) bundle.get("pdus");

				for (int i = 0; i < pdusObj.length; i++) {

					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
					String phoneNumber = currentMessage.getDisplayOriginatingAddress();
					Log.e("smsmsmsms","phoneNumber : "+phoneNumber);
					String senderNum = phoneNumber;
					String message = currentMessage.getDisplayMessageBody();

					if(senderNum.contains("307018")){
                        String parts[] = message.split(":");
                        if(parts.length> 1)
                        {
                            String num = parts[1].substring(0,4);;
                            listener.complet(true, num);
                        }


						break;
					}
				} 

			} 

		} catch (Exception e) {

		}


	}

}

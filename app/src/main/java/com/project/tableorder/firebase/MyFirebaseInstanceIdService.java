package com.project.tableorder.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.project.tableorder.util.Constants;
import com.project.tableorder.util.PreferenceHelper;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(Constants.DEBUG_TAG, refreshedToken);
        PreferenceHelper.getInstance(this).setStringPreference(Constants.PREF_KEY_FIREBASE_TOKEN, refreshedToken);
    }
}

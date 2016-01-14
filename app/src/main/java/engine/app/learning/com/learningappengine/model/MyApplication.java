package engine.app.learning.com.learningappengine.model;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

/**
 * Created by eltonjhony on 14/01/16.
 */
public class MyApplication extends android.app.Application {

    private String accountName;
    private GoogleAccountCredential googleAccountCredential;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public GoogleAccountCredential getGoogleAccountCredential() {
        return googleAccountCredential;
    }

    public void setGoogleAccountCredential(GoogleAccountCredential googleAccountCredential) {
        this.googleAccountCredential = googleAccountCredential;
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

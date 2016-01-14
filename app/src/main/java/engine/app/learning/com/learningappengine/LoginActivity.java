package engine.app.learning.com.learningappengine;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

import engine.app.learning.com.learningappengine.model.MyApplication;

/**
 * Created by eltonjhony on 14/01/16.
 */
public class LoginActivity extends Activity {

    private static final String PREF_ACCOUNT_NAME = "PREF_ACCOUNT_NAME";
    private static final int REQUEST_ACCOUNT_PICKER = 2;
    private SharedPreferences settings;
    private GoogleAccountCredential credential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button button = (Button) this.findViewById(R.id.loginId);

        settings = getSharedPreferences("learningApiProjectSample", 0);
        credential = GoogleAccountCredential.usingAudience(this,
                "server:client_id:938739565497-i4njb8kpphdt3kqrn6ruuc2n480ik0q5.apps.googleusercontent.com");
        setSelectedAccountName(settings.getString(PREF_ACCOUNT_NAME, null));

        if (credential.getSelectedAccountName() != null &&
                ((MyApplication) this.getApplication()).getAccountName() != null) {
            Intent intent = new Intent(this, StoryActivity.class);
            startActivity(intent);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAccount();
            }
        });
    }

    // setSelectedAccountName definition
    private void setSelectedAccountName(String accountName) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_ACCOUNT_NAME, accountName);
        editor.commit();
        credential.setSelectedAccountName(accountName);
    }

    public void chooseAccount() {
        startActivityForResult(credential.newChooseAccountIntent(),
                REQUEST_ACCOUNT_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ACCOUNT_PICKER:
                if (data != null && data.getExtras() != null) {
                    String accountName =
                            data.getExtras().getString(
                                    AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        setSelectedAccountName(accountName);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.commit();
                        ((MyApplication) this.getApplication()).setAccountName(accountName);
                        ((MyApplication) this.getApplication()).setGoogleAccountCredential(credential);
                        Intent intent = new Intent(LoginActivity.this, StoryActivity.class);
                        startActivity(intent);
                    }
                }
                break;
        }
    }
}

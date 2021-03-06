package engine.app.learning.com.learningappengine.async;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;

import java.io.IOException;

import engine.app.learning.com.learningappengine.LoginActivity;
import engine.app.learning.com.learningappengine.R;
import engine.app.learning.com.learningappengine.StoryActivity;
import engine.app.learning.com.learningappengine.model.MyApplication;
import engine.app.learning.com.learningappengine.model.User;

/**
 * Created by eltonjhony on 25/01/16.
 */
public class GetUserTask extends AsyncTask<Void, Void, User> {

    private final LoginActivity mContext;
    private final String mEmail;
    private final String mScope;
    private ProgressDialog mProgressDialog;

    public GetUserTask(LoginActivity mContext, String mEmail, String mScope) {
        this.mContext = mContext;
        this.mEmail = mEmail;
        this.mScope = mScope;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.mProgressDialog = new ProgressDialog(this.mContext);
        this.mProgressDialog.show();
        this.mProgressDialog.setMessage(mContext.getString(R.string.wait));

    }

    @Override
    protected User doInBackground(Void... params) {
        String token = null;
        try {
            token = fetchToken();
            if (token != null) {
                User user = fechUser(token);
                return user;
            }
        } catch (IOException e) {
            Log.e("AUTH_ERROR", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        mProgressDialog.dismiss();
        if (user != null) {
            ((MyApplication) mContext.getApplication()).setUser(user);
            Intent intent = new Intent(mContext, StoryActivity.class);
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext, "An unexpected error occurred! Cannot get details from User Logged!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private User fechUser(String token) throws IOException {
        GoogleCredential credential = new GoogleCredential().setAccessToken(token);
        Plus plus = new Plus.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
                        .setApplicationName("learning-api-project").build();
        Person p = plus.people().get("me").execute();
        User user = new User();
        user.setEmail(mEmail);
        user.setName(p.getDisplayName());
        user.setUrlPhotoUser(p.getImage().getUrl());

        return user;
    }

    private String fetchToken() throws IOException {
        try {
            return GoogleAuthUtil.getToken(mContext, mEmail, mScope);
        } catch (UserRecoverableAuthException userRecoverableException) {
            mContext.handleException(userRecoverableException);
        } catch (GoogleAuthException fatalException) {
            mContext.handleException(fatalException);
        }
        return null;
    }
}

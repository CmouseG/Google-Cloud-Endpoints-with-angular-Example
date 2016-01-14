package engine.app.learning.com.learningappengine.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

import java.io.IOException;
import backend.engine.app.learning.com.storyApi.StoryApi;
import backend.engine.app.learning.com.storyApi.model.Story;
import engine.app.learning.com.learningappengine.R;

/**
 * Created by elton on 11/07/15.
 */
public class NewStoryAsync extends AsyncTask<Void, Void, Story> {

    private Activity context;
    private StoryApi api;
    private GoogleAccountCredential credential;
    private Story story;
    private ProgressDialog progressDialog;

    public NewStoryAsync(Activity context, GoogleAccountCredential credential, Story story) {
        this.context = context;
        this.credential = credential;
        this.story = story;
    }

    private void init() {
        if(api == null) {  // Only do this once
            StoryApi.Builder builder = new StoryApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), credential)
                    .setRootUrl(context.getString(R.string.project_id));
            api = builder.build();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressDialog = new ProgressDialog(this.context);
        this.progressDialog.show();
        this.progressDialog.setMessage(context.getString(R.string.wait));
    }

    @Override
    protected Story doInBackground(Void... params) {
        try {
            init();
            return api.story().save(story).execute();
        } catch (IOException e) {
            Log.e("UNEXPECTED ERROR", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Story result) {
        this.progressDialog.dismiss();
        if(result != null) {
            Toast.makeText(context, context.getString(R.string.new_story_success), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, context.getString(R.string.new_story_error), Toast.LENGTH_LONG).show();
        }
    }
}
package engine.app.learning.com.learningappengine.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.learning.app.engine.backend.model.Story;
import com.learning.app.engine.backend.service.StoryEndpoint;

import engine.app.learning.com.learningappengine.R;

/**
 * Created by elton on 11/07/15.
 */
public class NewStoryAsync extends AsyncTask<Void, Void, Story> {

    private Activity context;
    private Story story;
    private ProgressDialog progressDialog;

    public NewStoryAsync(Activity context, Story story) {
        this.context = context;
        this.story = story;
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
            Log.e(context.getLocalClassName(), "BEGIN => createNewStory");
            StoryEndpoint endpoint = new StoryEndpoint();
            Story storySaved = endpoint.save(story);
            return storySaved;
        } catch (Exception ex) {
            Log.e(context.getLocalClassName(), ex.getMessage(), ex);
        }
        return null;
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
package engine.app.learning.com.learningappengine.helper;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.learning.app.engine.backend.model.Story;

import java.util.Calendar;

import engine.app.learning.com.learningappengine.R;

/**
 * Class that provides support to StoryActivity
 * Created by elton on 19/07/15.
 */
public class StoryHelper {

    private Activity context;
    private Spinner component;
    private Spinner participants;
    private EditText storyName;
    private EditText sprint;

    public StoryHelper(Activity context) {
        this.context = context;
    }

    public void init() {
        this.component = (Spinner) context.findViewById(R.id.component);
        this.participants = (Spinner) context.findViewById(R.id.participants);
        this.storyName = (EditText) context.findViewById(R.id.story_name);
        this.sprint = (EditText) context.findViewById(R.id.sprint);
    }

    public Story createStoryRequestDTO() {
        Story story = new Story();
        story.setComponent(component.getSelectedItem().toString());
        story.setParticipants(Long.parseLong(participants.getSelectedItem().toString()));
        story.setSprint(String.valueOf(sprint.getText()));
        story.setStoryName(String.valueOf(storyName.getText()));
        story.setRegDate(Calendar.getInstance().getTime());
        return story;
    }

    public boolean validate() {
        String sprintAsString = sprint.getText().toString();
        String storyAsString = storyName.getText().toString();

        if(participants.getSelectedItemPosition() == 0) {
            Toast.makeText(context, context.getString(R.string.error_number_participants), Toast.LENGTH_LONG).show();
            return false;
        }
        if(component.getSelectedItemPosition() == 0) {
            Toast.makeText(context, context.getString(R.string.error_component), Toast.LENGTH_LONG).show();
            return false;
        }
        if (storyAsString.trim().length() == 0) {
            storyName.requestFocus();
            storyName.setError(context.getString(R.string.story_name_required));
            return false;
        }
        if (sprintAsString.trim().length() == 0) {
            sprint.requestFocus();
            sprint.setError(context.getString(R.string.sprint_required));
            return false;
        }
        return true;
    }

}

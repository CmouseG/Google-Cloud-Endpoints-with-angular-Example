package engine.app.learning.com.learningappengine.animations;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import engine.app.learning.com.learningappengine.R;

/**
 * Created by elton on 18/07/15.
 */
public class AnimationCards {

    public void startAnimation(final int id, final Activity activity) {
        new Thread() {
            @Override
            public void run() {
                Animation fadeIn = AnimationUtils.loadAnimation(activity, R.anim.bottom_to_top);
                View tbl = activity.findViewById(id);
                tbl.startAnimation(fadeIn);
            }
        }.start();
    }

    public void onClickAnimation(Activity context, View v) {
        // feedback animation onClick()
        Animation onClickAnimation = AnimationUtils
                .loadAnimation(context, R.anim.onclickanim);
        v.startAnimation(onClickAnimation);
    }
}

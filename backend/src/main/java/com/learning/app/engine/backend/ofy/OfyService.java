package com.learning.app.engine.backend.ofy;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.learning.app.engine.backend.model.Story;

/**
 * Created by elton on 04/12/15.
 */
public class OfyService {
    static {
        ObjectifyService.register(Story.class);
    }
    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }
    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}

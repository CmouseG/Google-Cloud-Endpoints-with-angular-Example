package com.learning.app.engine.backend.repository;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.learning.app.engine.backend.model.Story;

import java.util.List;

import static com.learning.app.engine.backend.ofy.OfyService.ofy;

/**
 * Created by elton on 06/12/15.
 */
public class StoryRepositoryImpl implements IfStoryRepository {

    private static StoryRepositoryImpl storyRepository = null;

    public static synchronized StoryRepositoryImpl getInstance() {
        if (null == storyRepository) {
            storyRepository = new StoryRepositoryImpl();
        }
        return storyRepository;
    }

    @Override
    public Story save(Story story) {
        ofy().save().entity(story).now();
        return story;
    }

    @Override
    public Story update(Story story) {
        Story storyToUpdate = ofy().load().key(Key.create(Story.class,
                story.getId())).now();
        storyToUpdate.setComponent(story.getComponent());
        storyToUpdate.setStoryName(story.getStoryName());
        storyToUpdate.setSprint(story.getSprint());
        storyToUpdate.setParticipants(story.getParticipants());
        storyToUpdate.setRegDate(story.getRegDate());
        ofy().save().entity(storyToUpdate).now();
        return story;
    }

    @Override
    public Story findByIdDAO(Long id) {
        return ofy().load().type(Story.class).id(id).now();
    }

    @Override
    public CollectionResponse<Story> findAllStoriesDAO() {
        Query<Story> query = ofy().load().type(Story.class);
        List<Story> stories = query.list();
        return CollectionResponse.<Story>builder().setItems(stories).build();
    }

    @Override
    public Story remove(Story story) {
        ofy().delete().entity(story).now();
        return story;
    }
}

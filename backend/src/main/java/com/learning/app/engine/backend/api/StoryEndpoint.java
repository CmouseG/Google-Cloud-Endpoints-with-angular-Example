
package com.learning.app.engine.backend.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiClass;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.learning.app.engine.backend.constants.AppConstants;
import com.learning.app.engine.backend.model.Story;
import com.learning.app.engine.backend.repository.StoryRepositoryImpl;

import java.util.Calendar;

/**
 * Created by elton on 03/12/15.
 */
@Api (
    name = "storyApi",
    version = "v1",
    namespace = @ApiNamespace(
            ownerDomain = AppConstants.API_OWNER,
            ownerName = AppConstants.API_OWNER,
            packagePath = AppConstants.API_PACKAGE_PATH
    )
)
@ApiClass(resource = "story",
        clientIds = {
                AppConstants.ANDROID_CLIENT_ID,
                AppConstants.WEB_CLIENT_ID},
        audiences = {AppConstants.AUDIENCE_ID}
)
public class StoryEndpoint {

    @ApiMethod(name = "story.by.id", httpMethod = ApiMethod.HttpMethod.GET)
    public Story findById(@Named("id") Long id) throws BadRequestException {
        if (id == null) {
            throw new BadRequestException("Missing attribute id");
        }
        return StoryRepositoryImpl.getInstance().findByIdDAO(id);
    }

    @ApiMethod(name = "stories.list", httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Story> listStories() {
        return StoryRepositoryImpl.getInstance().findAllStoriesDAO();
    }

    @ApiMethod(name = "story.save", httpMethod = ApiMethod.HttpMethod.POST)
    public Story save(Story story) throws UnauthorizedException {
        if (story == null) {
            throw new UnauthorizedException("Request is invalid");
        }
        story.setRegDate(Calendar.getInstance().getTime());
        return StoryRepositoryImpl.getInstance().save(story);
    }

    @ApiMethod(name = "story.remove", httpMethod = ApiMethod.HttpMethod.DELETE)
    public Story remove(@Named("id") Long id) throws NotFoundException, BadRequestException {
        if (id == null) {
            throw new BadRequestException("Missing attribute id");
        }
        Story story = findById(id);
        if (story == null) {
            throw new NotFoundException("Cannot find story to remove");
        }
        return StoryRepositoryImpl.getInstance().remove(story);
    }

    @ApiMethod(name = "story.update", httpMethod = ApiMethod.HttpMethod.PUT)
    public Story update(Story story) throws BadRequestException {
        if (story == null || story.getId() == null) {
            throw new BadRequestException("Missing attribute id");
        }
        return StoryRepositoryImpl.getInstance().update(story);
    }
}

package com.learning.app.engine.backend.repository;

import com.google.api.server.spi.response.CollectionResponse;
import com.learning.app.engine.backend.model.Story;

/**
 * Created by elton on 08/12/15.
 */
public interface IfStoryRepository {

    Story save(Story story);
    Story update(Story story);
    Story findByIdDAO(Long id);
    CollectionResponse<Story> findAllStoriesDAO();
    Story remove(Story story);
}

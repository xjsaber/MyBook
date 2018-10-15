package com.xjsaber.java.web.service.site;

import com.xjsaber.java.web.service.site.entity.Discussion;

import java.util.List;

public interface DiscussionService
{
    List<Discussion> getAllDiscussions();
    Discussion getDiscussion(long id);
    void saveDiscussion(Discussion discussion);
}

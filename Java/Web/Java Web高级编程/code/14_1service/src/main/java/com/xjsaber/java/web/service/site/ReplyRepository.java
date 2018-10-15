package com.xjsaber.java.web.service.site;

import com.xjsaber.java.web.service.site.entity.Reply;

import java.util.List;

public interface ReplyRepository
{
    List<Reply> getForDiscussion(long id);
    void add(Reply reply);
    void update(Reply reply);
    void deleteForDiscussion(long id);
}

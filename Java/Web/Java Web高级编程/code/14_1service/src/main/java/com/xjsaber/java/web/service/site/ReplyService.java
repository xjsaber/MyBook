package com.xjsaber.java.web.service.site;

import com.xjsaber.java.web.service.site.entity.Reply;

import java.util.List;

public interface ReplyService
{
    List<Reply> getRepliesForDiscussion(long discussionId);
    void saveReply(Reply reply);
}

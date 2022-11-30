package com.github.smirnovanton90.jrtb.service;

import com.github.smirnovanton90.jrtb.javarushclient.GroupDiscussionInfo;
import com.github.smirnovanton90.jrtb.repository.entity.GroupSub;

/**
 * Service for manipulating with {@link GroupSub}.
 */
public interface GroupSubService {

    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
}


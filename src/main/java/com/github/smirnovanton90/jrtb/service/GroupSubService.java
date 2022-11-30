package com.github.smirnovanton90.jrtb.service;

import com.github.smirnovanton90.jrtb.javarushclient.GroupDiscussionInfo;
import com.github.smirnovanton90.jrtb.repository.entity.GroupSub;

import java.util.List;
import java.util.Optional;

/**
 * Service for manipulating with {@link GroupSub}.
 */
public interface GroupSubService {

    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
    GroupSub save(GroupSub groupSub);
    Optional<GroupSub> findById(Integer id);
    List<GroupSub> findAll();
}


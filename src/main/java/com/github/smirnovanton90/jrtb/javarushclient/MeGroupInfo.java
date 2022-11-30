package com.github.smirnovanton90.jrtb.javarushclient;

import lombok.Data;

/**
 * Group information related to authorized user. If there is no user - will be null.
 */
@Data
public class MeGroupInfo {
    private MeGroupInfoStatus status;
    private Integer userGroupId;
}

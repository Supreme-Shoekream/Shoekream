package com.supreme.admin.model.dto.socialDTO;

import com.supreme.admin.model.dto.MemberDTO;
import com.supreme.admin.model.entity.Follow;

public record FollowDTO(
        Long idx,
        MemberDTO follower,
        MemberDTO following
) {

    public static FollowDTO fromEntity(Follow follow, MemberDTO follower, MemberDTO following){
        return new FollowDTO(
                follow.getIdx(),
                follower,
                following
        );
    }
}

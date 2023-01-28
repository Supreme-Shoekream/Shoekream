package com.supreme.shoekream.model.dto.socialDTO;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Follow;

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

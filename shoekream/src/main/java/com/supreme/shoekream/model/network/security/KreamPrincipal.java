package com.supreme.shoekream.model.network.security;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record KreamPrincipal(
        Long idx,
        String nickname,
        String memberPw,
        Collection<? extends GrantedAuthority> authorities,
        String name,
        String email,
        String hp,
        String shoeSize
) implements UserDetails {
    public static KreamPrincipal of(Long idx, String nickname, String memberPw, String name, String email, String hp, String shoeSize){
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        return new KreamPrincipal(
                idx,
                nickname,
                memberPw,
                roleTypes.stream().map(RoleType::getEmail)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                name,
                email,
                hp,
                shoeSize
        );
    }

//    public static KreamPrincipal from(Member member){
//        return KreamPrincipal.of(
//                member.getIdx(),
//                member.getNickname(),
//                member.getMemberPw(),
//                member.getName(),
//                member.getEmail(),
//                member.getHp(),
//                member.getShoeSize()
//        );
//    }

    public static KreamPrincipal from(MemberDTO dto){
        return KreamPrincipal.of(
                dto.idx(),
                dto.nickname(),
                dto.memberPw(),
                dto.name(),
                dto.email(),
                dto.hp(),
                dto.shoeSize()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return memberPw;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum RoleType {
        USER("ROLE_USER");
        @Getter private final String email;
        RoleType(String email){
            this.email = email;
        }
    }
}

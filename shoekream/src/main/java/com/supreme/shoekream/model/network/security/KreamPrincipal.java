package com.supreme.shoekream.model.network.security;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.enumclass.Status;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record KreamPrincipal(
        Long idx,
        String nickname,
        String memberPw,
        Collection<? extends GrantedAuthority> authorities,
        String name,
        String hp,
        String email,
        Status status,
        String shoeSize,
        Long point,
        String profileMemo,
        String imgUrl,
        String bank,
        String accountNumber,
        Map<String, Object> oAuth2Attributes,
        KreamPrincipal.RoleType roleType

) implements UserDetails, OAuth2User {
    public static KreamPrincipal of(Long idx,
                                    String nickname,
                                    String memberPw,
                                    String name,
                                    String hp,
                                    String email,
                                    Status status,
                                    String shoeSize,
                                    Long point,
                                    String profileMemo,
                                    String imgUrl,
                                    String bank,
                                    String accountNumber
            , Map<String, Object> oAuth2Attributes, KreamPrincipal.RoleType roleType){
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        return new KreamPrincipal(
                idx,
                nickname,
                memberPw,
                roleTypes.stream().map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                name,
                hp,
                email,
                status,
                shoeSize,
                point,
                profileMemo,
                imgUrl,
                bank,
                accountNumber,
                oAuth2Attributes,
                roleType
        );
    }
    public static KreamPrincipal createof(
                                    String nickname,
                                    String memberPw,
                                    String name,
                                    String hp,
                                    String email,
                                    String shoeSize){
        return KreamPrincipal.of(
                null,
                nickname,
                memberPw,
                name,
                hp,
                email,
                null,
                shoeSize,
                null,
                null,
                null,
                null,
                null,
                Map.of(),
                RoleType.USER
        );
    }

    public static KreamPrincipal fromFull(MemberDTO dto){
        return KreamPrincipal.of(
                dto.idx(),
                dto.nickname(),
                dto.memberPw(),
                dto.name(),
                dto.hp(),
                dto.email(),
                dto.status(),
                dto.shoeSize(),
                dto.point(),
                dto.profileMemo(),
                dto.imgUrl(),
                dto.bank(),
                dto.accNumber(),
                Map.of(),
                RoleType.USER

        );
    }

    public static KreamPrincipal from(MemberDTO dto){
        return KreamPrincipal.createof(
                dto.nickname(),
                dto.memberPw(),
                dto.name(),
                dto.hp(),
                dto.email(),
                dto.shoeSize()
        );
    }

    public static KreamPrincipal createfrom(MemberDTO dto){
        return KreamPrincipal.createof(
                dto.nickname(),
                dto.memberPw(),
                dto.name(),
                dto.hp(),
                dto.email(),
                dto.shoeSize()
        );
    }

    public MemberDTO toDto(){
        return MemberDTO.of(
                nickname,
                memberPw,
                name,
                hp,
                email,
                shoeSize,
                imgUrl
        );
    }


    public MemberDTO toFullDto(){
        return MemberDTO.of(
                idx,
                nickname,
                memberPw,
                name,
                hp,
                email,
                status,
                shoeSize,
                point,
                profileMemo,
                imgUrl,
                bank,
                accountNumber

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

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2Attributes;
    }

    @Override
    public String getName() {
        return email;
    }

    public enum RoleType {
        USER("ROLE_USER");
        @Getter private final String name;
        RoleType(String name){
            this.name = name;
        }
    }
}

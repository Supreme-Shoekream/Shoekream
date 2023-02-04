package com.supreme.admin.model.network.response;

public record ProfileLinkResponse(
        String goToProfile
) {
    public static ProfileLinkResponse of(String link){
        return new ProfileLinkResponse(link);
    }
}

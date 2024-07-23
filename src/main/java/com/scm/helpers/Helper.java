package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.security.Principal;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {
        String username="";
        if (authentication instanceof OAuth2AuthenticationToken) {
            var OAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = OAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User) authentication.getPrincipal();

            if (clientId.equalsIgnoreCase("google")) {
                username=oauth2User.getAttribute("email").toString();
            }
            return  username;
        }else{
          return authentication.getName();
        }


    }
}

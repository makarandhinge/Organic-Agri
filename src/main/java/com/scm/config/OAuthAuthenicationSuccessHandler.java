package com.scm.config;

import com.scm.entity.Provider;
import com.scm.entity.User;
import com.scm.repositories.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepo userRepo;

    Logger logger = LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenicationSuccessHandler");

        DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
/*
        logger.info(user.getName());

        user.getAttributes().forEach((key,value)->{
            logger.info("{} => {}", key, value);
        });

        logger.info(user.getAttributes().toString());
*/
        //     Data Database Save:
        String email = user.getAttribute("email");
        String name = user.getAttribute("name");

//        create user and save in database

        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setPassword("password");
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Provider.GOOGLE);
        user1.setEmailVerified(true);
        user1.setEnabled(true);
        user1.setProviderId(user.getName());

        User user2 = userRepo.findByEmail(email).orElse(null);

        if(user2 == null) {

            userRepo.save(user1);
            logger.info("User Saved" + email);
        }


//        response.sendRedirect("/home");
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");

    }

}

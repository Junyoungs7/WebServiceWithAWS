package Jy.WebServiceWithAWS.config.auth;

import Jy.WebServiceWithAWS.config.auth.dto.OAuthAttributes;
import Jy.WebServiceWithAWS.config.auth.dto.SessionUser;
import Jy.WebServiceWithAWS.domain.user.Users;
import Jy.WebServiceWithAWS.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomUserOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Users usersInfo = saveOrUpdate(attributes);

        //System.out.println("1: " + usersInfo.getName() + " " + usersInfo.getEmail());
        httpSession.setAttribute("userinfo", new SessionUser(usersInfo));
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("userinfo");
        //System.out.println("2: " + sessionUser.getName() + " " + sessionUser.getEmail());

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(usersInfo.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private Users saveOrUpdate(OAuthAttributes attributes){
        Users users = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.updates(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        //System.out.println("1: " + users.getName() + " " + users.getEmail() + " " + users.getEmail());
        return userRepository.save(users);
    }
}

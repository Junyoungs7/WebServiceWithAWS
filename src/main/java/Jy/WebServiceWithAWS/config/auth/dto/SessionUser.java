package Jy.WebServiceWithAWS.config.auth.dto;

import Jy.WebServiceWithAWS.domain.user.Users;
import lombok.Getter;

@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;

    public SessionUser(Users user) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}

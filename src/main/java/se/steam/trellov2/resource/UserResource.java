package se.steam.trellov2.resource;

import org.springframework.stereotype.Component;
import se.steam.trellov2.service.UserService;

@Component
public class UserResource {

    private final UserService service;

    private UserResource(UserService service) {
        this.service = service;
    }
}

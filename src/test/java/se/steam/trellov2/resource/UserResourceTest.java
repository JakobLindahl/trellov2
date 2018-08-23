package se.steam.trellov2.resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.steam.trellov2.model.User;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserResourceTest {

    @Autowired
    private UserResource userResource;

    User user;

    @Test
    public void getUser() {
        User user = userResource.getUser(UUID.fromString("01b2624e-1363-4fa8-a406-0e7803c86188"));
        assertTrue(user.getFirstName().equals("JakobJakob") && user.getLastName().equals("JakobJakob")
        && user.getUsername().equals("JakobJakob"));
    }

    @Before
    public void setup(){
        user = new User("JakobJakob","JakobJakob","JakobJakob");
        user = userResource.postUser(user).
    }

    @After
    public void hej(){

    }
}
package ru.kurma.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kurma.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private RestTemplate restTemplate = new RestTemplate();

    private final String url = "http://localhost:8080/api/user/";

    public List<User> getUserList() {

        User[] users = restTemplate.getForObject(url, User[].class);

        assert users != null;
        return Arrays.asList(users);

    }

    public User getUserById(String id) {

        return restTemplate.getForObject(url+"/"+id, User.class);

    }

    public void addUser(String firstName, String lastName, String login, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        restTemplate.postForEntity(url, entity, User.class);
    }

    public void editUser(User user) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        restTemplate.exchange(url, HttpMethod.PUT, entity, User.class);
    }

    public void deleteUser(String id) {
        restTemplate.delete(url+"/"+id, User.class);
    }
}

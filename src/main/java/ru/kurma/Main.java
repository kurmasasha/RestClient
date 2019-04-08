package ru.kurma;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.kurma.model.User;

public class Main {

    private RestTemplate restTemplate = new RestTemplate();


    private final String url = "http://localhost:8080/api/user/";

    public static void main(String[] args) {

    }

    private String getUsers() {

        User[] users = restTemplate.getForObject(url, User[].class);

        return users.toString();

    }

    private String getsingleUser(String id) {
        return restTemplate.getForObject(url+"/"+id, User.class).toString();
    }

    private String addUser(String firstName, String lastName, String login, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, entity, User.class);

        return responseEntity.getBody().toString();
    }

    private String editUser(User user) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        restTemplate.exchange(url, HttpMethod.PUT, entity, User.class);
        return getsingleUser(user.getId().toString());
    }

    private void deleteUser(String id) {
        restTemplate.delete(url+"/"+id, User.class);
    }
}

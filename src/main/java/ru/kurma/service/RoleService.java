package ru.kurma.service;

import org.springframework.stereotype.Service;
import ru.kurma.model.Role;

@Service
public class RoleService {

    public Role getRoleById(String id) {

        if ("2".equals(id)) {
            return new Role(2, "admin");
        }
        else return new Role(1, "user");

    }
}

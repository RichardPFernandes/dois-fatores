package com.tarefa.doisfatores;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final Map<String, String> users = new HashMap<>();

    public UserService() {
        users.put("fernandesrichard312@gmail.com", "123456");
    }

    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public String getUserEmail(String username) {
        return users.get(username) != null ? username : null;
    }
}

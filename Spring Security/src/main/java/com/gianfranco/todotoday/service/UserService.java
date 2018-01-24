package com.gianfranco.todotoday.service;

import com.gianfranco.todotoday.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
}

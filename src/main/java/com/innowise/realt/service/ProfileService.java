package com.innowise.realt.service;

import com.innowise.realt.repository.domain.User;
import org.springframework.ui.Model;

public interface ProfileService {
    Model updateMessage(Model model, User user, String mail, String name, String phone);
}

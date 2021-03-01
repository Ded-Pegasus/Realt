package com.innowise.realt.service;

import com.innowise.realt.model.ValidFunctions;
import com.innowise.realt.repository.UserRepository;
import com.innowise.realt.repository.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Model updateMessage(Model model, User user, String mail, String name, String phone) {
        model.addAttribute(user);
        if (!name.isEmpty()) {
            User userFromDB = userRepository.findByUsername(name);
            if (userFromDB != null) {
                model.addAttribute("userError", "user exist!");
                return model;
            }
            user.setUsername(name);
        }
        if (!phone.isEmpty()) {
            if (!ValidFunctions.isPhoneNumber(phone)) {
                model.addAttribute("phoneError", "phone number must be 12 digits!");
                return model;
            }
            if (!ValidFunctions.isNumeric(phone)) {
                model.addAttribute("phoneError", "number should contain only numbers!");
                return model;
            }
            phone = "+" + phone.substring(0, 3) +" (" + phone.substring(3, 5) + ") " + phone.substring(5);
            user.setPhoneNumber(phone);
        }
        if(!mail.isEmpty()) {
            user.setMail(mail);
        }
        userRepository.save(user);

        return model;
    }

}

package com.innowise.realt.service;

import com.innowise.realt.repository.domain.Listing;
import com.innowise.realt.repository.domain.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface NewPost {
    Model addNewPost(String uploadPath, User author, MultipartFile file,
                     Listing listing, Model model, BindingResult bindingResult) throws IOException;
}

package com.innowise.realt.controllers;

import com.innowise.realt.repository.domain.Listing;
import com.innowise.realt.repository.domain.User;
import com.innowise.realt.service.NewPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class NewListingController {
    @Autowired
    private NewPost newPost;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/newListing")
    public String viewFormNewMessage() {
        return "newListing";
    }

    @PostMapping("/newListing")
    public String add(
            @AuthenticationPrincipal User author,
            @RequestParam("file") MultipartFile file,
            @Valid Listing listing,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        listing.setAuthor(author);
        newPost.addNewPost(uploadPath, author, file, listing, model, bindingResult);
        return "newListing";
    }
}

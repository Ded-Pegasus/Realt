package com.innowise.realt.controllers;

import com.innowise.realt.repository.ListingRepository;
import com.innowise.realt.repository.domain.Listing;
import com.innowise.realt.repository.domain.User;
import com.innowise.realt.service.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private Main main;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model,
                       @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Listing> page = main.listingList(pageable, filter);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);
        return "main";
    }

    @GetMapping("/postDetail/{listing}")
    public String title(@PathVariable Listing listing,
                        Model model) {

        model.addAttribute("message", listing);
        return "listingDetail";
    }

    @PostMapping("/postDetail/{listing}")
    public String updatePost(@PathVariable Listing listing,
                             @RequestParam(required = false) MultipartFile title_file,
                             @RequestParam(required = false) MultipartFile file1,
                             @RequestParam(required = false) MultipartFile file2,
                             @RequestParam(required = false) String tag,
                             @RequestParam(required = false) String price,
                             @RequestParam(required = false) String title,
                             @RequestParam(required = false) String text,
                             Model model) throws IOException {

        Listing updateMassage = new Listing(tag, price, title, text);
        main.listingDetail(model, uploadPath, listing, updateMassage, title_file, file1, file2);
        return "listingDetail";
    }

    @PostMapping("/deleteListing/{listing}")
    public String deletePost(
            @AuthenticationPrincipal User user,
            @PathVariable Listing listing
    ){
        listing.setDeleted();
        listingRepository.save(listing);
        return "redirect:/user-listings/" + user.getId();
    }

    @GetMapping("/user-listings/{author}")
    public String userMessages(
            @RequestParam(required = false, defaultValue = "") String filter,
            @PathVariable User author,
            Model model,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Listing> page = main.listingListForUser(pageable, author);


        model.addAttribute("page", page);
        model.addAttribute("url", "/user-listing/" + author.getId());
        model.addAttribute("filter", filter);

        return "userListings";
    }
}
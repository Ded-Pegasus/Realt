package com.innowise.realt.service;

import com.innowise.realt.model.ValidFunctions;
import com.innowise.realt.repository.ListingRepository;
import com.innowise.realt.repository.domain.Listing;
import com.innowise.realt.repository.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class NewPostServiceImpl implements NewPost {
    @Autowired
    private ListingRepository listingRepository;

    @Override
    public Model addNewPost(String uploadPath, User author, MultipartFile file,
                            Listing listing, Model model, BindingResult bindingResult) throws IOException {


        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ValidFunctions.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            return model;
        }

        Listing listingFromDB = listingRepository.findByTitle(listing.getTitle());

        if (listing.getPrice()!=null && ValidFunctions.isDouble(listing.getPrice().replaceAll(" ", ""))) {
            model.addAttribute(listing.getPrice());
            listing.setPrice(listing.getPrice());
        } else {
            model.addAttribute("priceError", "Price should contain only numbers!");
            return model;
        }

        if (listingFromDB !=null) {
            model.addAttribute("titleError", "Post with this title already exist!");
            return model;
        } else {
            model.addAttribute(listing.getTitle());
        }

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String filename = ValidFunctions.saveTitlePhoto(file, uploadPath, listing.getTitle(), "title");
            listing.setFilename(filename);
        } else {
            listing.setFilename("error.jpg");
        }

        model.addAttribute("message", listing);
        listingRepository.save(listing);
        return model;
    }
}

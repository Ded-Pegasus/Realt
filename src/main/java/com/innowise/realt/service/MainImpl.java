package com.innowise.realt.service;

import com.innowise.realt.model.ValidFunctions;
import com.innowise.realt.repository.ListingRepository;
import com.innowise.realt.repository.domain.Listing;
import com.innowise.realt.repository.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MainImpl implements Main {
    @Autowired
    private ListingRepository listingRepository;

    @Override
    public Page<Listing> listingList(Pageable pageable, String filter) {
        Page<Listing> page;
        if (filter != null && !filter.isEmpty() && !filter.contains("All")) {
            return listingRepository.findByTag(filter, pageable);
        } else {
            return listingRepository.findAll(pageable);
        }
    }

    @Override
    public Page<Listing> listingListForUser(Pageable pageable, User author) {
        return listingRepository.findByUser(pageable, author);
    }

    @Override
    public Model listingDetail(Model model, String uploadPath, Listing listing, Listing updateMassage,
                               MultipartFile title_file, MultipartFile file1, MultipartFile file2) throws IOException {

        Listing listingFromDB = listingRepository.findByTitle(updateMassage.getTitle());
        if (updateMassage.getTitle()!=null && !updateMassage.getTitle().isEmpty()) {
            if (listingFromDB !=null) {
                model.addAttribute("titleError", "Post with this title already exist!");
            } else {
                model.addAttribute(updateMassage.getTitle());
                listing.setTitle(updateMassage.getTitle());
            }
        }
        if (updateMassage.getPrice()!=null && !updateMassage.getPrice().isEmpty()) {
            if (ValidFunctions.isDouble(updateMassage.getPrice().replaceAll(" ", ""))) {
                model.addAttribute(updateMassage.getPrice());
                listing.setPrice(updateMassage.getPrice());
            } else {
                model.addAttribute("priceError", "Price should contain only numbers!");
                return model;
            }
        }
        if (updateMassage.getText()!=null && !updateMassage.getText().isEmpty()) {
            model.addAttribute(updateMassage.getText());
            listing.setText(updateMassage.getText());
        }
        if (updateMassage.getTag()!=null && !updateMassage.getTag().isEmpty()) {
            model.addAttribute(updateMassage.getTag());
            listing.setTag(updateMassage.getTag());
        }
        if (title_file!=null && !title_file.isEmpty()) {
            String filename = ValidFunctions.saveTitlePhoto(title_file, uploadPath,
                    updateMassage.getTitle(), "title");
            listing.setFilename(filename);
        }
        if (file1!=null && !file1.isEmpty()) {
            String filename = ValidFunctions.saveTitlePhoto(file1, uploadPath,
                    updateMassage.getTitle(), "firstSlide");
            listing.setFilename1(filename);
        }
        if (file2!=null && !file2.isEmpty()) {
            String filename = ValidFunctions.saveTitlePhoto(file2, uploadPath,
                    updateMassage.getTitle(), "secondSlide");
            listing.setFilename2(filename);
        }
        model.addAttribute("message", listing);
        listingRepository.save(listing);
        return model;
    }
}
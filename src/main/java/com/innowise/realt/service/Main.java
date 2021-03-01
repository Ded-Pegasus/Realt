package com.innowise.realt.service;

import com.innowise.realt.repository.domain.Listing;
import com.innowise.realt.repository.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface Main {

    Model listingDetail(Model model, String uploadPath, Listing listing, Listing updateMassage,
                        MultipartFile title_file, MultipartFile file1, MultipartFile file2) throws IOException;
    Page<Listing> listingList(Pageable pageable, String filter);

    Page<Listing> listingListForUser(Pageable pageable, User author);
}

package com.innowise.realt.repository;

import com.innowise.realt.repository.domain.Listing;
import com.innowise.realt.repository.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ListingRepository extends CrudRepository<Listing, Integer> {
    Page<Listing> findAll(Pageable pageable);
    Page<Listing> findByTag(String tag, Pageable pageable);
    Listing findByTitle(String title);

    @Query("from Listing as m where m.author = :author")
    Page<Listing> findByUser(Pageable pageable, @Param("author") User author);
}

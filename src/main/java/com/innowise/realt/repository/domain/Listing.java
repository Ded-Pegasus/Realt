package com.innowise.realt.repository.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Entity
@Where(clause = "DELETED = false")
public class Listing {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String tag;
    @NotBlank(message = "Title cannot be empty")
    @Length(max = 70, message = "Title to long")
    private String title;
    private String filename;
    private String filename1;
    private String filename2;
    @NotBlank(message = "Price cannot be empty")
    private String price;
    @Column(columnDefinition="text")
    private String text;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "DELETED")
    private boolean deleted = false;

    public Listing() {
    }

    public Listing(String tag, String price, String title, String text) {
        this.text = text;
        this.price = price;
        this.tag = tag;
        this.title = title;
    }

    public void setDeleted() {
        this.deleted = true;
    }

    public User getAuthor() {
        return author;
    }

    public String getAuthorName() {
        if (author != null) {
            return author.getUsername();
        } else {
            return "<none>";
        }
    }

    public String getAuthorPhoneNumber() {
        if (author != null) {
            return author.getPhoneNumber();
        } else {
            return "<none>";
        }
    }

    public String getAuthorMail() {
        if (author != null) {
            return author.getMail();
        } else {
            return "<none>";
        }
    }
}

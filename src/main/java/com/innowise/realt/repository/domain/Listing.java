package com.innowise.realt.repository.domain;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename1() {
        return filename1;
    }

    public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

    public String getFilename2() {
        return filename2;
    }

    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String coast) {
        this.price = coast;
    }
}

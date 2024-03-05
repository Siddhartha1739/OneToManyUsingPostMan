package com.example.OneToMany.Models;


import com.example.OneToMany.Entity.Books;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LibraryModel {
    private int LibraryID;
    @NotNull(message = "Name shouldn't be Empty")
    @NotBlank(message = "Name shouldn't be Empty")
    private String Name;
    @NotNull(message = "Location shouldn't be Empty")
    @NotBlank(message = "Location shouldn't be Blank")
    private String Location;
    @Max(value=9999999999L,message = "Number should not exceed 10 digits")
    @NotBlank(message = "Contact Information shouldn't be Blank")
    private Long ContactInformation;

    private List<Books> books=new ArrayList<>();

    public int getLibraryID() {
        return LibraryID;
    }

    public void setLibraryID(int libraryID) {
        LibraryID = libraryID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public long getContactInformation() {
        return ContactInformation;
    }

    public void setContactInformation(long contactInformation) {
        ContactInformation = contactInformation;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
}

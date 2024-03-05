package com.example.OneToMany.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Library")
public class Library {
    @Id
    private int LibraryID;

    private String Name;

    private String Location;

    private Long ContactInformation;
    @OneToMany(mappedBy = "library",cascade = CascadeType.ALL)
    private List<Books> books=new ArrayList<>();

    public Library() {
    }
    public Library(int libraryID, String name, String location, Long contactInformation, List<Books> books) {
        LibraryID = libraryID;
        Name = name;
        Location = location;
        ContactInformation = contactInformation;
        this.books = books;
    }

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

    public Long getContactInformation() {
        return ContactInformation;
    }

    public void setContactInformation(Long contactInformation) {
        ContactInformation = contactInformation;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
}

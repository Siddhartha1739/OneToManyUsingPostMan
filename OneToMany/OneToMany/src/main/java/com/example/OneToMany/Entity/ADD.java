package com.example.OneToMany.Entity;

import com.example.OneToMany.Models.BooksModel;
import com.example.OneToMany.Models.LibraryModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ADD {
    @JsonIgnore
    private List<BooksModel> books;
    @JsonIgnore
    private LibraryModel library;

    public ADD(List<BooksModel> books, LibraryModel library) {
        this.books = books;
        this.library = library;
    }

    public List<BooksModel> getBooks() {
        return books;
    }

    public void setBooks(List<BooksModel> books) {
        this.books = books;
    }

    public LibraryModel getLibrary() {
        return library;
    }

    public void setLibrary(LibraryModel library) {
        this.library = library;
    }
}

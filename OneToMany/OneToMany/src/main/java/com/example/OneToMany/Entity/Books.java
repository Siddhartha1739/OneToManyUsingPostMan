package com.example.OneToMany.Entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name="Books")
public class Books {
       @Id
        private int BookId;

        private String BookName;

        private String Author;

        private int NumberOfPages;
        @ManyToOne
        @JoinColumn(name = "LibraryId")
        private Library library;

        public Books() {
        }

        public Books(int bookId, String bookName, String author, int numberOfPages, Library library) {
            BookId = bookId;
            BookName = bookName;
            Author = author;
            NumberOfPages = numberOfPages;
            this.library = library;
        }
}

package com.example.OneToMany.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import  lombok.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BooksModel {
        private int BookId;
        @NotNull(message = "Book Name shouldn't be Empty")
        @NotBlank(message = "Book Name shouldn't be Empty")
        private String BookName;
        @NotNull(message = "Author shouldn't be Empty")
        @NotBlank(message = "Author shouldn't be Empty")
        private String Author;
        @NotNull(message = "Number of Pages shouldn't be Empty")
        private int NumberOfPages;
}

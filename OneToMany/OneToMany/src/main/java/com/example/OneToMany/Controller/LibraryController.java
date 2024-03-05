package com.example.OneToMany.Controller;

import com.example.OneToMany.Entity.Books;
import com.example.OneToMany.Models.BooksModel;
import com.example.OneToMany.Models.LibraryModel;
import com.example.OneToMany.Service.LibraryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LibraryController {
    @Autowired
    private LibraryService libraryService;
    //public int libId=0;
    @GetMapping("/")
    public ResponseEntity<String> First(){
        return ResponseEntity.ok("Login Successfull");
    }
    @GetMapping("csrf-token")
    public Object getCsrfToken(HttpServletRequest request){
        return request.getAttribute("csrf");
    }

    @PostMapping("/addedLib")
    public String addedLib(@Valid  @RequestBody LibraryModel libraryModel){
        System.out.println(libraryModel);
        libraryService.addLibrary(libraryModel);
        return "Library Addded Sccessfully";
    }


    @RequestMapping("/viewLib")
    public String viewLib(){
        List<LibraryModel> library= libraryService.getAll();

        return "viewLib";
    }

    @RequestMapping("/updateLibrary/{LibraryID}")
    public String updateLibrary(@PathVariable int LibraryID,ModelMap model ){
        LibraryModel library= libraryService.getLibraryBYId(LibraryID);
        String Name=library.getName();
        String Location=library.getLocation();
        long ContactInformation=library.getContactInformation();
        model.addAttribute("Id",LibraryID);
        model.addAttribute("Name",Name);
        model.addAttribute("Location",Location);
        model.addAttribute("ContactInformation",ContactInformation);
        return "updating";
    }
    @RequestMapping("/updatedLibrary/{LibraryID}/{ContactInformation}")
    public String updated(@PathVariable int LibraryID,String Name,String Location,@PathVariable long ContactInformation,ModelMap model){
        libraryService.updateLibrary(LibraryID,Name,Location,ContactInformation);
        List<LibraryModel> library= libraryService.getAll();
        model.addAttribute("libraryList",library);
        return "viewLib";
    }


    @RequestMapping("/updateBooks/{BookId}")
    public String updateBooks(@PathVariable int BookId,ModelMap model ){
        Books books= libraryService.getBooksBYId(BookId);
        String Name=books.getBookName();
        String Author=books.getAuthor();
        int NumberOfPages=books.getNumberOfPages();
        model.addAttribute("BookId",BookId);
        model.addAttribute("BookName",Name);
        model.addAttribute("Author",Author);
        model.addAttribute("NumberOfPages",NumberOfPages);
        return "updatingBooks";
    }


    @GetMapping(value = "/viewBooks/{LibraryID}")
    public  List<BooksModel>viewBooks(@PathVariable("LibraryID") int LibraryID) {
        List<BooksModel> books = libraryService.getbooksByLibrary(LibraryID);
        return books;
    }

    @DeleteMapping("/deleteLib/{LibraryID}")
    public String deleteLib(@PathVariable int LibraryID,ModelMap model){
        libraryService.delLibrary(LibraryID);
        return "Library Deleted Successfully";
    }


    @PostMapping("/addedBooks/{LibraryID}")
    public String addedBooks( @Valid @RequestBody BooksModel booksModel,@PathVariable int LibraryID){
        libraryService.addBooks(booksModel,LibraryID);
        return "Books Added Successfully";
    }

    @PutMapping("/updatedBooks/{LibraryID}/{BookId}")
    public List<BooksModel> updatedBooks(@RequestBody BooksModel booksModel,@PathVariable int BookId,@PathVariable int LibraryID){
        libraryService.updateBooks(booksModel,BookId,LibraryID);
        List<BooksModel> books= libraryService.getbooksByLibrary(LibraryID);
        return books;
    }

    @DeleteMapping("/deleteBooks/{LibraryID}/{BookId}")
    public String deleteBooks(@PathVariable int BookId,@PathVariable int LibraryID){
        libraryService.delBooks(BookId);
        List<BooksModel> books= libraryService.getbooksByLibrary(LibraryID);
        return "Book  is  Deleted Successfully ";
    }
}

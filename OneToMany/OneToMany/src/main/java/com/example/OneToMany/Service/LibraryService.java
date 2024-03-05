package com.example.OneToMany.Service;

import com.example.OneToMany.Entity.Books;
import com.example.OneToMany.Entity.Library;
import com.example.OneToMany.Models.BooksModel;
import com.example.OneToMany.Models.LibraryModel;
import com.example.OneToMany.Repository.BooksRepository;
import com.example.OneToMany.Repository.LibraryRepository;
import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    LibraryRepository libraryRepository;
    public void addLibrary(LibraryModel libraryModel){
            Library library=new Library();
            BeanUtils.copyProperties(libraryModel,library);
            libraryRepository.save(library);
    }

    public void delLibrary(int LibraryID){
        libraryRepository.deleteById(LibraryID);
    }

    public void delBooks(int BookID){
        booksRepository.deleteById(BookID);
    }

    public List<LibraryModel> getAll(){
        List<Library> libraryList=libraryRepository.findAll();
        List<LibraryModel> libraryModelList=new ArrayList<>();

        libraryList.forEach(lib -> {
            LibraryModel libraryModel=new LibraryModel();
            BeanUtils.copyProperties(lib,libraryModel);
            libraryModelList.add(libraryModel);
        });
        return libraryModelList;
    }

    public void updateLibrary(int LibraryID,String Name,String Location,long ContactInformation){
        Library library=libraryRepository.getReferenceById(LibraryID);
        String NameL=library.getName();
        String LocationL=library.getLocation();
        long ContactInformationL=library.getContactInformation();
        NameL=Name;
        LocationL=Location;
        ContactInformationL=ContactInformation;
        library.setName(NameL);
        library.setLocation(LocationL);
        library.setContactInformation(ContactInformationL);
        libraryRepository.save(library);
    }

    public void updateBooks(BooksModel booksModel,int BookId,int LibraryID){
        Books books=booksRepository.getReferenceById(BookId);
        String BName=books.getBookName();
        String BAuthor=books.getAuthor();
        int BNumberOfPages=books.getNumberOfPages();
        BName=booksModel.getBookName();
        BAuthor=booksModel.getAuthor();
        BNumberOfPages=booksModel.getNumberOfPages();
       books.setBookName(BName);
       books.setAuthor(BAuthor);
       books.setNumberOfPages(BNumberOfPages);
       Library library=libraryRepository.getReferenceById(LibraryID);
        booksRepository.save(new Books(BookId,BName,BAuthor,BNumberOfPages,library));
    }
    public void addBooks(BooksModel booksModel,int libId){
        Books books=new Books();
        BeanUtils.copyProperties(booksModel,books);

        Optional<Library> library=libraryRepository.findById(libId);
        books.setLibrary(library.get());


        List<Books> booksList= new ArrayList<>();
        booksList.add(books);

        library.get().setBooks(booksList);
        libraryRepository.save(library.get());

    }

    public LibraryModel getLibraryBYId(int LibraryID){

        Library library= libraryRepository.getReferenceById(LibraryID);
        LibraryModel libraryModel=new LibraryModel();
        BeanUtils.copyProperties(library,libraryModel);
        return libraryModel;
    }

    public Books getBooksBYId(int BookId){
        return booksRepository.getReferenceById(BookId);
    }
    public List<BooksModel> getbooksByLibrary(int LibraryID){
         Library library=libraryRepository.getReferenceById(LibraryID);
        List<Books> booksList=library.getBooks();
        List<BooksModel> booksModelList=new ArrayList<>();
        booksList.forEach(books -> {
            BooksModel booksModel=new BooksModel();
            BeanUtils.copyProperties(books,booksModel);
            booksModelList.add(booksModel);
        });
        LibraryModel libraryModel=new LibraryModel();
        libraryModel.setBooks(booksList);
         return booksModelList;
    }

}

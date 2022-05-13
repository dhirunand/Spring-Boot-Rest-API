package com.api.book.bootrestbook.services;

import com.api.book.bootrestbook.entities.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component  //Autowired ke liye ye annotation
public class BookService {

    private static List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(new Book(434, "Java the complete Reference", "author"));
        bookList.add(new Book(876, "Head first to java", "afhguthor"));
        bookList.add(new Book(65345, "Think in java", "hgdfhd"));
    }

    public List<Book> getAllBooks() {
        return bookList;
    }

    public Book getBookById(int id) {
        Book book = null;
        try {
            book = bookList.stream().filter(e -> e.getId() == id).findFirst().get();
        } catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }

    public Book addBook(Book book) {
        bookList.add(book);
        return book;
    }

    public Book deleteBook(int bookId){
        for(Book book : bookList){
            if(book.getId() == bookId){
                bookList.remove(book);
                return book;
            }
        }
        return null;
    }

    public void updateBook(Book newBook, int id){
        for(Book book : bookList){
            if(book.getId() == id){
                bookList.remove(book);
                bookList.add(newBook);
                return;
            }
        }
    }
}

package com.api.book.bootrestbook.controllers;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Controller //Normal MVC me normal page/response dene ke liye @Controller, but for restapi we've to use @RestController
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

//    @RequestMapping(value = "books", method = RequestMethod.GET)
//    @ResponseBody //as it is string chala jaye "This is testing book first", i.e. Response body me as it is text chala jaye, @RestController use krne pr @ResponseBody lgane ki jrurat nhi pdti so commenting this
    @GetMapping("/books") // == @RequestMapping(value = "books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBooks() {
//        ResponseEntity, data aur response code dono bhejta h
        List<Book> list = this.bookService.getAllBooks(); //jackson ki configuration automation boot me h jo automation obj to json and json to obj convert krte rhta h
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }


    //get single book handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book book = bookService.getBookById(id);
        if (book == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(Optional.of(book));
    }

    //add new book handler
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            Book book1 = this.bookService.addBook(book);
            System.out.println(book1);
            return ResponseEntity.of(Optional.of(book)); // or ResponseEntity.status(HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete book handler
    @DeleteMapping("/books/{bookId}")
    public Book deleteBook(@PathVariable("bookId") int bookId) {

        Book book = this.bookService.deleteBook(bookId);
        return book;
    }

    //update book handler
    @PutMapping("/books/{bookId}")
    public void updateBook(@RequestBody Book newBook, @PathVariable("bookId") int bookId) {
        this.bookService.updateBook(newBook, bookId);
    }

}
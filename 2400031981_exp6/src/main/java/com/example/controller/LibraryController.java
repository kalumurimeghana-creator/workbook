package com.example.controller;


import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryController {

    // In-memory list to store books
    private List<Book> bookList = new ArrayList<>();

    // Constructor → adds default books once
    public LibraryController() {
        bookList.add(new Book(1, "Spring Boot in Action", "Craig Walls", 450));
        bookList.add(new Book(2, "Java Programming", "James Gosling", 399));
        bookList.add(new Book(3, "Microservices with Spring", "Sam Newman", 550));
    }

    // 2. /welcome
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Online Library System";
    }

    // 3. /count
    @GetMapping("/count")
    public int totalBooks() {
        return bookList.size();
    }

    // 4. /price
    @GetMapping("/price")
    public double bookPrice() {
        return 499.99;
    }

    // 5. /books → NOW RETURNS ALL BOOKS
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookList;
    }

    // 6. /books/{id}
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookList.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // 7. /search?title=
    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        return "Search result for book: " + title;
    }

    // 8. /author/{name}
    @GetMapping("/author/{name}")
    public String authorName(@PathVariable String name) {
        return "Books written by author: " + name;
    }

    // 9. /addbook
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookList.add(book);
        return "Book added successfully";
    }

    // 10. /viewbooks
    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return bookList;
    }
}
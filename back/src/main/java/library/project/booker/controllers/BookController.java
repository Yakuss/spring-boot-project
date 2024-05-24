package library.project.booker.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import library.project.booker.models.Book;
import library.project.booker.models.BookType;
import library.project.booker.services.BookRepository;
import library.project.booker.services.BookTypeRepository;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;
    private final BookTypeRepository bookTypeRepository;

    public BookController(BookRepository bookRepository, BookTypeRepository bookTypeRepository) {
        this.bookRepository = bookRepository;
        this.bookTypeRepository = bookTypeRepository;
    }

    
    //id and name
    @GetMapping("/user/{userId}/books/search/{title}")
    public ResponseEntity<List<Book>> getBooksByTitleContainingAndUserId(@PathVariable Long userId, @PathVariable String title) {
        List<Book> books = bookRepository.findByTitleContainingAndUserId(title, userId);
        if (books != null && !books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    @GetMapping("/user/{userId}/books")
    public ResponseEntity<List<Book>> getBooksByUserId(@PathVariable Long userId) {
        List<Book> books = bookRepository.findByUserId(userId);
        if (books != null && !books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPublicationDate(bookDetails.getPublicationDate());
            bookRepository.save(book);
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    
    @GetMapping("/books/{id}/view")
    public ResponseEntity<String> viewPdf(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return ResponseEntity.ok(book.getPdfUrl());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(keyword);
        return ResponseEntity.ok(books);
    }
    /*
    @GetMapping("/books/searchByType")
    public ResponseEntity<List<Book>> getBooksByType(@RequestParam String typeName) {
        BookType bookType = bookTypeRepository.findByName(typeName);
        if (bookType != null) {
            List<Book> books = bookRepository.findByBookType(bookType);
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    
    @GetMapping("/books/searchByType")
    public ResponseEntity<List<Book>> getBooksByType(@RequestParam Long bookTypeId) {
        List<Book> books = bookRepository.findByBookTypeId(bookTypeId);
        return ResponseEntity.ok(books);
    }

///search booktype and id user
    @GetMapping("/user/{userId}/books/searchByType/{bookTypeId}")
    public ResponseEntity<List<Book>> getBooksByTypeAndUserId(@PathVariable Long userId, @PathVariable Long bookTypeId) {
        List<Book> books = bookRepository.findByBookTypeIdAndUserId(bookTypeId, userId);
        if (books != null && !books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

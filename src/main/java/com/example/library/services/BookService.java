package com.example.library.services;

import com.example.library.models.Book;
import com.example.library.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
            .map(book -> {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPublisher(updatedBook.getPublisher());
                book.setIssueDate(updatedBook.getIssueDate());
                book.setStudentName(updatedBook.getStudentName());
                book.setReturnDate(updatedBook.getReturnDate());
                return bookRepository.save(book);
            })
            .orElseThrow(() -> new RuntimeException("Книга не найдена"));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

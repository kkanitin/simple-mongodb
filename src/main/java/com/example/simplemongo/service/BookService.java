package com.example.simplemongo.service;

import com.example.simplemongo.document.Author;
import com.example.simplemongo.document.Book;
import com.example.simplemongo.repository.AuthorRepositoryImpl;
import com.example.simplemongo.repository.BookRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepositoryImpl bookRepositoryImpl;
    private final AuthorRepositoryImpl authorRepositoryImpl;

    public List<Book> getByPenName(String penName) {
        Author byPenName = authorRepositoryImpl.findByPenName(penName);
        return bookRepositoryImpl.findByAuthor(byPenName.getId());
    }
}

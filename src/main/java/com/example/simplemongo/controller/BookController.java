package com.example.simplemongo.controller;

import com.example.simplemongo.document.Book;
import com.example.simplemongo.model.BookByAuthorPenNameRequest;
import com.example.simplemongo.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getBookByAuthorPenName(@RequestBody @Validated BookByAuthorPenNameRequest request) {
        return ResponseEntity.ok(bookService.getByPenName(request.getPenName()));
    }

}

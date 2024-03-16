package com.example.simplemongo.controller;

import com.example.simplemongo.document.Author;
import com.example.simplemongo.model.AuthorByPenNameRequest;
import com.example.simplemongo.model.AuthorModel;
import com.example.simplemongo.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody @Validated AuthorModel authorModel) {
        return ResponseEntity.ok(authorService.createAuthor(authorModel));
    }

    @GetMapping
    public ResponseEntity<Author> getAuthorByPenName(@RequestBody @Validated AuthorByPenNameRequest request) {
        return ResponseEntity.ok(authorService.getAuthorByPenName(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable String id, @RequestBody @Validated AuthorModel authorModel) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorModel));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Long> partialUpdateAuthor(@PathVariable String id, @RequestBody AuthorModel authorModel) {
        return ResponseEntity.ok(authorService.partialUpdateAuthor(id, authorModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAuthor(@PathVariable String id) {
        return ResponseEntity.ok(authorService.deleteAuthor(id));
    }
}

package com.example.simplemongo.controller;

import com.example.simplemongo.document.Author;
import com.example.simplemongo.model.AuthorByPenNameRequest;
import com.example.simplemongo.model.AuthorModel;
import com.example.simplemongo.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<List<Author>> createAuthors(@RequestBody @Validated List<AuthorModel> authorModels) {
        return ResponseEntity.ok(authorService.createAuthors(authorModels));
    }

    @GetMapping
    public ResponseEntity<Author> getAuthorByPenName(@RequestBody @Validated AuthorByPenNameRequest request) {
        Author authorsByPenName = authorService.getAuthorByPenName(request);
        return ResponseEntity.ok(authorsByPenName);
    }
}

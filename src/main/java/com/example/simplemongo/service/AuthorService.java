package com.example.simplemongo.service;

import com.example.simplemongo.document.Author;
import com.example.simplemongo.mapper.AuthorMapper;
import com.example.simplemongo.model.AuthorByPenNameRequest;
import com.example.simplemongo.model.AuthorModel;
import com.example.simplemongo.repository.AuthorRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepositoryImpl authorRepositoryImpl;
    private final AuthorMapper authorMapper;

    public List<Author> createAuthors(List<AuthorModel> authorModels) {
        return authorRepositoryImpl.saveAll(authorMapper.toAuthorEntities(authorModels));
    }

    public Author getAuthorByPenName(AuthorByPenNameRequest authorByPenNameRequest) {
        return authorRepositoryImpl.findByPenName(authorByPenNameRequest.getPenName());
    }
}



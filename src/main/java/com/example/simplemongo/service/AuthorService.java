package com.example.simplemongo.service;

import com.example.simplemongo.document.Author;
import com.example.simplemongo.mapper.AuthorMapper;
import com.example.simplemongo.model.AuthorByPenNameRequest;
import com.example.simplemongo.model.AuthorModel;
import com.example.simplemongo.repository.AuthorRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepositoryImpl authorRepositoryImpl;
    private final AuthorMapper authorMapper;

    public Author createAuthor(AuthorModel authorModel) {
        return authorRepositoryImpl.save(authorMapper.toAuthorEntity(authorModel));
    }

    public Author getAuthorByPenName(AuthorByPenNameRequest authorByPenNameRequest) {
        return authorRepositoryImpl.findByPenName(authorByPenNameRequest.getPenName());
    }

    public Author updateAuthor(String authorId, AuthorModel authorModel) {
        return authorRepositoryImpl.update(authorMapper.toAuthorEntityWithId(authorId, authorModel));
    }

    public long partialUpdateAuthor(String authorId, AuthorModel authorModel) {
        Author authorEntity = authorMapper.toAuthorEntityWithId(authorId, authorModel);
        return authorRepositoryImpl.partialUpdate(authorEntity, authorMapper.toUpdateDefinition(authorEntity));
    }

    public Long deleteAuthor(String id) {
        return authorRepositoryImpl.delete(id);
    }
}



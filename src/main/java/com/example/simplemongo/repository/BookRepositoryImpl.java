package com.example.simplemongo.repository;

import com.example.simplemongo.document.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class BookRepositoryImpl implements SimpleMongoRepository<Book, String> {

    private final MongoTemplate mongoTemplate;

    public List<Book> findByAuthor(String authorId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("author.id").is(authorId));
        return mongoTemplate.find(query, Book.class);
    }

    @Override
    public List<Book> saveAll(List<Book> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Book save(Book entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Book update(Book entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long partialUpdate(Book entities, UpdateDefinition updateDefinition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long delete(String pk) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

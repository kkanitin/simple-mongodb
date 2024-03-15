package com.example.simplemongo.repository;

import com.example.simplemongo.document.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class BookRepositoryImpl {

    private final MongoTemplate mongoTemplate;

    public List<Book> findByAuthor(String authorId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("author.id").is(authorId));
        return mongoTemplate.find(query, Book.class);
    }
}

package com.example.simplemongo.repository;

import com.example.simplemongo.document.Author;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class AuthorRepositoryImpl {

    private final MongoTemplate mongoTemplate;

    public Author findByPenName(String penName) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("book")
                .localField("_id")
                .foreignField("author")
                .pipeline(Aggregation.project().andExclude("author"))
                .as("books");

        MatchOperation matchOperation = Aggregation.match(Criteria.where("penName").is(penName));
        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperation,
                matchOperation
        );

        return mongoTemplate.aggregate(aggregation, "author", Author.class).getMappedResults().getFirst();
    }

    public List<Author> saveAll(List<Author> authors) {
        return mongoTemplate.insertAll(authors).stream().toList();
    }
}

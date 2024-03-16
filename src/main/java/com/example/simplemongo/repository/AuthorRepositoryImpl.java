package com.example.simplemongo.repository;

import com.example.simplemongo.document.Author;
import com.mongodb.client.result.DeleteResult;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class AuthorRepositoryImpl implements SimpleMongoRepository<Author, String> {

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

    @Override
    public List<Author> saveAll(List<Author> authors) {
        return mongoTemplate.insertAll(authors).stream().toList();
    }

    @Override
    public Author save(Author entity) {
        return mongoTemplate.save(entity);
    }

    @Override
    public Author update(Author author) {
        return mongoTemplate.save(author);
    }

    @Override
    public long partialUpdate(Author author, UpdateDefinition updateDefinition) {
        Query query = new Query(Criteria.where("_id").is(author.getId()));

        return mongoTemplate.updateFirst(query, updateDefinition, Author.class).getModifiedCount();
    }

    @Override
    public long delete(String authorId) {
        Query query = new Query(Criteria.where("_id").is(authorId));
        DeleteResult remove = mongoTemplate.remove(query, Author.class);
        return remove.getDeletedCount();
    }
}

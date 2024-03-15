package com.example.simplemongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Log4j2
@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;
    @Value("${spring.data.mongodb.port}")
    private String mongoPort;
    @Value("${spring.data.mongodb.database}")
    private String mongoDb;
    @Value("${spring.data.mongodb.username}")
    private String mongoUsername;
    @Value("${spring.data.mongodb.password}")
    private String mongoPassword;

    @Bean
    public MongoClient mongo() {
        String connectionStringRaw = String.format("mongodb://%s:%s@%s:%s/", mongoUsername, mongoPassword, mongoHost, mongoPort);
        log.info("connectionStringRaw : {}", connectionStringRaw);
        ConnectionString connectionString = new ConnectionString(connectionStringRaw);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .retryWrites(true)
                .writeConcern(WriteConcern.MAJORITY)
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory(mongo(), mongoDb);
    }

    @Bean
    public DbRefResolver mongoDbRefResolver() {
        return new DefaultDbRefResolver(mongoDbFactory());
    }

    @Bean
    public MongoTemplate mongoTemplate() {

        //remove _class
        MappingMongoConverter converter = new MappingMongoConverter(mongoDbRefResolver(), new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return new MongoTemplate(mongoDbFactory(), converter);

    }
}

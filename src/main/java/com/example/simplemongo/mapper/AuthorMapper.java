package com.example.simplemongo.mapper;

import com.example.simplemongo.document.Author;
import com.example.simplemongo.model.AuthorModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface AuthorMapper {

    default UpdateDefinition toUpdateDefinition(Author author) {
        Update updateDefinition = new Update();
        if (ObjectUtils.isEmpty(author)) {
            return updateDefinition;
        }

        if (!ObjectUtils.isEmpty(author.getName())) {
            updateDefinition.set("name", author.getName());
        }
        if (!ObjectUtils.isEmpty(author.getPenName())) {
            updateDefinition.set("penName", author.getPenName());
        }
        if (!ObjectUtils.isEmpty(author.getNationality())) {
            updateDefinition.set("nationality", author.getNationality());
        }
        if (!ObjectUtils.isEmpty(author.getSite())) {
            updateDefinition.set("site", author.getSite());
        }

        return updateDefinition;
    }

    default Author toAuthorEntityWithId(String id, AuthorModel authorModel) {
        return Author.builder()
                .id(id)
                .name(authorModel.getName())
                .penName(authorModel.getPenName())
                .nationality(authorModel.getNationality())
                .site(authorModel.getSite())
                .build();
    }

    //there are something went wrong while build with generate mapstruct method.
    @Named("toAuthorEntity")
    default Author toAuthorEntity(AuthorModel authorModel) {
        return toAuthorEntityWithId(null, authorModel);
    }

    @IterableMapping(qualifiedByName = "toAuthorEntity")
    List<Author> toAuthorEntities(List<AuthorModel> authorModels);
}

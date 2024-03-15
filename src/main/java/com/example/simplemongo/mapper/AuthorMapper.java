package com.example.simplemongo.mapper;

import com.example.simplemongo.document.Author;
import com.example.simplemongo.model.AuthorModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface AuthorMapper {

    //there are something went wrong while build with generate mapstruct method.
    @Named("toAuthorEntity")
    default Author toAuthorEntity(AuthorModel authorModel) {
        return Author.builder()
                .name(authorModel.getName())
                .penName(authorModel.getPenName())
                .nationality(authorModel.getNationality())
                .build();
    }

    @IterableMapping(qualifiedByName = "toAuthorEntity")
    List<Author> toAuthorEntities(List<AuthorModel> authorModels);
}

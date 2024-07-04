package com.recipebook.domain;

import com.recipebook.domain.common.AbstractEntity;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe extends AbstractEntity {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Type(ListArrayType.class)
    @Column(name = "ingredients", columnDefinition = "text[]")
    private List<String> ingredients;

    @Type(ListArrayType.class)
    @Column(name = "instructions", columnDefinition = "text[]")
    private List<String> instructions;

    private String contact;

}

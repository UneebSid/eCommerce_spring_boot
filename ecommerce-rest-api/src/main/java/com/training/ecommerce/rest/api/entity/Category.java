package com.training.ecommerce.rest.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(
        name = "categories"
)
public class Category
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "category_name",nullable = false, unique = true)
    private String categoryName;
    @Column(name = "category_description")
    private String categoryDescription;
    @Column(name = "category_image")
    private String categoryImage;
    private int position;
    private boolean status;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<SubCategory> subCategories = new HashSet<>();

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Product> products = new HashSet<>();
}

package com.training.ecommerce.rest.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        name = "subcategories"
)
public class SubCategory
{

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "sub_cat_name", nullable = false, unique = true)
    private String subCategoryName;
    private String subCategoryDescription;
    private String subCategoryImage;
    private int position;
    private boolean status;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Category category;
//
//    @OneToMany(
//            mappedBy = "subCategory",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private Set<Product> products = new HashSet<>();
}

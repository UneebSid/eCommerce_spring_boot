package com.training.ecommerce.rest.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "products"
)
public class Product
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "product_image")
    private String productImage;

    private double price;
    private int position;
    private boolean status;

    @ManyToOne
            (
//                    fetch = FetchType.LAZY
            )
    @JoinColumn(name = "category_id")
    private Category category;

//    @ManyToOne
//            (
//                    fetch = FetchType.LAZY
//            )
//    @JoinColumn(
//            name = "sub_cat_id"
//    )
//    private SubCategory subCategory;
}

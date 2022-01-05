package com.example.demologin.entity;

import com.example.demologin.model.dto.ProductInfoDto;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@SqlResultSetMappings( //Create list result mapping
        value = {
                @SqlResultSetMapping(
                        name = "productInfoDto",
                        classes = @ConstructorResult(
                                targetClass = ProductInfoDto.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Integer.class),
                                        @ColumnResult(name = "name", type = String.class),
                                        @ColumnResult(name = "slug", type = String.class),
                                        @ColumnResult(name = "price", type = Long.class),
                                        @ColumnResult(name = "totalSold", type = Integer.class),
                                        @ColumnResult(name = "promotionPrice", type = Integer.class)
                                }
                        )
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
@Table(name = "product")
@EqualsAndHashCode
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class Product {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", columnDefinition = "varchar(300) NOT NULL")
    private String name;

    @Column(name = "slug", nullable = false)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brandId;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_available", columnDefinition = "TINYINT(1)")
    private boolean isAvailable;

    @Column(name = "create_at", nullable = false)
    private Date create_at;

    @Column(name = "price")
    private long price;

    @Type(type = "json")
    @Column(name = "onfeet_images",columnDefinition = "json")
    private List<String> onfeetImages;

    @Type(type = "json")
    @Column(name = "product_images", columnDefinition = "json")
    private List<String> productImages;

    @Column(name = "total_sold")
    private int totalSold;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "categoruy_id")
    )
    private List<Category> categories;
}

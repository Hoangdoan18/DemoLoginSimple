package com.example.demologin.entity;

import com.example.demologin.model.dto.BrandInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@SqlResultSetMappings(
        value = {
                @SqlResultSetMapping(
                        name = "brandInfo",
                        classes = @ConstructorResult(
                                targetClass = BrandInfo.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Integer.class),
                                        @ColumnResult(name = "name", type = String.class),
                                        @ColumnResult(name = "thumbnail", type = String.class),
                                        @ColumnResult(name = "product_count", type = Integer.class)
                                }
                        )
                )
        }
)
@NamedNativeQuery(
        name = "getListBrandAndProductCount",
        resultSetMapping = "brandInfo",
        query = "SELECT brand.id, brand.name, brand.thumbnail,\n" +
                "(\n" +
                "    SELECT count(product.id)\n" +
                "    FROM product\n" +
                "    WHERE product.brand_id = brand.id\n" +
                ") product_count \n" +
                "FROM brand "
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "thumbnail")
    private String thumbnail;
}

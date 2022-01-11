package com.example.demologin.repository;

import com.example.demologin.entity.Brand;
import com.example.demologin.model.dto.BrandInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query(nativeQuery = true, name = "getListBrandAndProductCount")
    public List<BrandInfo> getListBrandAndProductCount();

    @Query(nativeQuery = true, value = "SELECT brand_id FROM brand")
    List<Integer> getAllBrandID();
}

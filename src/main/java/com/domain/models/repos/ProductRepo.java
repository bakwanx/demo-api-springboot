package com.domain.models.repos;

import com.domain.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//CrudRepository<Entity,Primary Key>
public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findByNameContains(String name);

    @Query(value = "Select * From tbl_product WHERE id = ?1", nativeQuery = true)
    Product getDataById(Long id);

}

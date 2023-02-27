package com.domain.models.repos;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//CrudRepository<Entity,Primary Key>
public interface ProductRepo extends CrudRepository<Product, Long> {

    // p itu sebuah alias
    @Query("SELECT p FROM Product p WHERE p.name = :name")//nyebutnya JPAQL
    Product findProductByName(@PathParam("name") String name);//Anotation Pathparam digunakan untuk menghubungkan query diatas

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    List<Product> findProductsByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")//mencari product berdasarkan category id
    List<Product> findByProductCategory(@PathParam("categoryId") Long categoryId);

    List<Product> findByNameContains(String name);

    @Query(value = "Select * From tbl_product WHERE id = ?1", nativeQuery = true)
    Product getDataById(Long id);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    List<Product> findProductsBySupplier(@PathParam("supplier") Supplier supplier);

}

package com.domain.services;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.models.repos.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Class Service digunakan untuk melakukan proses bisnis/logic2 nya
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    //bisa digunakan untuk create atau update
    //update jika data sudah ada (berdasarkan id)
    public Product save(Product product){
        return productRepo.save(product);
    }

    public Product findById(Long id){
        Optional<Product> byId = productRepo.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    //get list of data
    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(Long id){
        productRepo.deleteById(id);
    }

    public Iterable<Product> findByName(String name){
        return productRepo.findByNameContains(name);
    }

    public void addSupplier(Supplier supplier, Long productId){
        Product product = findById(productId);
        if(product == null){
            throw new RuntimeException("Product with ID: "+productId+" not found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }
}

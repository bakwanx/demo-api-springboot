package com.domain.services;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.models.repos.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Class Service digunakan untuk melakukan proses bisnis/logic2 nya
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierService supplierService;

    //bisa digunakan untuk create atau update
    //update jika terdapat id
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

    public Product findByProductName(String name){
        return productRepo.findProductByName(name);
    }

    public List<Product> findByProductNameLike(String name){
        return productRepo.findProductsByNameLike("%"+name+"%");
    }

    public List<Product> findByCategory(Long categoryId){
        return productRepo.findByProductCategory(categoryId);
    }

    public List<Product> findBySupplier(Long supplierId){
    Supplier supplier = supplierService.findOne(supplierId);
        if(supplier == null){
            return new ArrayList<Product>();
        };
        return productRepo.findProductsBySupplier(supplier);
    }
}

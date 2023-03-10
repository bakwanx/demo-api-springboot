package com.domain.controllers;

import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.dto.SupplierData;
import com.domain.models.entities.Supplier;
import com.domain.services.SupplierService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        /*
        // Mapping dengan cara manual
        Supplier supplier = new Supplier();
        supplier.setName(supplierData.getName());
        supplier.setAddress(supplierData.getAddress());
        supplier.setEmail(supplierData.getEmail());
        */

        // Mapping menggunakan Model Mapper
        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Supplier> findAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable("id") Long id) {
        return supplierService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        /*
        // Mapping dengan cara manual
        Supplier supplier = new Supplier();
        supplier.setName(supplierData.getName());
        supplier.setAddress(supplierData.getAddress());
        supplier.setEmail(supplierData.getEmail());
        */

        // Mapping menggunakan Model Mapper
        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/search/byEmail")
    public Supplier findByEmail(@RequestBody SearchData searchData) {
        return supplierService.findByEmail(searchData.getSearchKey());
    }

    @PostMapping("/search/byName")
    public List<Supplier> findByName(@RequestBody SearchData searchData) {
        return supplierService.findSuppliersByName(searchData.getSearchKey());
    }

    @PostMapping("/search/byNameOrEmail")
    public List<Supplier> findByNameOrEmail(@RequestBody SearchData searchData) {
        return supplierService.findByNameOrEmail(searchData.getSearchKey(), searchData.getOtherSearchKey());
    }
}

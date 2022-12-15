package com.example.sample_BE.repository;

import com.example.sample_BE.table.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

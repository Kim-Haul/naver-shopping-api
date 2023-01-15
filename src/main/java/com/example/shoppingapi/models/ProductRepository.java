package com.example.shoppingapi.models;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository 는 인터페이스로 생성
public interface ProductRepository extends JpaRepository<Product, Long> {
}
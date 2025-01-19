package com.example.catalogservice.jpa.repository;

import com.example.catalogservice.jpa.entity.CatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<CatalogEntity, Long> {
}

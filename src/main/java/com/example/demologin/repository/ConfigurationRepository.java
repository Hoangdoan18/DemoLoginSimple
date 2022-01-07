package com.example.demologin.repository;

import com.example.demologin.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {
}

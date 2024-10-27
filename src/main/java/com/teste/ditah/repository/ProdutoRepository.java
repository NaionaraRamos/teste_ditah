package com.teste.ditah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.ditah.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}

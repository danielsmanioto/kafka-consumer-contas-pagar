package com.danielsmanioto.kafka.consumer.repository;

import com.danielsmanioto.kafka.consumer.entity.ContasPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContasPagarRepository extends JpaRepository<ContasPagar, Long> {
}

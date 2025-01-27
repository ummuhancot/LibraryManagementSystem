package com.tpe.repository;

import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner,Long> {

    //1-c
    boolean existsByEmail(String email);

    //3-c
    @Query("SELECT new com.tpe.dto.OwnerDTO(o) FROM Owner o WHERE o.id=:pId")
    Optional<OwnerDTO> findOwnerDTOById(@Param("pId") Long id);
}
package com.tpe.repository;

import com.tpe.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//opsiyonel yani zorunlu değil zaten jpa yı extends yaptık.
public interface BookRepository extends JpaRepository <Book,Long>{





}

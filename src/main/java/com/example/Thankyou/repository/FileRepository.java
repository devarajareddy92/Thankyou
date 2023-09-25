package com.example.Thankyou.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Thankyou.entity.FileDetails;

@Repository
public interface FileRepository extends JpaRepository<FileDetails, Long> {
}



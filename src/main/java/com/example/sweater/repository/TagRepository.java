package com.example.sweater.repository;

import com.example.sweater.model.Tag;
import com.example.sweater.repository.custom.TagCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>, TagCustomRepository {


}

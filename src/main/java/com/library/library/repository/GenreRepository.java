package com.library.library.repository;

import com.library.library.entity.Genre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query("SELECT g from Genre g WHERE g.name = :name")
    Optional<Genre> findByName(@Param("name") String name);
}

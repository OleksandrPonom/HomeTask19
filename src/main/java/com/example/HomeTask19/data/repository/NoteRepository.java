package com.example.HomeTask19.data.repository;

import com.example.HomeTask19.data.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, UUID> {

	@Transactional
	@Query("FROM NoteEntity ne WHERE ne.title = :title")
	Optional<NoteEntity> findByTitle (@Param("title") String title);
}

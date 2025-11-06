package com.example.ativdade_aps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ativdade_aps.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}

package com.rjk.quizmicroservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rjk.quizmicroservice.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{

	
}

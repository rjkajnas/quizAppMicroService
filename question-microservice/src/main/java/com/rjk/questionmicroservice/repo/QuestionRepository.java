package com.rjk.questionmicroservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rjk.questionmicroservice.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
	
	public List<Question> findByCategory(String category);
	@Query(value="select q.id from question q where q.category = :category order by RANDOM() limit :numQ", nativeQuery=true)
	public List<Integer> findQuestionsByCategory(String category, Integer numQ);

}

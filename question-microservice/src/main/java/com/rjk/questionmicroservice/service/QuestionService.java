package com.rjk.questionmicroservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rjk.questionmicroservice.model.Answer;
import com.rjk.questionmicroservice.model.Question;
import com.rjk.questionmicroservice.model.QuestionWrapper;
import com.rjk.questionmicroservice.repo.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	QuestionRepository repo;

	public ResponseEntity<List<Question>> getAllQuestions() {
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		return new ResponseEntity<>(repo.findByCategory(category),HttpStatus.OK);
	}
	
	public ResponseEntity<Question> addQuestion(Question qn) {
		return new ResponseEntity<>(repo.save(qn), HttpStatus.CREATED);
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQ) {
		List<Integer> questionIds = repo.findQuestionsByCategory(category, numQ);
		return new ResponseEntity<>(questionIds, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> qnIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		for(Integer id : qnIds) {
			questions.add(repo.findById(id).get());
		}
		for(Question qn : questions) {
			QuestionWrapper qw = new QuestionWrapper(qn.getId(), qn.getQuestionTitle(), qn.getOption1(), qn.getOption2(), qn.getOption3(), qn.getOption4());
			wrappers.add(qw);
		}
		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Answer> ans) {
		int score =0;
		for (Answer a : ans) {
			Question qn = repo.findById(a.getId()).get();
			if (a.getAnswer().equals(qn.getRightAnswer())) {
				score++;
			}
		}
		return new ResponseEntity<>(score, HttpStatus.OK);
	}
	
}

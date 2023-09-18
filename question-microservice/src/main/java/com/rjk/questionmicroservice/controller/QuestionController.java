package com.rjk.questionmicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rjk.questionmicroservice.model.Answer;
import com.rjk.questionmicroservice.model.Question;
import com.rjk.questionmicroservice.model.QuestionWrapper;
import com.rjk.questionmicroservice.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	QuestionService service;
	
	@Autowired
	Environment env;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return service.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
		return service.getQuestionsByCategory(category );
	}
	
	@PostMapping("new")
	public ResponseEntity<Question> addQuestion(@RequestBody Question qn) {
		return service.addQuestion(qn);
	}
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQ) {
		System.out.println(env.getProperty("local.server.port"));
		return service.getQuestionsForQuiz(category, numQ);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> qnIds) {
		return service.getQuestionsById(qnIds);
	}
	
	@PostMapping("score")
	public ResponseEntity<Integer> submitQuizAnswers(@RequestBody List<Answer> ans ){
		return service.getScore(ans);
		
	}
}
package com.rjk.quizmicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rjk.quizmicroservice.feign.QuizInterface;
import com.rjk.quizmicroservice.model.Answer;
import com.rjk.quizmicroservice.model.QuestionWrapper;
import com.rjk.quizmicroservice.model.Quiz;
import com.rjk.quizmicroservice.model.QuizDTO;
import com.rjk.quizmicroservice.repo.QuizRepository;


@Service
public class QuizService {

	@Autowired
	QuizRepository repo;
	
	@Autowired
	QuizInterface quizInterface;
	
	

	public ResponseEntity<String> createQuiz(QuizDTO quiz) {
		List<Integer> questions = quizInterface.getQuestionsForQuiz(quiz.getCategory(), quiz.getNumQ()).getBody();
		Quiz q = new Quiz();
		q.setTitle(quiz.getTitle());
		q.setQuestionIds(questions);
		repo.save(q);
		return new ResponseEntity<>("Quiz creation SUCCESS", HttpStatus.CREATED);

	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Quiz quiz = repo.findById(id).get();
		List<Integer> questionIds = quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questionsForQuiz = quizInterface.getQuestionsById(questionIds);
		return questionsForQuiz;
	}

	public ResponseEntity<Integer> calculateQuizScore(int id, List<Answer> ans) {
		ResponseEntity<Integer> score = quizInterface.submitQuizAnswers(ans);
		return score;
	}

}

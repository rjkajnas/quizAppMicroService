package com.rjk.quizmicroservice.model;

import lombok.Data;

@Data
public class QuizDTO {
	private String category;
	private int numQ;
	private String title;
	
	public QuizDTO(String category, int numQ, String title) {
		super();
		this.category = category;
		this.numQ = numQ;
		this.title = title;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getNumQ() {
		return numQ;
	}
	public void setNumQ(int numQ) {
		this.numQ = numQ;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}

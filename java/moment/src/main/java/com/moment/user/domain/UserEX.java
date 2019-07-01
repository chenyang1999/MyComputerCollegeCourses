package com.moment.user.domain;

import com.moment.grade.domain.GradeVO;

public class UserEX extends UserVO {
	private GradeVO grade;

	public GradeVO getGrade() {
		return grade;
	}

	public void setGrade(GradeVO grade) {
		this.grade = grade;
	}
	
	
	
	
}

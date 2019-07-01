package com.moment.pic.domain;

import com.moment.user.domain.UserEX;

public class PicEX extends PicVO {
	private UserEX user;

	public UserEX getUser() {
		return user;
	}

	public void setUser(UserEX user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return user.getGrade().toString();
	}
	
	
}

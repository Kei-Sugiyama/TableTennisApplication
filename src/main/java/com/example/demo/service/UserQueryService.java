package com.example.demo.service;

import com.example.demo.entity.Users;

public interface UserQueryService {
	public Users findUser(String loginId);
}

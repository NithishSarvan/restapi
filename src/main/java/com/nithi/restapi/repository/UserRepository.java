package com.nithi.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nithi.restapi.users.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}

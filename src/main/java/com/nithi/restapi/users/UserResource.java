package com.nithi.restapi.users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService service;

	public UserResource(UserDaoService service) {

		this.service = service;
	}

	// GET -> /users
	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return service.retriveAllusers();
	}

	@GetMapping("/users/{id}")
	public User findByUserId(@PathVariable int id) {

		User user = service.findById(id);

		if (user == null)

			throw new UserNotFoundException("id = " + id);

		return user;
	}
	
	
	@GetMapping("/users/hateoas/{id}")
	public EntityModel<User> findByUserIdHateoas(@PathVariable int id) {

		User user = service.findById(id);

		if (user == null)

			throw new UserNotFoundException("id = " + id);

		EntityModel<User> entityModel = EntityModel.of(user);
		//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
		
		entityModel.add(link.withRel("all-users"));
		return entityModel;
		
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}
	


	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.createUser(user);
		URI loaction = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(loaction).build();
	}

}

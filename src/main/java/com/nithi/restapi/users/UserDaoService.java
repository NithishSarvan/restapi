package com.nithi.restapi.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> user = new ArrayList<User>();
	private static int id =0;

	static {
		user.add(new User(++id, "nithish", LocalDate.now().minusYears(29)));
		user.add(new User(++id, "sajith", LocalDate.now().minusYears(10)));
		user.add(new User(++id, "vhaana", LocalDate.now().minusYears(6)));
	}

	public List<User> retriveAllusers() {
		return user;
	}

	public User findById(int id) {

		Predicate<? super User> predicate = u -> u.getId() == id;
		return user.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {

		Predicate<? super User> predicate = u -> u.getId() == id;
		user.removeIf(predicate);
	}


	public  User createUser(User users) {

		users.setId(++id);
		user.add(users);
		
	
		return users ;
		
	}
}

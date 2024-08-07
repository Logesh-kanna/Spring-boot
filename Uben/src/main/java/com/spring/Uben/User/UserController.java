package com.spring.Uben.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("register")
	@ResponseBody
	public String addUser(@RequestBody UserModel user) {
        return userService.register(user);
	}
	
	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody LoginModel login) {
	    LoginResult result = userService.login(login.getName(), login.getPassword());
	    
	    if (result.getStatus()) {
	        return ResponseEntity.ok(result.getData());
	    } else {
	        return ResponseEntity.badRequest().body(result.getError());
	    }
	}
	
	@GetMapping("user/users")
	@ResponseBody
	public List<UserModel> users() {
		return userService.showAllUser();
	}
	
	@GetMapping("user/get_user")
	@ResponseBody
	public UserModel getUser(@RequestBody UserModel user) {
		return userService.showUser(user.getId());
	}
	
	@PostMapping("user/update_user")
	@ResponseBody
	public String updateUser(@RequestBody UserModel user) {
		userService.updateUser(user.getId(), user);
		return "Updated Successfully";
	}
	
	@PostMapping("user/delete_user")
	@ResponseBody
	public String deleteUser(@RequestBody UserModel user) {
		userService.delete(user.getId());
		return "Deleted Successfully";
	}
	
}

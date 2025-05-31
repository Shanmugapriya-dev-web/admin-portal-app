package com.adminportal.Application.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.Application.model.User;
import com.adminportal.Application.model.UserRequestDTO;
import com.adminportal.Application.model.UserResponseDTO;
import com.adminportal.Application.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public <T> Object validateUser(UserRequestDTO request) {
		String userName = request.getUserName();
		String password = request.getPassword();
		if (!userName.isBlank() && !userName.isEmpty()) {

		}
		Optional<User> validateUser = userRepository.findByUserNameAndPassword(userName, password);
		if (validateUser.isPresent()) {
			UserResponseDTO response = new UserResponseDTO();
			response.setUserId(validateUser.get().getId());
			response.setUserName(validateUser.get().getUserName());
			response.setRoleId(validateUser.get().getRoleId());
			response.setRoleName(validateUser.get().getPassword());
			return response;
		}
		return null;
	}

	public String addUser(@Valid JSONObject jObject) {
		String name = jObject.getString("userName");
		String password = jObject.getString("password");
		int roleId = jObject.getInt("roleId");
		int userId = jObject.getInt("userId");
		User user = new User();
		user.setRoleId(roleId);
		user.setUserName(name);
		user.setPassword(password);
		user.setActiveInd(1);
		user.setCreatedBy(userId);
		user.setCreatedOn(LocalDateTime.now());
		user.setUpdatedBy(userId);
		user.setUpdatedOn(LocalDateTime.now());
		User userDate = userRepository.save(user);
		if (userDate.getId() != 0) {
			return "User Details Added Successfully";
		} else {
			return "Failed to Add User Details";
		}
	}

}

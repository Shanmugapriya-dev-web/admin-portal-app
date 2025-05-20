package com.adminportal.Application.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.Application.model.User;
import com.adminportal.Application.model.UserRequestDTO;
import com.adminportal.Application.model.UserResponseDTO;
import com.adminportal.Application.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public <T> Object validateUser(UserRequestDTO request) {
		String userName = request.getUserName();
		String password = request.getPassword();
		if(!userName.isBlank() && !userName.isEmpty()) {
			
		}
		Optional<User> validateUser = userRepository.findByUserNameAndPassword(userName, password);
		if(validateUser.isPresent()) {
			UserResponseDTO response = new UserResponseDTO();
			response.setUserId(validateUser.get().getId());
			response.setUserName(validateUser.get().getUserName());
			response.setRoleId(validateUser.get().getRoleId());
			response.setRoleName(validateUser.get().getPassword());
			return response;
		}
		return null;
	}

}

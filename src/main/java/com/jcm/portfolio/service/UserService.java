package com.jcm.portfolio.service;

import com.jcm.portfolio.model.UserApp;
import com.jcm.portfolio.model.dto.UserDto;
import java.util.List;

public interface UserService {

  UserDto getUser(String id) throws Exception;

  List<UserDto> getAllUsers();

  UserDto updateUser(String id, UserApp user);

  UserDto createUser(UserApp userApp) throws Exception;

  void deleteUser(String userId) throws Exception;
}

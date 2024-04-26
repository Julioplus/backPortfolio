package com.jcm.portfolio.service.implementation;

import com.jcm.portfolio.model.UserApp;
import com.jcm.portfolio.model.dto.UserDto;
import com.jcm.portfolio.model.mapper.UserMapper;
import com.jcm.portfolio.repository.UserRepository;
import com.jcm.portfolio.service.UserService;
import com.jcm.portfolio.utils.exception.CustomException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.repository = userRepository;
    this.userMapper = userMapper;
  }


  @Override
  public UserDto getUser(String id) throws CustomException {
    return userMapper.userToDto(
        repository.findById(new ObjectId(id))
            .orElseThrow(() -> new CustomException("El usuario solicitado no existe")));
  }

  @Override
  public List<UserDto> getAllUsers() {
    return userMapper.listUsersToListDtoUsers(repository.findAll());
  }

  @Override
  public UserDto updateUser(String id, UserApp user) {
    UserApp userApp = repository.findById(new ObjectId(id))
        .orElseThrow(() -> new CustomException("El usuario solicitado no existe"));
    checkData(userApp);
    return null;
  }

  @Override
  public UserDto createUser(UserApp newUser) {
    log.info("El usuario con datos: {} se ha insertado correctamente.", newUser);
    return userMapper.userToDto(repository.save(newUser));
  }

  @Override
  public void deleteUser(String id) throws Exception {
    getUser(id);
    repository.deleteById(new ObjectId(id));
  }

  private void checkData(UserApp userApp) {
    // TODO este metodo comprueba que los datos del Dto pasados por el front estan correctamente segun las metricas de la app.
  }
}

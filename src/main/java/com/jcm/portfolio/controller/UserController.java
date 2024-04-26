package com.jcm.portfolio.controller;

import com.jcm.portfolio.model.dto.UserDto;
import com.jcm.portfolio.model.mapper.UserMapper;
import com.jcm.portfolio.service.UserService;
import com.jcm.portfolio.utils.exception.ExceptionMessageModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userApp")
@Slf4j
public class UserController {

  private final UserMapper userMapper;

  private final UserService userService;

  @Autowired
  public UserController(UserMapper userMapper, UserService userService) {
    this.userMapper = userMapper;
    this.userService = userService;
  }

  @GetMapping
  public List<UserDto> getAllUsers() {
    return userService.getAllUsers();
  }

  @Operation(summary = "Get a UserApp with id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found a UserApp",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = UserDto.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ExceptionMessageModel.class))}),
      @ApiResponse(responseCode = "404", description = "UserApp not found",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ExceptionMessageModel.class))})})
  @GetMapping(value = "/{id}", produces = "application/json")
  public UserDto getUser(@PathVariable String id) throws Exception {
    return userService.getUser(id);
  }

  @PutMapping(value = "/{id}", produces = "applicaciont/json")
  public UserDto updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
    return userService.updateUser(id, userMapper.userDtoToUserApp(userDto));
  }

  @PostMapping(produces = "application/json")
  public UserDto createUser(@RequestBody UserDto userDto) throws Exception {
    return userService.createUser(userMapper.userDtoToUserApp(userDto));
  }

  @DeleteMapping(value = "/{id}")
  public void deleteUser(@PathVariable String id) throws Exception {
    userService.deleteUser(id);
  }


}

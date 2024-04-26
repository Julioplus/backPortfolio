package com.jcm.portfolio.model.mapper;

import com.jcm.portfolio.configuration.MapperConfiguration.IgnoreAuditingFieldsMapper;
import com.jcm.portfolio.model.UserApp;
import com.jcm.portfolio.model.dto.UserDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  //@Mapping(target = "birthday", source = "user.birthday", dateFormat = "yyyy-MM-dd")

  //@Mapping(target = "paymentTypes", ignore = true)
  UserDto userToDto(UserApp user);

  //@Mapping(target = "birthday", source = "userDto.birthday", dateFormat = "yyyy-MM-dd")
  //@Mapping(target = "paymentTypes", ignore = true)
  @IgnoreAuditingFieldsMapper
  UserApp userDtoToUserApp(UserDto userDto);

  List<UserDto> listUsersToListDtoUsers(List<UserApp> userAppList);

}

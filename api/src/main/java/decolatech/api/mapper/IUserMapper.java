package decolatech.api.mapper;

import decolatech.api.dto.read.UserDTO;
import decolatech.api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IUserMapper {
    IUserMapper MAP = Mappers.getMapper(IUserMapper.class);
    List<UserDTO> toDtoList(List<User> user);


    User toEntity(decolatech.api.dto.write.UserDTO userDTO);
}

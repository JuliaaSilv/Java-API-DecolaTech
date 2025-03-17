package decolatech.api.mapper;

import decolatech.api.dto.read.AccountDTO;
import decolatech.api.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAccountMapper {
    IAccountMapper MAP = Mappers.getMapper(IAccountMapper.class);
    AccountDTO toDto(Account account);

    Account toEntityWrite(decolatech.api.dto.write.AccountDTO account);
}

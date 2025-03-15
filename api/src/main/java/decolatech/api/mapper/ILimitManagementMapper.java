package decolatech.api.mapper;

import decolatech.api.dto.read.LimitManagementDTO;
import decolatech.api.dto.read.NewsDTO;
import decolatech.api.entity.LimitManagement;
import decolatech.api.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ILimitManagementMapper {
    ILimitManagementMapper MAP = Mappers.getMapper(ILimitManagementMapper.class);
    LimitManagementDTO toDto(LimitManagement limitManagement);

    LimitManagement toEntity(decolatech.api.dto.write.LimitManagementDTO limitManagement);
}

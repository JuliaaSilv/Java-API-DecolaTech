package decolatech.api.mapper;

import decolatech.api.dto.read.NewsDTO;
import decolatech.api.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface INewsMapper {
    INewsMapper MAP = Mappers.getMapper(INewsMapper.class);
    List<NewsDTO> toDtoList(List<News> news);

    List<News> toEntityList(List<decolatech.api.dto.write.NewsDTO> news);
}

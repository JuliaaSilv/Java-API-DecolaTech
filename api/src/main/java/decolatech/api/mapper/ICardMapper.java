package decolatech.api.mapper;

import decolatech.api.dto.read.CardDTO;
import decolatech.api.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICardMapper {
    ICardMapper MAP = Mappers.getMapper(ICardMapper.class);
    CardDTO toDto(Card card);

    Card toEntity(decolatech.api.dto.write.CardDTO card);
}

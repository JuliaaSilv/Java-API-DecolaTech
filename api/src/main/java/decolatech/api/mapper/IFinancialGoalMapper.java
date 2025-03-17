package decolatech.api.mapper;

import decolatech.api.dto.read.FinancialGoalDTO;
import decolatech.api.entity.FinancialGoal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IFinancialGoalMapper {
    IFinancialGoalMapper MAP = Mappers.getMapper(IFinancialGoalMapper.class);
    FinancialGoalDTO toDto(FinancialGoal financialGoal);

    FinancialGoal toEntityWrite(decolatech.api.dto.write.FinancialGoalDTO financialGoal);
}

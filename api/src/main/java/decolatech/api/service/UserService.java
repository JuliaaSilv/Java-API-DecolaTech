package decolatech.api.service;

import decolatech.api.dto.read.UserDTO;
import decolatech.api.entity.News;
import decolatech.api.mapper.*;
import decolatech.api.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final INewsRepository newsRepository;
    private final ILimitManagementRepository limitManagementRepository;
    private final IFinancialGoalRepository financialGoalRepository;
    private final ICardRepository cardRepository;
    private final IAccountRepository accountRepository;

    public UserService(IUserRepository userRepository, INewsRepository newsRepository, ILimitManagementRepository limitManagementRepository, IFinancialGoalRepository financialGoalRepository, ICardRepository cardRepository, IAccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
        this.limitManagementRepository = limitManagementRepository;
        this.financialGoalRepository = financialGoalRepository;
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    public List<UserDTO> buscarUsuarios() {
        List<UserDTO> result = new ArrayList<>();
        var users = userRepository.findAll();
        var news = newsRepository.findAll();
        var limits = limitManagementRepository.findAll();
        var financials = financialGoalRepository.findAll();
        var cards = cardRepository.findAll();
        var accounts = accountRepository.findAll();

        for (var user : users) {
            if (user.getId()==3){
                var teste = "teste";
            }
            UserDTO userDTO = new UserDTO();

            userDTO.id = user.getId();
            userDTO.name = user.getName();
            userDTO.cpf = user.getCpf();
            userDTO.email = user.getEmail();
            userDTO.phone = user.getPhone();
            userDTO.birthdate = user.getBirthDate();
            userDTO.password = user.getPassword();

            var account = accounts
                    .stream().filter(x -> x.getId().equals(user.getAccountId()))
                    .findFirst();
            var card = cards
                    .stream().filter(x -> x.getId().equals(user.getCardId()))
                    .findFirst();
            var limit = limits
                    .stream().filter(x -> x.getId().equals(user.getLimitManagementId()))
                    .findFirst();
            var financial = financials
                    .stream().filter(x -> x.getId().equals(user.getFinancialGoalId()))
                    .findFirst();
            var notifications = news
                    .stream().filter(x -> x.getUserId().equals(user.getId()))
                    .toList();
            if (account.isEmpty() || card.isEmpty() || limit.isEmpty() || financial.isEmpty() || notifications.isEmpty()) {
                continue;

            }
            userDTO.account = IAccountMapper.MAP.toDto(account.get());
            userDTO.card = ICardMapper.MAP.toDto(card.get());
            userDTO.limitManagement = ILimitManagementMapper.MAP.toDto(limit.get());
            userDTO.financialGoal = IFinancialGoalMapper.MAP.toDto(financial.get());
            userDTO.news = INewsMapper.MAP.toDtoList(notifications); //Todo: Implementar usando user ID

            result.add(userDTO);
        }
        return result;
    }

    public UserDTO buscarUsuarioporId(Long id) {
        var result = this.buscarUsuarios()
                .stream().filter(x -> x.id.equals(id))
                .findFirst();
        if (result.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");

        }
        return result.get();


    }

    public Object DeletarUsuarioporId(Long id) {
        var result = userRepository.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");

        }
        userRepository.deleteById(id);
        return id;
    }

    public Object CriarUsuario(decolatech.api.dto.write.UserDTO userDTO) {
        var userExists = userRepository.existsByCpf(userDTO.cpf);
        if (userExists) {
            throw new RuntimeException("Cpf existente");
        }
        var user = IUserMapper.MAP.toEntity(userDTO);
        var news = INewsMapper.MAP.toEntityList(userDTO.news);
        var limit = ILimitManagementMapper.MAP.toEntity(userDTO.limitManagement);
        var financial = IFinancialGoalMapper.MAP.toEntity(userDTO.financialGoal);
        var card = ICardMapper.MAP.toEntity(userDTO.card);
        var account = IAccountMapper.MAP.toEntity(userDTO.account);

        var newsResult = newsRepository.saveAll(news);
        var limitResult = limitManagementRepository.save(limit);
        var financialResult = financialGoalRepository.save(financial);
        var cardResult = cardRepository.save(card);
        var accountResult = accountRepository.save(account);


        var limitIsCreated = !(limitResult.getId() <= 0);
        var accountIsCreated = !(accountResult.getId() <= 0);
        var cardIsCreated = !(cardResult.getId() <= 0);

        var errors = new ArrayList<String>();

        if (!limitIsCreated) errors.add("Limite está vazio.");
        if (!accountIsCreated) errors.add("Conta está vazia.");
        if (!cardIsCreated) errors.add("Cartão está vazio.");

        if (!errors.isEmpty()) {
            throw new RuntimeException ("Erro: " + String.join(" ", errors));
        }
        user.setLimitManagementId(limitResult.getId());
        user.setAccountId(accountResult.getId());
        user.setCardId(cardResult.getId());

        var userResult = userRepository.save(user);

        var userIsCreated = !(userResult.getId() <=0);

        if (!userIsCreated) errors.add("Não foi possivel criar o usuário");
        if (!errors.isEmpty()) {
            throw new RuntimeException ("Erro: " + String.join(" ", errors));
        }
        news.forEach(x->x.setUserId(userResult.getId()));

        newsRepository.saveAll(news);
        financialGoalRepository.save(financial);

        return userResult.getId();
    }


    public Object AtualizarUsuario(decolatech.api.dto.write.UserDTO userDTO) {
        var userExists = userRepository.findById(userDTO.id);
        if (userExists.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }


}

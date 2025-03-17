package decolatech.api.service;

import decolatech.api.dto.read.UserDTO;
import decolatech.api.entity.Account;
import decolatech.api.entity.News;
import decolatech.api.mapper.*;
import decolatech.api.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static decolatech.api.MethodsAdapter.AtualizarDados;

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
            if (user.getId() == 3) {
                var teste = "teste";
            }
            UserDTO userDTO = new UserDTO();

            userDTO.id = user.getId();
            userDTO.name = user.getName();
            userDTO.cpf = user.getCpf();
            userDTO.email = user.getEmail();
            userDTO.phone = user.getPhone();
            userDTO.birthdate = user.getBirthdate();
            userDTO.password = user.getPassword();

            var account = accounts
                    .stream().filter(x -> x.getUserId().equals(user.getId()))
                    .findFirst();
            var card = cards
                    .stream().filter(x -> x.getUserId().equals(user.getId()))
                    .findFirst();
            var limit = limits
                    .stream().filter(x -> x.getUserId().equals(user.getId()))
                    .findFirst();
            var financial = financials
                    .stream().filter(x -> x.getUserId().equals(user.getId()))
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
        var user = IUserMapper.MAP.toEntityWrite(userDTO);
        var news = INewsMapper.MAP.toEntityList(userDTO.news);
        var limit = ILimitManagementMapper.MAP.toEntityWrite(userDTO.limitManagement);
        var financial = IFinancialGoalMapper.MAP.toEntityWrite(userDTO.financialGoal);
        var card = ICardMapper.MAP.toEntityWrite(userDTO.card);
        var account = IAccountMapper.MAP.toEntityWrite(userDTO.account);

        var errors = new ArrayList<String>();
        var userResult = userRepository.save(user);
        var userIsCreated = !(userResult.getId() <= 0);
        if (!userIsCreated) throw new RuntimeException("Não foi possivel criar o usuário");

        news.forEach(x -> x.setUserId(user.getId()));
        limit.setUserId(user.getId());
        financial.setUserId(user.getId());
        card.setUserId(user.getId());
        account.setUserId(user.getId());

        newsRepository.saveAll(news);
        limitManagementRepository.save(limit);
        financialGoalRepository.save(financial);
        cardRepository.save(card);
        accountRepository.save(account);

        return userResult.getId();
    }


    public Object AtualizarUsuario(decolatech.api.dto.write.UserDTO userDTO , Long id) {
        var userExists = userRepository.findById(id);
        if (userExists.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }
        if (userDTO.account!=null) {
        userDTO.account.userId = userDTO.id;
        }

        if (userDTO.card!=null) {
        userDTO.card.userId = userDTO.id;
        }

        if (userDTO.financialGoal!=null) {
        userDTO.financialGoal.userId = userDTO.id;
        }

        if (userDTO.limitManagement!=null) {
        userDTO.limitManagement.userId = userDTO.id;
        }

        if (!userDTO.news.isEmpty()) {
        userDTO.news.forEach(x->x.userId = userDTO.id);
        }

        return userRepository.findById(id)
                .map(oldUser -> {
                    var newUser = IUserMapper.MAP.toEntityWrite(userDTO);
                    oldUser = AtualizarDados(oldUser, newUser);

                    var accountEntity = IAccountMapper.MAP.toEntityWrite(userDTO.account);
                    var cardEntity = ICardMapper.MAP.toEntityWrite(userDTO.card);
                    var financialEntity = IFinancialGoalMapper.MAP.toEntityWrite(userDTO.financialGoal);
                    var limitEntity = ILimitManagementMapper.MAP.toEntityWrite(userDTO.limitManagement);
                    var newsEntities = userDTO.news.stream().map(INewsMapper.MAP::toEntity).toList();


                    if (accountEntity != null) {
                        var oldDataAccount = accountRepository.findByUserId(id);
                        var currentAccountId = accountEntity.getId();
                        if (!currentAccountId.equals(oldDataAccount.getId()))
                            throw new RuntimeException("Id da conta não existe");
                    }
                    if (cardEntity != null) {
                        var oldDataCard = cardRepository.findByUserId(id);
                        var currentCardId = cardEntity.getId();
                        if (!currentCardId.equals(oldDataCard.getId()))
                            throw new RuntimeException("Id da conta não existe");
                    }
                    if (financialEntity != null) {
                        var oldDataFinancial = financialGoalRepository.findByUserId(id);
                        var currentFinancialId = financialEntity.getId();
                        if(!currentFinancialId.equals(oldDataFinancial.getId()))
                            throw new RuntimeException("Id da conta não existe");
                    }
                    if (limitEntity != null) {
                        var oldDataLimit = limitManagementRepository.findByUserId(id);
                        var currentLimitId = limitEntity.getId();
                        if (!currentLimitId.equals(oldDataLimit.getId()))
                            throw new RuntimeException("Id da conta não existe");


                    }
                    if (!newsEntities.isEmpty()) {
                        var oldDataNews = newsRepository.findByUserId(id);
                        for (News newsEntity : newsEntities) {
                            if (newsEntity.getId() != null && oldDataNews.stream()
                                    .noneMatch(existing-> existing.getId().equals(newsEntity.getId()))) {
                                throw new RuntimeException();
                            }

                        }
                    }


                })
    }


}

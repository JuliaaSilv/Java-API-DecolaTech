package decolatech.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_Id")
    private Long accountId;

    @Column(name = "card_Id")
    private Long cardId;

    @Column(name = "limit_management_id")
    private Long limitManagementId;

    @Column(name = "financial_goal_id")
    private Long financialGoalId;

    @Column(name = "name")
    private String name;

    @Column(unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(unique = true)
    private String cpf;

    @Column(name = "profile_Picture")
    private String profilePicture;

    @Column(name = "account_creation_date")
    private LocalDateTime accountCreationDate;

    @Column(name = "birth_Date")
    private LocalDate birthDate;

    @Column(name = "password")
    private String password;


    }


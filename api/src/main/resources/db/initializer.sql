-- Inserção de dados para conta
INSERT INTO tb_account (number, agency, balance, additional_limit) VALUES
('12345-6', '001', 1500.50, 500.00), --id= 1
('98765-4', '002', 3500.75, 1200.00),--id=2
('24680-1', '003', 500.00, 100.00), --id=3
('13579-2', '004', 8200.10, 3000.00), --id=4
('11223-5', '005', 920.30, 200.00); --id=5

--Inserção de dados para card

INSERT INTO tb_card (number, available_limit, expiration_date, cvv) VALUES
('4111111111111111', 5000.00, '2026-12-31', '1234'),  --id=1
('5500000000000004', 12000.00, '2025-05-31', '5678'),   --id=2
('340000000000009', 2500.50, '2024-10-30', '2345'),     --id=3
('6011510000000016', 8000.75, '2027-03-15', '9876'),    --id=4
('30000000000004', 1500.00, '2026-07-31', '4321');      --id=5


--Inserção de dados para Financial

INSERT INTO tb_financial_goal (goal_amount, saved_amount, remaining_amount) VALUES
(10000.00, 2500.00, 7500.00), --id=1
(5000.00, 4000.00, 1000.00),   --id=2
(15000.00, 5000.00, 10000.00), --id=3
(20000.00, 12000.00, 8000.00),  --id=4
(7500.00, 3000.00, 4500.00);    --id=5

--Inserção de dados para LimitManagement

INSERT INTO tb_limit_management (max_limit_allowed, requested_increase, increase_status) VALUES
(5000.00, 1500.00, 'Approved'), --id=1
(10000.00, 2000.00, 'Pending'), --id=2
(7000.00, 3000.00, 'Denied'),   --id=3
(12000.00, 5000.00, 'Approved'), --id=4
(8000.00, 1000.00, 'Pending');  --id=5

--Inserção News

INSERT INTO tb_news (tittle, content, user_id) VALUES
('Lançamento de Novo Produto', 'Estamos empolgados em anunciar o lançamento de nosso novo produto que promete revolucionar o mercado!',1), --id=1
('Promoção de Férias', 'Aproveite as promoções de férias com descontos especiais em diversos produtos. Não perca essa oportunidade!', 2), --id=2
('Atualização de Sistema', 'Nosso sistema recebeu uma importante atualização para melhorar a experiência do usuário. A nova versão já está disponível.', 3), --id=3
('Parceria Internacional', 'Firmamos uma parceria estratégica com empresas internacionais para expandir nossos serviços globalmente.', 4), --id=4
('Evento Exclusivo para Clientes', 'Você é nosso convidado especial para um evento exclusivo, com muitos benefícios e surpresas. Marque no calendário!', 3); --id=5

--Inserção User

INSERT INTO tb_user (account_Id, card_Id, limit_management_id, financial_goal_id, name, email, phone, cpf, profile_Picture, account_creation_date, birth_Date, password) VALUES
(1, 2, 3, 4,  'João Silva', 'joao.silva@email.com', '(11) 98765-4321', '123.456.789-01', '/images/profile1.jpg', '2025-03-08 10:15:30', '1990-05-15', 'senha123'), --id=1
(2, 3, 4, 5,  'Maria Oliveira', 'maria.oliveira@email.com', '(21) 99876-5432', '234.567.890-12', '/images/profile2.jpg', '2025-03-08 11:20:45', '1985-10-22', 'senha456'),--id=2
(3, 4, 5, 6, 'Carlos Pereira', 'carlos.pereira@email.com', '(31) 91234-5678', '345.678.901-23', '/images/profile3.jpg', '2025-03-08 12:30:50', '1992-02-17', 'senha789'),--id=3
(4, 5, 6, 7,  'Ana Souza', 'ana.souza@email.com', '(41) 93456-7890', '456.789.012-34', '/images/profile4.jpg', '2025-03-08 13:40:55', '1988-12-05', 'senha101112'),--id=4
(5, 6, 7, 8,  'Pedro Costa', 'pedro.costa@email.com', '(51) 96789-0123', '567.890.123-45', '/images/profile5.jpg', '2025-03-08 14:50:10', '1995-07-30', 'senha131415');--id=5


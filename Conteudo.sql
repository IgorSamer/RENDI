INSERT INTO `escolaridades` (`id`, `nome`) VALUES
(1, 'Médio'),
(2, 'Superior'),
(3, 'Técnico');

INSERT INTO `estados_civis` (`id`, `nome`) VALUES
(1, 'Solteiro'),
(2, 'Casado'),
(3, 'Viúvo'),
(4, 'Divorciado');

INSERT INTO `funcoes` (`id`, `nome`) VALUES
(1, 'Frentista'),
(2, 'Gerente'),
(3, 'Caixa');

INSERT INTO `generos` (`id`, `nome`) VALUES
(1, 'Masculino'),
(2, 'Feminino');

INSERT INTO `setores` (`id`, `nome`) VALUES
(1, 'Caixa'),
(2, 'Lava-car'),
(3, 'Abastecimento'),
(4, 'Garagem'),
(5, 'Gerência');

INSERT INTO `tipos_pagamentos` (`id`, `nome`) VALUES
(1, 'Débito'),
(2, 'Crédito'),
(3, 'À vista');

INSERT INTO `unidades_medidas` (`id`, `nome`) VALUES
(2, 'Caixa'),
(1, 'Litro'),
(3, 'Unidade');

INSERT INTO `clientes` (`id`, `email`, `nome`, `sobrenome`, `rg`, `cpf`, `end_cep`, `end_uf`, `end_cidade`, `end_bairro`, `end_rua`, `end_numero`, `data_nascimento`, `id_genero`) VALUES
(1, 'luh@yahoo.com', 'Luana', 'Souza', '18182921', '99903920', '82229003', 'SC', 'Jundiaí', 'Alternativo', 'Rua da Amargura', 88, '1965-08-25', 2),
(2, 'leo@oi.com', 'Leronardo', 'Jorge', '888883928', '999991829', '83700701', 'PR', 'Curitiba', 'Pinheirinho', 'Rua dos Anônimos', 12, '1995-10-13', 1);

INSERT INTO `clientes_telefones` (`id`, `numero`, `id_cliente`) VALUES
(1, '(31) 99993-1929', 1),
(2, '(31) 99238-2983', 1),
(3, '(32) 98839-0280', 1),
(4, '(41) 99244-2424', 2);

INSERT INTO `funcionarios` (`id`, `email`, `nome`, `sobrenome`, `rg`, `cpf`, `end_cep`, `end_uf`, `end_cidade`, `end_bairro`, `end_rua`, `end_numero`, `data_admissao`, `data_demissao`, `data_nascimento`, `salario`, `foto`, `ativo`, `id_estado_civil`, `id_funcao`, `id_genero`, `id_escolaridade`, `id_setor`) VALUES
(1, 'gerente@rendi.com', 'Irineu', 'Nenheu', '49.138.312-5', '803.287.030-01', '83701700', 'PR', 'Araucária', 'Cachoeira', 'Rua Roque Langer', 257, '2017-05-08', NULL, '1998-12-21', 13000, 'usuario_padrao.png', 1, 1, 2, 1, 2, 5),
(2, 'igor@hotmail.com', 'Igor', 'Remas Cordeiro', '9182989389', '8238938', '83701700', 'PR', 'Araucária', 'Cachoeira', 'Roque Langer', 257, '2017-06-04', '2017-06-22', '1998-12-21', 82739.92, 'usuario_padrao.png', 1, 1, 2, 1, 2, 5);

INSERT INTO `funcionarios_telefones` (`id`, `numero`, `id_funcionario`) VALUES
(1, '(41) 99725-0863', 2),
(2, '(41) 3642-5627', 2);

INSERT INTO `usuarios` (`id`, `usuario`, `senha`, `data`, `ativo`, `id_funcionario`) VALUES
(1, 'Irineu', 'hahaha', '2017-05-09', 1, 1);
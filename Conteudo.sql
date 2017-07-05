INSERT INTO `tipos_pagamentos` (`id`, `nome`) VALUES
(1, 'Débito'),
(2, 'Crédito'),
(3, 'À vista');

INSERT INTO `unidades_medidas` (`id`, `nome`) VALUES
(2, 'Caixa'),
(1, 'Litro'),
(3, 'Unidade');

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

INSERT INTO `combustiveis` (`id`, `nome`, `preco`, `cor`) VALUES
(1, 'Álcool', 2.59, '3949ab'),
(2, 'Gasolina', 3.29, '689f38');

INSERT INTO `orgaos_emissores` (`id`, `nome`) VALUES
(1, 'ANVISA'),
(2, 'NASA');

INSERT INTO `setores` (`id`, `nome`) VALUES
(1, 'Caixa'),
(2, 'Lava-car'),
(3, 'Abastecimento'),
(4, 'Garagem'),
(5, 'Gerência');

INSERT INTO `clientes` (`id`, `email`, `nome`, `sobrenome`, `rg`, `cpf`, `end_cep`, `end_uf`, `end_cidade`, `end_bairro`, `end_rua`, `end_numero`, `data_nascimento`, `id_genero`) VALUES
(1, 'luh@yahoo.com', 'Luana', 'Souza', '18182921', '99903920', '82229003', 'SC', 'Jundiaí', 'Alternativo', 'Rua da Amargura', 88, '1965-08-25', 2),
(2, 'leo@oi.com', 'Leronardo', 'Jorge', '888883928', '999991829', '83700701', 'PR', 'Curitiba', 'Pinheirinho', 'Rua dos Anônimos', 12, '1995-10-13', 1),
(3, 'mlkdocs1.6@hotmail.com', 'Morcego', 'de Souza', '00.000.000-5', '000.000.000-50', '83701700', 'PR', 'Araucária', 'Cachoeira', 'Rua Roque Langer', 257, '1999-06-26', 1),
(4, 'igoremascordeiro@hotmail.com', 'Igor', 'Remas Cordeiro', '00.000.000-8', '000.000.000-09', '83701700', 'PR', 'Araucária', 'Cachoeira', 'Rua Roque Langer', 257, '1998-06-26', 1),
(5, 'leonardofelipegrandi@gmail.com', 'Leonardo', 'Felipe Grandi', '000390.4940-04', '484.542.964-02', '81460307', 'PR', 'Curitiba', 'CIC', 'Rua Barcellos de Freitas', 45, '2017-06-26', 1);

INSERT INTO `clientes_telefones` (`id`, `numero`, `id_cliente`) VALUES
(1, '(31) 99993-1929', 1),
(2, '(31) 99238-2983', 1),
(3, '(32) 98839-0280', 1),
(4, '(41) 99244-2424', 2),
(5, '(41) 99474-0585', 5);

INSERT INTO `fornecedores` (`id`, `email`, `nome_empresarial`, `cnpj`, `nome_fantasia`, `status`, `end_cep`, `end_uf`, `end_cidade`, `end_bairro`, `end_rua`, `end_numero`) VALUES
(1, 'globo@globo.com', 'GLOBO COMUNICACAO E PARTICIPACOES S/A', '27865757000102', 'GCP,TV GLOBO, REDE GLOBO, GLOBO.COM, SOM LIVRE', 1, '22460901', 'RJ', 'Rio de Janeiro', 'Jardim Botânico', 'Jardim Botânico', 303);

INSERT INTO `fornecedores_telefones` (`id`, `numero`, `id_fornecedor`) VALUES
(1, '(21) 2540-2623', 1);

INSERT INTO `funcionarios` (`id`, `email`, `nome`, `sobrenome`, `rg`, `cpf`, `end_cep`, `end_uf`, `end_cidade`, `end_bairro`, `end_rua`, `end_numero`, `data_admissao`, `data_demissao`, `data_nascimento`, `salario`, `foto`, `ativo`, `id_estado_civil`, `id_funcao`, `id_genero`, `id_escolaridade`, `id_setor`) VALUES
(1, 'gerente@rendi.com', 'Irineu', 'Nenheu', '49.138.312-5', '803.287.030-01', '83701700', 'PR', 'Araucária', 'Cachoeira', 'Rua Roque Langer', 257, '2017-05-08', NULL, '1998-12-21', 13000, 'usuario_padrao.png', 1, 1, 2, 1, 2, 5),
(2, 'igor@hotmail.com', 'Igor', 'Remas Cordeiro', '9182989389', '8238938', '83701700', 'PR', 'Araucária', 'Cachoeira', 'Roque Langer', 257, '2017-06-04', '2017-06-22', '1998-12-21', 82739.92, 'usuario_padrao.png', 1, 1, 2, 1, 2, 5);

INSERT INTO `funcionarios_telefones` (`id`, `numero`, `id_funcionario`) VALUES
(1, '(41) 99725-0863', 2),
(2, '(41) 3642-5627', 2);

INSERT INTO `licencas` (`id`, `nome`, `descricao`, `anexo`, `protocolo`, `data_emissao`, `data_vencimento`, `id_orgao_emissor`, `id_funcionario`) VALUES
(1, 'Licença de Funcionamento', 'Primeira licença.', 'anexo', '344453333', '2017-06-26 00:00:00', '2017-07-20 00:00:00', 1, 1),
(2, 'Licença para ir para a Lua', 'Bem louco, né?', 'anexo', '34446495033', '2017-06-26 00:00:00', '2017-07-23 00:00:00', 2, 1);

INSERT INTO `ordens_compra` (`id`, `data`, `data_prevista`, `id_status`, `observacoes`, `valor`, `parcelas`, `distancia_pagamento`, `id_fornecedor`, `id_funcionario`, `id_tipo_pagamento`) VALUES
(1, '2017-06-24 23:06:15', '2017-06-02 00:00:00', 1, NULL, 158.39, 3, 10, 1, 1, 2);

INSERT INTO `produtos` (`id`, `nome`, `descricao`, `preco`, `quantidade`, `foto`, `id_setor`, `id_unidade_medida`, `id_funcionario`) VALUES
(1, 'Óleo', 'Óleo para carro', 45.9, 0, 'Oleo-02.png', 4, 3, 1),
(2, 'Pneu', 'Jogo de pneus para moto', 112.49, 0, 'pneu-on-off-r-34_1.png', 4, 3, 1),
(3, 'Esponja', 'Esponja para lavar', 13.9, 0, 'produto_padrao.png', 2, 3, 1),
(4, 'Óleo para Bicicleta', 'Apenas um simples óleo', 948.44, 0, '722109A6C799CBF89FA53A21E0C6C788.png', 4, 3, 1),
(5, 'Pneu aro 22', 'Um belo pneu', 495.44, 0, '6C11EDF1AB6A75D9774DF473E261C730.png', 4, 3, 1),
(6, 'Teste', 'Sei lá', 293.44, 0, '6AD49060D896A2298016FBE169BAB0EF.png', 2, 1, 1);

INSERT INTO `produtos_ordem_compra` (`id`, `quantidade`, `id_ordem_compra`, `id_produto`) VALUES
(1, 1, 1, 1),
(2, 1, 1, 2);

INSERT INTO `servicos` (`id`, `nome`, `preco`, `id_setor`) VALUES
(1, 'Lavagem de carro', 25, 2),
(2, 'Lavagem de caminhão', 120, 2),
(3, 'Troca de óleo de carro', 12.5, 4),
(4, 'Lavagem de moto', 15, 2);

INSERT INTO `servicos_vendas` (`id`, `data`, `placa`, `quilometragem`, `id_servico`, `id_funcionario`, `id_cliente`) VALUES
(1, '2017-06-26 18:49:39', 'API-2606', 485.55, 1, 1, 5),
(2, '2017-06-26 18:54:44', 'BER-2906', 686.55, 3, 1, 4);

INSERT INTO `usuarios` (`id`, `usuario`, `senha`, `data`, `ativo`, `id_funcionario`) VALUES
(1, 'Irineu', 'hahaha', '2017-05-09', 1, 1);
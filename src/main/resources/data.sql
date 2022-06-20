INSERT INTO categorias(nome, status) VALUES ('INFORMÁTICA', 'ATIVA');
INSERT INTO categorias(nome, status) VALUES ('LIVROS', 'ATIVA');
INSERT INTO categorias(nome, status) VALUES ('FILMES', 'ATIVA');
INSERT INTO categorias(nome, status) VALUES ('MODA', 'INATIVA');

INSERT INTO produtos(nome, descricao, preco_unitario, quantidade_estoque, categoria_id ) VALUES ("Mouse", "Mouse", 30, 3, 1);

INSERT INTO clientes(cpf, bairro, cidade, complemento, estado, numero, rua, nome, telefone)
VALUES (154637485766, "Janta Genebra", "Campinas", "H22", "SP", 366, "Rua da esquina", "Gabriel", "199283747474");

INSERT INTO clientes(cpf, bairro, cidade, complemento, estado, numero, rua, nome, telefone)
VALUES (154637485766, "Janta Genebra", "Campinas", "G06", "SP", 366, "Rua da esquina", "Maria", "190083747474");


INSERT INTO pedidos(data, desconto, tipo_desconto, cliente_id) VALUES ("2022/05/30", "0.00", "NENHUM", 1);

INSERT INTO itens_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id)
VALUES (0.00, 223.6, 3, "NENHUM", 1, 1);


SELECT c.nome, COUNT(item.id) AS quantidade, SUM((item.preco_unitario * item.quantidade)) AS montanteVendido
FROM pedidos
JOIN itens_pedido item
JOIN produtos p
JOIN categorias c
WHERE pedidos.id = item.pedido_id AND item.produto_id = p.id AND p.categoria_id = c.id
GROUP BY c.id, pedidos.id, item.id;


INSERT INTO usuarios(email, senha, cliente_id) VALUES("aluno@exemplo.com", "$2a$10$JhY8lcscK7wotSZJCnNCL..ZmEq.R9TUGPo00Bai1qc4GkczudRTW", 4);





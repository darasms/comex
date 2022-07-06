# Comex

<h2>Documentação dos padrões DDD</h2>


<div>
    <h3>1. Quais padrões táticos do DDD cada classe implementa?</h3>
    <strong>Cliente: </strong> Aggregate Root / Entity <br>
    <strong>CPF</strong> Aggregate / Value Object <br>
    <strong>Telefone: </strong> Aggregate <br>
    <strong>Endereco: </strong> Aggregate <br>
    <strong>Pedido: </strong> Aggregate Root / Entity <br>
    <strong>ItemDePedido: </strong> Aggregate <br>
    <strong>Produto: </strong> Aggregate <br>
    <strong>Categoria: </strong> Entity <br>
    <strong>Descontos</strong> Domain Service


</div>

<div>
    <h3>2. Quais os agregados da aplicação, qual sua raiz e que classes os compõem?</h3>
    <strong>Cliente (Aggregate Root):</strong> <br>
    - CPF <br>
    - Telefone <br>
    - Endereco <br><br>
    <strong>Pedido (Aggregate Root):</strong> <br>
    - ItemDePedido <br>
    - Produto <br><br>
    <strong>Produto (Aggregate Root):</strong> <br>
    -Categoria <br>
</div>


<div>
    <h3>3. Quais contextos delimitados existem?</h3>
    <p>
        Existem 2 contexto compartilhados: <strong>Loja</strong> e <strong>Estoque</strong> <br> 
        e a estratégia de desacoplamento utilizada foi a <strong>núcluo compartilhado</strong>.
    </p>
</div>





# Comex

<h2>Implementação do módulo de Pagamentos</h2>

<div>
    <h3>1. Você criará um serviço separado ou fará no seu projeto atual?</h3>
    <p>Segundo o próprio contexto descrito, a empresa já possui uma organização que permite a 
    adoção da arquitetura distribuida. Percebe-se quando  ela diz que possui uma equipe independente
    para cuidar de um dos seus domínios (sistema de geração de notas fiscais).</p>
    <p>Apesar, de um primeiro momento, a escolha da utilização da técnica que shared kernel foi adotada para 
    desacoplar alguns de nossos domínios, as novas  funcionalidades poderão ser criadas 
    em serviços independentes (caso necessário), pois a estrutura organizacional permite.</p>
    <p>Pensando na nova funcionalidade, ela poderia ser criada separadamente e a utilização 
    das fronteiras baseadas em domínio de negócio (utilização dos padrões DDD) nos ajudariam a construir o novo 
    microsserviço com baixo acoplamento, por exemplo.</p>

</div>

<div>
    <h3>2. O Banco de Dados será separado ou será o mesmo do seu projeto atual?</h3>

<p>Já que a escolha foi utilizar a arquitetura de microsserviços o ideal a se fazer é não compatilhar 
recursos, para manter o baixo acoplamento entre os serviços. Pensando nisso, criaria um novo banco de dados
que ficaria sobre a responsabilidade do novo microsservico.</p>
<p>O tipo de acoplamento de estaríamos adicionando à nossa aplicação, utilizando o mesmo banco de dados, é o Acoplamento de dados em comum
podendo até ser um acoplamneto de conteúdo, o que possui como um agravante o fato de uma eventual mudanca na estrutura do banco afetar mais que um 
microsserviço, ou um microsserviço assumir responsabilidades que não estão no escopo do seu negócio
e infringir um conceito do DDD, no caso, o contexto delimitado (bounded context).</p>

</div>

<div>
    <h3>3. Você precisará de um API Gateway? Se sim, quais as responsabilidades dessa peça da sua arquitetura?</h3>
    <p>Eu poderia criar uma API gateway para a aplicação como um todo para atuar como entrada única para os microsserviços corretos
    quanto para segurança da minha aplicacao.</p>
    <P>E, também uma api gateway para pagamentos para cuidar de redirecionamento das chamadas para o lugar correto.</P>
</div>

<div>
    <h3>4. O sistema de notas fiscais será um projeto separado do de pagamentos ou os times serão unidos?</h3>
    <p>Um time independente, pois desta forma ganhamos produtividade na equipe com os desenvolvimentos de novas features</p>
</div>


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





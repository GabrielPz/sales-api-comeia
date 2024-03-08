# Execução
    mvn spring-boot:run
# Informações
    Para que o sistema de autenticação funcione é necessário ter o keycloak instalado e funcional,
    o mesmo deve estar alocado na porta 8080.

    Com o docker instalado, rode o seguinte comando:
        docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.1 start-dev
        
    Rodar o seguinte comando:
        docker-compose up -d

    Para verificar pode suar o comando:
        docker ps

# Implementações
    Autenticação com o keycloak rodando e funcional

# Possiveis implementações
    Talvez seja melhor tirar o item price da tabela de vendas e colocar numa entity de item
    e ae para calcular o valor da venda basta multiplicar a quantidade de itens vendidos através da FK com o item

    ADICIONAR VALIDAÇÃO PARA QUANDO O ARRAY DE ITENS NA VENDA ESTIVER VAZIO
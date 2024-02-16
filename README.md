# Sistema de Certificação de Estudantes

Este é um projeto que desenvolvi através do NLW EXPERT com o intuito de estudar e pôr em prática minhas habilidades em programação orientada a objetos utilizando a linguagem JAVA. O projeto é uma aplicação API REST onde podemos cadastrar as certificações de alunos junto as alternativas selecionadas para as questões, e então gerar uma nota para eles.

## Funcionalidades Principais

- **Persistência de Dados**: As informações dos estudantes, suas certificações e respostas são armazenadas em um banco de dados.
- **Ranking de Estudantes**: O sistema gera um ranking dos 10 melhores estudantes com base em suas pontuações de certificação.
- **Verificação de Certificação**: Possibilidade de verificar se um estudante já possui certificação em uma determinada tecnologia.

## Tecnologias Utilizadas

- Java
- Spring Framework (Spring Boot, Spring Data JPA)
- Hibernate
- Docker
- PostgreSQL

## Como Executar o Projeto

1. Clone o repositório para o seu ambiente local.
2. Execute o Docker para criar o contêiner do banco de dados PostgreSQL.
3. Configure as propriedades do banco de dados no arquivo `application.properties`.
4. Execute o projeto usando sua IDE preferida ou via linha de comando usando o Maven: `mvn spring-boot:run`.
5. Acesse a aplicação em `http://localhost:8080` (ou outra porta, dependendo da configuração).

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue para relatar bugs, sugerir melhorias ou enviar pull requests.

## Autor

- [Eduardo Zamit](https://github.com/eduardozamit) - Desenvolvedor

## Licença

Este projeto é licenciado sob a [Licença MIT](https://opensource.org/licenses/MIT) - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

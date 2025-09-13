# Sistema gerenciador de músicas.

**Nome:** Bruno Soares Jordão Lopes  
**E-mail:** [brunosoareslopes13@gmail.com]  
**LinkedIn:** [https://www.linkedin.com/in/bruno-soares-lopes/]  
**Telefone:** [(31) 99173-7625]

---

## Recurso Escolhido
O recurso escolhido foi **Música**, com as seguintes propriedades:

| Propriedade        | Tipo       | Obrigatório   | Descrição                                       |
|--------------------|------------|---------------|-------------------------------------------------|
| `id`               | Inteiro    | ✅ Automático | Identificador único da música                  |
| `titulo`           | String     | ✅ Sim        | Nome da música                                 |
| `artista`          | String     | ✅ Sim        | Nome do artista ou banda                       |
| `genero`           | String     | ❌ Não        | Gênero musical                                 |
| `duracao`          | Inteiro    | ✅ Sim        | Duração em segundos (é convertida para mm:ss)  |
| `data_lancamento`  | Data       | ✅ Sim        | Data de lançamento da música                   |
| `data_cadastro`    | Data       | ✅ Automática | Data em que foi cadastrada no sistema          |


---

## Tecnologias Utilizadas

- **Java**
- **SQLite** como banco de dados
- **Docker** para conteinerização
- Interface via **linha de comando**

---

## Instalação e Execução

### Requisitos
- Docker (opcional)
- JDK 21 (Java Development Kit)
- SQLite JDBC Driver (`sqlite-jdbc-3.50.3.0.jar`)
Essas foram as versões utilizadas, mas também pode funcionar em outras.

### Banco de Dados
- A aplicação utiliza **SQLite** como banco de dados embutido, conforme exigido pelo case técnico.

- A estrutura da tabela `musica` está definida no arquivo `schema.sql`, localizado na raiz do projeto.  

- Esse script é carregado e executado automaticamente pela classe `Inicializador.java` ao iniciar a aplicação.

### Executar com Docker
docker build -t gerenciador-musicas .
docker run -it gerenciador-musicas

### Executar Manualmente
#### Com CMD
javac -cp ".;lib\sqlite-jdbc-3.50.3.0.jar" src\dao\MusicaDAO.java src\database\Conexao.java src\database\Inicializador.java src\model\Musica.java src\view\MenuPrincipal.java 

java -cp ".;lib\sqlite-jdbc-3.50.3.0.jar;src" view.MenuPrincipal

#### Com compilador
- Abra o projeto no seu compilador (utilizei o Eclipse)

- No Eclipse vá em project -> properties -> Java Build Path -> Libraries -> clique em Modulepath -> Add External JARs ->      selecione o . jar da pasta lib, aplique e feche.
  
- Execute a classe view.MenuPrincipal

### Funcionalidades
- Cadastrar música: Insere uma nova música com validações

- Listar músicas: Exibe todas as músicas cadastradas

- Buscar por ID: Mostra os dados de uma música específica

- Atualizar música: Modifica os dados de uma música existente

- Deletar música: Remove uma música pelo ID

### Validações Implementadas
- Campos obrigatórios não podem ser vazios

- Duração deve ser um número positivo

- Datas devem seguir o formato DD/MM/YYYY

### Dockerfile
- Incluído na raiz do projeto. Ele compila o código, copia o banco musica.db e executa a aplicação automaticamente.

### Logs
- **Arquivo logs/app.log registra:**

    - Operações realizadas

    - Erros de execução

    - Informações de depuração

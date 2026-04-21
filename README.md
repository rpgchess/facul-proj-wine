# Gerenciador de Vinhos 🍷

## 📌 Sobre o Projeto

Aplicação web Java para gerenciamento de catálogo de vinhos. Sistema CRUD completo desenvolvido como atividade de faculdade para gestão de inventário de adegas e vinícolas.

## 🎯 Objetivo Acadêmico

Projeto de faculdade focado em:
- Desenvolvimento web com Java EE
- CRUD completo (Create, Read, Update, Delete)
- Persistência em banco de dados SQLite
- Utilização de Maven para gestão de dependências
- Padrão MVC (Model-View-Controller)

## 🚀 Tecnologias Utilizadas

- **Java 8+** - Linguagem de programação
- **Maven** - Build tool e gerenciamento de dependências
- **Java Servlets** - Controllers
- **JSP** (JavaServer Pages) - Views
- **SQLite** - Banco de dados embutido
- **HTML/CSS/JavaScript** - Frontend
- **JDBC** - Acesso a banco de dados
- **Bootstrap 4.6** - Framework CSS responsivo
- **jQuery 3.6** - Biblioteca JavaScript
- **AngularJS 1.8** - Framework frontend SPA
- **Gson 2.10** - Serialização JSON

## 📁 Estrutura do Projeto

```
facul-proj-wine/
├── pom.xml                 # Configuração Maven
├── db_wine.db              # Banco de dados SQLite
├── src/
│   ├── main/
│   │   ├── java/           # Código Java
│   │   │   └── br.app/
│   │   │       ├── model/      # Modelos (Wine, Category, etc)
│   │   │       ├── dao/        # Data Access Objects
│   │   │       └── servlet/    # Servlets (Controllers)
│   │   └── webapp/         # Views JSP, CSS, JS
│   │       ├── WEB-INF/
│   │       ├── index.jsp
│   │       └── css/, js/
│   └── test/               # Testes unitários
└── README.md
```

## 🔧 Funcionalidades

### Gerenciamento de Vinhos

- ✅ Cadastrar novo vinho
- ✅ Listar todos os vinhos
- ✅ Buscar vinho por ID/nome
- ✅ Atualizar dados do vinho
- ✅ Excluir vinho

### Informações do Vinho

- **Nome** do vinho
- **Tipo** (Tinto, Branco, Rosé, espumante)
- **Ano** de safra
- **Região/País** de origem
- **Produtor/Vinícola**
- **Preço**
- **Quantidade** em estoque
- **Descrição/Notas**

## 💻 Como Executar

### Pré-requisitos

- **JDK 8+** instalado
- **Maven 3.6+** instalado
- **Apache Tomcat 8.5+** ou outro servidor de aplicação Java EE (opcional)

### Instalação e Execução

#### Opção 1: Com Maven Tomcat Plugin (Recomendado para Desenvolvimento)

```bash
# 1. Clonar repositório
git clone <repository-url>
cd facul-proj-wine

# 2. Compilar projeto
mvn clean compile

# 3. Executar com Tomcat embutido
mvn tomcat7:run

# 4. Acessar aplicação
# Abra o navegador em: http://localhost:9999/wine
```

#### Opção 2: Deploy em Tomcat Standalone

```bash
# 1. Build com Maven
mvn clean package

# 2. O arquivo WAR será gerado em:
# target/wine-1.0.0.war

# 3. Deploy no Tomcat
cp target/wine-1.0.0.war $TOMCAT_HOME/webapps/

# 4. Iniciar Tomcat
$TOMCAT_HOME/bin/startup.sh  # Linux/Mac
$TOMCAT_HOME/bin/startup.bat # Windows

# 5. Acessar aplicação
# http://localhost:8080/wine-1.0.0/
```

#### Opção 3: Com IDE (Eclipse/IntelliJ)

1. Importar projeto como Maven Project
2. Maven > Update Project (baixar dependências)
3. Configurar servidor Tomcat na IDE
4. Run on Server ou usar Maven goal: `tomcat7:run`

### Configuração de Porta

Por padrão, o projeto está configurado para rodar na porta **9999**.
Para alterar, edite `pom.xml`:

```xml
<plugin>
  <groupId>org.apache.tomcat.maven</groupId>
  <artifactId>tomcat7-maven-plugin</artifactId>
  <configuration>
    <port>8080</port> <!-- Altere aqui -->
    <path>/wine</path>
  </configuration>
</plugin>
```

## 💾 Banco de Dados

### Modelo de Dados

```sql
CREATE TABLE wine (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    year INTEGER,
    country VARCHAR(50),
    region VARCHAR(100),
    producer VARCHAR(100),
    price DECIMAL(10,2),
    quantity INTEGER,
    description TEXT
);
```

### Inicialização

O banco de dados SQLite (`db_wine.db`) é criado automaticamente na primeira execução.

## 📚 Exemplo de Uso

### Modelo Wine

```java
public class Wine {
    private Long id;
    private String name;
    private String type;
    private Integer year;
    private String country;
    private String region;
    private String producer;
    private BigDecimal price;
    private Integer quantity;
    private String description;
    
    // Getters e Setters
}
```

### DAO (Data Access Object)

```java
public class WineDAO {
    public void save(Wine wine) { }
    public Wine findById(Long id) { }
    public List<Wine> findAll() { }
    public void update(Wine wine) { }
    public void delete(Long id) { }
}
```

### Servlet (Controller)

```java
@WebServlet("/wine")
public class WineServlet extends HttpServlet {
    private WineDAO wineDAO = new WineDAO();
    
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
            throws ServletException, IOException {
        List<Wine> wines = wineDAO.findAll();
        request.setAttribute("wines", wines);
        request.getRequestDispatcher("/WEB-INF/wine-list.jsp")
               .forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) 
            throws ServletException, IOException {
        // Criar novo vinho
    }
}
```

## 🎓 Conceitos Aprendidos

- ✅ Arquitetura MVC em Java Web
- ✅ Servlets e JSP
- ✅ JDBC e persistência
- ✅ Maven para build e dependências
- ✅ CRUD completo
- ✅ Padrão DAO (Data Access Object)
- ✅ Front Controller pattern

## 📦 Dependências Maven

```xml
<dependencies>
    <!-- Servlet API -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
        <scope>provided</scope>
    </dependency>
    
    <!-- JSP API -->
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>javax.servlet.jsp-api</artifactId>
        <version>2.3.3</version>
        <scope>provided</scope>
    </dependency>
    
    <!-- SQLite JDBC -->
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.x.x</version>
    </dependency>
</dependencies>
```

## ⚙️ Melhorias Implementadas

Este projeto recebeu diversas melhorias de qualidade e atualização:

### ✅ Build e Configuração
- **Maven atualizado** para versão 1.0.0
- **Dependências atualizadas**:
  - SQLite JDBC: 3.25.2 → 3.45.0.0
  - Gson: 2.8.5 → 2.10.1
  - Bootstrap: 4.1.3 → 4.6.2
  - jQuery: 3.3.1 → 3.6.4
  - AngularJS: 1.7.5 → 1.8.3
- **EditorConfig** adicionado para encoding UTF-8
- **Plugins Maven** atualizados e configurados:
  - maven-compiler-plugin: 3.11.0
  - maven-war-plugin: 3.3.2
  - maven-surefire-plugin: 3.0.0
- **JUnit 5** adicionado para testes unitários
- **Properties** configuradas (encoding UTF-8, Java 8)

### ✅ Documentação
- README expandido com seções detalhadas
- Múltiplas opções de execução documentadas
- Exemplos de código Java
- Instruções de configuração de porta
- Conceitos aprendidos destacados

### ✅ Boas Práticas
- Encoding UTF-8 padronizado
- Indentação consistente via EditorConfig
- Path do Tomcat atualizado para `/wine`
- Estrutura Maven padrão

### 🧪 Testes

```bash
# Executar testes unitários
mvn test

# Gerar relatório de cobertura
mvn test jacoco:report
```

### 📋 Próximas Melhorias Sugeridas

- [ ] Implementar autenticação de usuários
- [ ] Adicionar validação de formulários server-side
- [ ] Upload de imagens dos vinhos
- [ ] Sistema de avaliações e reviews
- [ ] Filtros avançados de busca
- [ ] Exportação de relatórios (PDF, Excel)
- [ ] API REST com JSON
- [ ] Migrar para Spring Boot (modernização)

## 👨‍💻 Autor

Claudio Almeida

## 📝 Licença

Projeto acadêmico.

---

> **Nota acadêmica**: Este é um projeto de faculdade para aprendizado de desenvolvimento web com Java EE.


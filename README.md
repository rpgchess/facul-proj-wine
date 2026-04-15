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

- **Java** - Linguagem de programação
- **Maven** - Build tool e gerenciamento de dependências
- **Java Servlets** - Controllers
- **JSP** (JavaServer Pages) - Views
- **SQLite** - Banco de dados
- **HTML/CSS/JavaScript** - Frontend
- **JDBC** - Acesso a banco de dados

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

- **JDK 8+**
- **Maven 3.x**
- **Servidor de aplicação Java** (Tomcat 8+, WildFly, etc)

### Instalação

```bash
# 1. Clonar repositório
git clone <repository-url>
cd facul-proj-wine

# 2. Build com Maven
mvn clean package

# 3. Deploy do WAR
# O arquivo wine-0.0.1.war será gerado em target/
# Copiar para webapps/ do Tomcat ou fazer deploy via IDE
```

### Execução

```bash
# Com Maven
mvn clean install
mvn tomcat7:run

# Acessar
http://localhost:8080/wine
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

## 👨‍💻 Autor

Claudio Almeida

## 📝 Licença

Projeto acadêmico.

---

> **Nota acadêmica**: Este é um projeto de faculdade para aprendizado de desenvolvimento web com Java EE.


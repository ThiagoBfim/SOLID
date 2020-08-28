
### Single Responsibility Principle

O esperado dessa demanda é separar o Main em algumas outras classes, 
para tentar garantir uma responsabilidade única por classe.

Também é extremamente importante que tenha um teste para garantir que 
a reforação não altere o comportamento do sistema.


----------------------------------------------------------

### Clean Architecture

![Clean Architecture](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

Iniciando a separação da logica para utilizar O.O, criando entidades de dominio.


----------------------------------------------------------

#### Dependency Inversion Principle 

O Core do nosso software estava dependendo de casos de uso, de regras da aplicação.
O Gerador estava com pacotes do CLI e do Gerador, o impacto disso é o seguinte:
- Se precisarmos alterar os ParametrosExternos para ser a Web por exemplo, não seria possível, 
pois estamos dependendo de uma implementação e não de uma interface.
- Se precisarmos criar um Gerador de DOCx precisariamos alterar a classe principal do sistema, o impacto seria maior.

Com a solução proposta, faremos factory e interfaces para inverter a dependencia dos modulos.


----------------------------------------------------------

### Interface Segregation Principle

Deve ser criado dois temas para a geração dos ebooks, sendo eles:

##### 1º
``` 
h1	{
    border-bottom: 1px	dashed	black;
    font-size: 3em;
}
h2	{
    border-left: 1px	solid	black;
    padding-left: 5px;
    border-bottom: 1px	solid	black;
}

blockquote {
    border: 1px solid black;
    padding: 5px;
}
```

##### 2º 

```
li	{
    border-bottom: 0.5px	dashed	red;
}

```

Para isso, utilizaremos o principio de segregação de interface, para assegurar
que nossa aplicação dependa de uma interface, e não de uma implementação.
Então, se precisarmos futuramente expor essa API, alguém poderia apenas criar a implementação
dessa interface e tudo funcionaria.

Para que isso funcione  precisaremos utilizar o ServiceLoader para que a aplicação
consiga detectar a implementação de um projeto externo.

#### Parte 2:

O cliente solicitou que fosse liberado a lista capitulos, pois há algumas páginas na qual ele precisa
adicionar o rodapé.

Para isso vamos criar um método na nossa interface para que o usuário possa adicionar
esse comportamento.

### Liskov Substitution  Principle

O sistema estava violando alguns principios.

Com a refatoração de extrair o método de ação antes da geração do PDF para outra
classe, passamos a respeitar o Interface Segregation Principle, 
que diz que Clientes não devem ser obrigados a depender de métodos que não usam.

E também aplicamos o Liskov Substituiton Principle, 
que diz que os subtipos devem ser substituíveis por seus tipos base.

Antes todos eram obrigados a implementar o método "acaoAntesGeracaoPDF", 
na qual permitiam trazer algum comporamento indevido, como por exemplo:

<code>throw new UnsupportedOperationException();</code>

### Open and Closed Principle 




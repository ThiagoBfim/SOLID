
#### Single Responsibility Principle

O esperado dessa demanda é separar o Main em algumas outras classes, 
para tentar garantir uma responsabilidade única por classe.

Também é extremamente importante que tenha um teste para garantir que 
a reforação não altere o comportamento do sistema.


----------------------------------------------------------

#### Clean Architecture

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

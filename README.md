# Projeto de Cupom

Este é um projeto Java que implementa um sistema de cupons. Ele usa Java 17, Spring MVC, Spring Data MONGO,
Lombok e foi desenvolvido no IntelliJ IDEA.

## Estrutura do Projeto

O projeto consiste em várias classes que gerenciam a funcionalidade de cupons:

1. `ApplyCouponRequest.java` - Esta classe é usada para fazer solicitações para aplicar um cupom.
2. `ApplyCouponResponse.java` - Esta classe é usada para lidar com a resposta após a aplicação de um cupom.
3. `Coupon.java` - É a classe de modelo que representa um cupom.
4. `CouponController.java` - Esta é a classe controladora que gerencia todas as operações relacionadas a cupons.
5. `CouponMapper.java` - É usado para mapear entre objetos de Coupon e DTOs.
6. `CouponRepository.java` - Esta é a interface do repositório que interage com o banco de dados Mongo.
7. `CouponRequest.java` - Esta classe é usada para fazer solicitações para criar um novo cupom.
8. `CouponResponse.java` - Esta classe é usada para lidar com a resposta após a criação de um novo cupom.
9. `CouponService.java` - É a interface de serviço que define todos os métodos para lidar com operações de cupom.
10. `CouponServiceImpl.java` - Esta é a classe que implementa a interface do serviço de cupom.

## Como Configurar

Para configurar o projeto no seu ambiente local:

1. Clone o repositório para a sua máquina local
2. Abra o IntelliJ IDEA (mais simples) e importe o projeto
3. Certifique-se de ter Java 17 instalado e configurado corretamente
4. Inicie o seu servidor MongoDB
5. Rode o projeto
# Java e a Arte da Abstracao com Classes e Encapsulamento

Repositorio de estudos, exemplos e exercicios praticos desenvolvidos durante o curso **Java e a Arte da Abstracao com Classes e Encapsulamento**, integrante do **Bootcamp Itau - Java com Inteligencia Artificial** na [DIO](https://dio.me).

---

## Sobre o Conteudo

Este modulo aprofunda a transicao da programacao estruturada para o paradigma da **Programacao Orientada a Objetos (POO)** em Java 21, focando em modelagem consistente, protecao de estado e regras de negocio.

### Pilares e Conceitos Abordados

1. **Abstracao (`01-abstracao-e-classes/`):**
   - Modelagem de entidades do mundo real em classes de software.
   - Separacao clara entre estado (atributos) e comportamento (metodos).
   - Diferenca entre classe (molde) e objeto (instancia na memoria heap).

2. **Encapsulamento e Modificadores de Acesso (`02-encapsulamento-e-modificadores/`):**
   - Ocultamento de informacao (*information hiding*) com atributos `private`.
   - Protecao de integridade via metodos de negocio (ex: depositar e sacar) em vez de simples setters ingenuos.
   - Modificadores de visibilidade: `private`, `default`, `protected` e `public`.

3. **Construtores e Palavra-chave `this` (`03-construtores-e-this/`):**
   - Inicializacao obrigatoria de objetos garantindo estado inicial valido.
   - Sobrecarga de construtores (*constructor overloading*).
   - Uso de `this` para desambiguacao de escopo e chamadas encadeadas.

4. **Recursos Modernos do Java - Records (`04-recursos-modernos-records/`):**
   - Uso de `record` para criacao de DTOs imutaveis sem boilerplate.
   - Construtores compactos para validacao de parametros.

5. **Dominio de Estoque (`05-exercicios-praticos/`):**
   - Implementacao de classe de dominio rica com regras de estoque e calculos derivados.

---

## Exercicios Praticos com Menu Interativo (`exercicios-fixacao-poo/`)

Todos os exercicios contam com menu interativo via terminal (`Scanner`), tratamento de entradas e opcao de encerramento (`Sair`):

| Exercicio | Arquivo | Descricao e Regras |
|---|---|---|
| **01. Banco Digital** | `Exercicio01ContaBancariaApp.java` | Gerenciamento de conta bancaria com limite de cheque especial proporcional ao deposito inicial (R$ 50 fixo ou 50%). Cobranca automatica de taxa de 20% sobre o cheque utilizado assim que novos depositos entram. |
| **02. Controle de Carro Manual** | `Exercicio02CarroApp.java` | Simulador de cambio manual com 6 marchas + ponto morto. Regras estritas de velocidade por marcha, proibicao de pular marchas, controle de manobras (virar apenas entre 1 e 40 km/h) e desligamento seguro. |
| **03. Maquina de Banho PetShop** | `Exercicio03PetShopApp.java` | Controle de insumos (agua e shampoo com capacidades maximas de 30L e 10L), abastecimento controlado, banho individual e esterilizacao obrigatoria caso o pet saia sem estar limpo. |

---

## Estrutura de Diretorios

```text
.
├── 01-abstracao-e-classes/
│   ├── Carro.java
│   └── MainAbstracao.java
├── 02-encapsulamento-e-modificadores/
│   ├── ContaBancaria.java
│   └── MainEncapsulamento.java
├── 03-construtores-e-this/
│   ├── Cliente.java
│   └── MainConstrutores.java
├── 04-recursos-modernos-records/
│   ├── MainRecords.java
│   └── TransacaoRecord.java
├── 05-exercicios-praticos/
│   ├── GerenciadorEstoqueApp.java
│   └── Produto.java
├── exercicios-fixacao-poo/
│   ├── Exercicio01ContaBancariaApp.java
│   ├── Exercicio02CarroApp.java
│   └── Exercicio03PetShopApp.java
└── README.md
```

---

## Como Compilar e Executar

Requisitos: **JDK 21** ou superior instalado.

```bash
# Compilar todos os arquivos
javac 01-abstracao-e-classes/*.java \
      02-encapsulamento-e-modificadores/*.java \
      03-construtores-e-this/*.java \
      04-recursos-modernos-records/*.java \
      05-exercicios-praticos/*.java \
      exercicios-fixacao-poo/*.java

# Executar qualquer exercicio interativo (exemplo: Carro)
java exercicios_fixacao_poo.Exercicio02CarroApp
```

---
Autor: **[Gabriel Felippe Baptista](https://github.com/GabrielF1100)**
Bootcamp Itau - Java com Inteligencia Artificial (DIO)

package abstracao;

/**
 * ============================================================================
 * GUIA DEFINITIVO DE POO - PARTE 1: O PILAR DA ABSTRAÇÃO E ANATOMIA DA CLASSE
 * ============================================================================
 * 
 * 1. O QUE É POO (PROGRAMAÇÃO ORIENTADA A OBJETOS)?
 * ------------------------------------------------
 * É um paradigma de desenvolvimento onde o software é modelado como um conjunto
 * de objetos que conversam entre si, trocando mensagens (chamando métodos).
 * Na programação estruturada/procedural antiga, tínhamos dados soltos de um lado
 * e funções soltas do outro. Na POO, DADOS e COMPORTAMENTOS ficam unidos.
 * 
 * 2. O QUE É O PILAR DA ABSTRAÇÃO?
 * --------------------------------
 * Abstrair significa "isolar o essencial e ignorar o supérfluo".
 * No mundo real, um carro tem milhares de peças (velas de ignição, fios, pistões).
 * Mas para o nosso sistema de software, nós ABSTRAÍMOS e escolhemos apenas:
 * - O que o carro TEM (Atributos de Estado): marca, modelo, velocidade, ligado.
 * - O que o carro FAZ (Métodos de Comportamento): ligar, desligar, acelerar, frear.
 * 
 * 3. CLASSE vs OBJETO (A GRANDE DIFERENÇA):
 * -----------------------------------------
 * - CLASSE: É a planta baixa, o molde ou a receita de bolo. Ela não anda nem gasta
 *   combustível, apenas define como todo carro deve ser construído na memória.
 * - OBJETO (Instância): É o carro real fabricado a partir da planta. Fica alocado
 *   na memória HEAP do computador, possuindo seus próprios valores específicos.
 */
public class Carro {

    // ========================================================================
    // ATRIBUTOS DE INSTÂNCIA (ESTADO DO OBJETO)
    // ========================================================================
    // Representam as características que descrevem o objeto na memória.
    // Cada novo objeto 'Carro' criado terá sua própria cópia independente destes atributos.
    public String marca;
    public String modelo;
    public boolean ligado;
    public int velocidade;

    // ========================================================================
    // MÉTODOS DE INSTÂNCIA (COMPORTAMENTOS E REGRAS DE NEGÓCIO)
    // ========================================================================
    // São as ações que o objeto sabe executar.
    // Boas práticas de POO: métodos devem alterar o estado interno do objeto
    // e proteger o sistema contra estados inconsistentes (ex: carro em 200 km/h desligado).

    /**
     * Altera o estado do atributo 'ligado' para true.
     */
    public void ligar() {
        if (!ligado) {
            ligado = true;
            System.out.println("O " + modelo + " deu a partida! Vrummm 🚗💨");
        } else {
            System.out.println("O carro já está ligado.");
        }
    }

    /**
     * Regra de consistência de estado:
     * Um carro não pode ser desligado em movimento sem parar primeiro.
     */
    public void desligar() {
        if (ligado && velocidade == 0) {
            ligado = false;
            System.out.println("O carro foi desligado com segurança.");
        } else if (velocidade > 0) {
            System.out.println("⚠️ Perigo! Não é possível desligar o carro em movimento (" + velocidade + " km/h)!");
        } else {
            System.out.println("O carro já estava desligado.");
        }
    }

    /**
     * Método com parâmetro: recebe a intensidade da aceleração.
     * Dependência de estado: só acelera se o carro estiver previamente ligado.
     */
    public void acelerar(int limiteIncremento) {
        if (ligado) {
            velocidade += limiteIncremento;
            System.out.println("Acelerando para " + velocidade + " km/h.");
        } else {
            System.out.println("Não é possível acelerar: o carro está desligado!");
        }
    }

    /**
     * Decrementa a velocidade garantindo que nunca fique negativa.
     */
    public void frear(int decremento) {
        if (velocidade > 0) {
            velocidade -= decremento;
            if (velocidade < 0) velocidade = 0;
            System.out.println("Freando. Velocidade atual: " + velocidade + " km/h.");
        } else {
            System.out.println("O carro já está totalmente parado.");
        }
    }
}

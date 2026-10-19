package estoque;

/**
 * ============================================================================
 * GUIA DEFINITIVO DE POO - PARTE 5: APLICAÇÃO PRÁTICA E REGRAS DE DOMÍNIO
 * ============================================================================
 * 
 * Este exemplo reúne todos os conceitos anteriores em um caso real de negócio:
 * - Abstração: Representando um Produto de Estoque no mundo digital.
 * - Encapsulamento: Blindagem dos atributos com 'private'.
 * - Métodos Ricos: Em vez de apenas setQuantidade(), temos 'adicionarEstoque()'
 *   e 'baixarEstoque()', que contêm regras e validações consistentes.
 * - Métodos de Cálculo de Negócio: 'calcularValorTotalEmEstoque()'.
 */
public class Produto {

    // 1. Atributos Privados (Estado protegido)
    private String codigo;
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    // 2. Construtor: Inicialização controlada
    public Produto(String codigo, String nome, double preco, int quantidadeInicial) {
        this.codigo = codigo;
        this.nome = nome;
        setPreco(preco); // Usa o setter para já passar pela validação de preço positivo
        this.quantidadeEstoque = Math.max(0, quantidadeInicial); // Garante que nunca nasça negativo
    }

    // 3. Getters e Setters com regras
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        } else {
            System.err.println("Preço não pode ser negativo. Atribuído 0.0.");
            this.preco = 0.0;
        }
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    // 4. Comportamentos de Negócio (Regras de Domínio)

    /**
     * Entrada de mercadoria: só aceita quantidades positivas.
     */
    public void adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeEstoque += quantidade;
            System.out.println("✅ +" + quantidade + " unidades de " + nome + " adicionadas ao estoque.");
        } else {
            System.out.println("Quantidade para entrada deve ser maior que zero.");
        }
    }

    /**
     * Saída/Venda de mercadoria:
     * Verifica se há estoque suficiente para evitar inconsistência (saldo negativo).
     */
    public boolean baixarEstoque(int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade para saída deve ser maior que zero.");
            return false;
        }

        if (this.quantidadeEstoque >= quantidade) {
            this.quantidadeEstoque -= quantidade;
            System.out.println("✅ -" + quantidade + " unidades de " + nome + " retiradas do estoque.");
            return true;
        } else {
            System.out.println("❌ Estoque insuficiente! Disponível: " + this.quantidadeEstoque + " unidades.");
            return false;
        }
    }

    /**
     * Método de cálculo dinâmico:
     * Não precisamos de um atributo 'valorTotal' salvo na memória,
     * pois ele pode ser derivado a qualquer instante de (preço * quantidade).
     */
    public double calcularValorTotalEmEstoque() {
        return this.preco * this.quantidadeEstoque;
    }
}

package encapsulamento;

/**
 * ============================================================================
 * GUIA DEFINITIVO DE POO - PARTE 2: O PILAR DO ENCAPSULAMENTO E ACESSO SEGURO
 * ============================================================================
 * 
 * 1. O QUE É ENCAPSULAMENTO?
 * --------------------------
 * Encapsular vem de "colocar dentro de uma cápsula". Significa ocultar os detalhes
 * internos do objeto e proteger seus dados contra modificações diretas ou perigosas
 * vindas de fora da classe. É o princípio de "Ocultamento de Informação" (Information Hiding).
 * 
 * 2. O PERIGO DE NÃO ENCAPSULAR:
 * ------------------------------
 * Se 'saldo' fosse 'public double saldo', qualquer classe externa poderia fazer:
 * conta.saldo = -50000.00; // Ou pior: conta.saldo = 99999999.00;
 * Isso destruiria toda a integridade do sistema financeiro sem passar por nenhuma validação!
 * 
 * 3. A REGRA DE OURO DO ENCAPSULAMENTO:
 * -------------------------------------
 * - Atributos quase SEMPRE devem ser marcados como 'private'.
 * - O mundo exterior só pode ler valores através de GETTERS.
 * - Modificações de valor só ocorrem através de SETTERS com validações ou métodos
 *   de domínio de negócio (ex: 'depositar' e 'sacar').
 * 
 * 4. MODIFICADORES DE ACESSO EM JAVA:
 * -----------------------------------
 * - private:   Apenas a própria classe enxerga e altera. (Nível máximo de proteção).
 * - (default): Visível apenas por classes dentro do mesmo pacote (package-private).
 * - protected: Visível no mesmo pacote e por subclasses que herdam desta classe.
 * - public:    Acesso livre por qualquer classe do projeto.
 */
public class ContaBancaria {

    // ========================================================================
    // ATRIBUTOS PRIVADOS (BLINDAGEM DE ESTADO)
    // ========================================================================
    // Nenhum código fora deste arquivo pode ler ou escrever diretamente nestas variáveis.
    private String titular;
    private String numeroConta;
    private double saldo;

    // ========================================================================
    // CONSTRUTOR: GARANTIA DE CONSISTÊNCIA INICIAL
    // ========================================================================
    // Em POO, o construtor é o guardião da criação do objeto.
    // Ele impede que uma conta nasça sem dono ou com saldo negativo inválido.
    public ContaBancaria(String titular, String numeroConta, double saldoInicial) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        
        // Validação da regra de negócio na própria inicialização
        if (saldoInicial >= 0) {
            this.saldo = saldoInicial;
        } else {
            System.err.println("Saldo inicial não pode ser negativo. Inicializado com R$ 0.0");
            this.saldo = 0.0;
        }
    }

    // ========================================================================
    // MÉTODOS DE LEITURA (GETTERS)
    // ========================================================================
    // Permitem que o mundo exterior consulte o estado sem perigo de modificá-lo.

    public String getTitular() {
        return titular;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    // ========================================================================
    // MÉTODOS DE NEGÓCIO (EM VEZ DE 'setSaldo', TEMOS REGRAS REAIS)
    // ========================================================================
    // RESSALVA IMPORTANTE DE POO:
    // Evite criar 'setSaldo(double novoSaldo)'! Se você criar um setter ingênuo,
    // você abre a brecha de segurança novamente.
    // Prefira criar métodos que expressam ações reais do mundo do negócio:

    /**
     * Deposita dinheiro na conta apenas se o valor for positivo.
     */
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.printf("Depósito de R$ %.2f realizado. Saldo atual: R$ %.2f\n", valor, this.saldo);
        } else {
            System.out.println("❌ Valor de depósito inválido! Deve ser maior que zero.");
        }
    }

    /**
     * Saque seguro: verifica se o valor é positivo E se há saldo disponível.
     * Devolve true se a operação foi autorizada e false caso contrário.
     */
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("❌ Valor de saque deve ser positivo.");
            return false;
        }

        if (this.saldo >= valor) {
            this.saldo -= valor;
            System.out.printf("✅ Saque de R$ %.2f autorizado. Saldo atual: R$ %.2f\n", valor, this.saldo);
            return true;
        } else {
            System.out.printf("❌ Saldo insuficiente! Saldo disponível: R$ %.2f | Tentativa: R$ %.2f\n", this.saldo, valor);
            return false;
        }
    }
}

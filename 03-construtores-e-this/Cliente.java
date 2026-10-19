package construtores;

/**
 * ============================================================================
 * GUIA DEFINITIVO DE POO - PARTE 3: CONSTRUTORES, SOBRECARGA E A PALAVRA 'this'
 * ============================================================================
 * 
 * 1. O QUE É UM CONSTRUTOR?
 * -------------------------
 * É um método especial responsável por inicializar um objeto no momento em que
 * usamos a palavra-chave 'new'.
 * REGRAS SINTÁTICAS DO CONSTRUTOR:
 * - Tem EXATAMENTE o mesmo nome da classe (com inicial maiúscula).
 * - NÃO possui tipo de retorno (nem mesmo 'void').
 * 
 * 2. PARA QUE SERVE O CONSTRUTOR?
 * --------------------------------
 * Em POO, um construtor garante a integridade de nascimento do objeto.
 * Se um 'Cliente' no banco OBRIGATORIAMENTE precisa de CPF e Nome, nós não
 * permitimos que ele seja instanciado sem esses dados.
 * 
 * 3. O QUE É A PALAVRA-CHAVE 'this'?
 * ----------------------------------
 * 'this' é uma referência que aponta para a INSTÂNCIA ATUAL do próprio objeto.
 * Ele serve para dois propósitos principais:
 * a) Desambiguação de Escopo: diferenciar o atributo da classe do parâmetro do método:
 *    this.nome = nome;
 *    (Onde 'this.nome' é o atributo do objeto, e 'nome' é o parâmetro recebido).
 * b) Chamada Encadeada de Construtores: usar 'this(...)' para chamar outro construtor da mesma classe.
 * 
 * 4. SOBRECARGA DE CONSTRUTORES (CONSTRUCTOR OVERLOADING):
 * --------------------------------------------------------
 * Uma classe pode ter mais de um construtor, desde que a lista de parâmetros
 * seja diferente (tipos ou quantidade). Isso dá flexibilidade na hora de criar o objeto.
 */
public class Cliente {

    // Atributos privados
    private String cpf;
    private String nome;
    private String email;

    // ========================================================================
    // CONSTRUTOR 1: COMPLETO (RECEBE TODOS OS DADOS)
    // ========================================================================
    public Cliente(String cpf, String nome, String email) {
        // 'this.cpf' refere-se ao atributo privado acima.
        // 'cpf' refere-se à variável local recebida no parâmetro.
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    // ========================================================================
    // CONSTRUTOR 2: SOBRECARREGADO (SEM E-MAIL OBRIGATÓRIO)
    // ========================================================================
    // Permite cadastrar um cliente rápido apenas com CPF e Nome.
    // Usamos 'this(...)' na primeira linha para reaproveitar a lógica do construtor completo!
    public Cliente(String cpf, String nome) {
        this(cpf, nome, "Não informado"); // Chama o Construtor 1 automaticamente
    }

    // ========================================================================
    // GETTERS E SETTERS
    // ========================================================================
    // Note que CPF e Nome NÃO possuem 'setter' de propósito!
    // Uma vez registrado o CPF e Nome de um cliente no banco, eles não devem mudar arbitrariamente.
    // Isso é um exemplo prático de IMUTABILIDADE parcial via encapsulamento.

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    // O e-mail pode ser atualizado com o tempo, por isso possui setter:
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.err.println("Formato de e-mail inválido.");
        }
    }

    public void exibirFicha() {
        System.out.println("┌───────────────────────────────────┐");
        System.out.println("│ FICHA DO CLIENTE (POO)            │");
        System.out.println("├───────────────────────────────────┤");
        System.out.println("│ Nome:  " + String.format("%-26s", nome) + " │");
        System.out.println("│ CPF:   " + String.format("%-26s", cpf) + " │");
        System.out.println("│ Email: " + String.format("%-26s", email) + " │");
        System.out.println("└───────────────────────────────────┘");
    }
}

package records;

import java.time.LocalDateTime;

/**
 * ============================================================================
 * GUIA DEFINITIVO DE POO - PARTE 4: JAVA MODERNO, RECORDS E IMUTABILIDADE
 * ============================================================================
 * 
 * 1. O QUE SÃO DTOs (DATA TRANSFER OBJECTS)?
 * ------------------------------------------
 * No dia a dia de sistemas backend (especialmente com APIs REST e microsserviços),
 * precisamos de classes que servem apenas como "pacotes de dados" para transportar
 * informações de um lugar para outro (ex: do banco de dados para a tela).
 * 
 * 2. O PROBLEMA HISTÓRICO DAS CLASSES TRADICIONAIS (BOILERPLATE):
 * ---------------------------------------------------------------
 * Antigamente, para criar uma simples classe com 5 campos, tínhamos que escrever:
 * - 5 atributos private final
 * - 1 construtor gigante
 * - 5 métodos getters
 * - Método equals() e hashCode() manuais
 * - Método toString() manual
 * Eram mais de 60 linhas de código repetitivo!
 * 
 * 3. A SOLUÇÃO MODERNA: 'record' (JAVA 14 / 16 / 17 / 21)
 * -------------------------------------------------------
 * Um 'record' é uma classe concisa e 100% IMUTÁVEL.
 * Ao declarar a linha 'public record TransacaoRecord(...)':
 * - O compilador cria todos os atributos como 'private final'.
 * - Cria o construtor canônico com todos os parâmetros.
 * - Cria métodos de acesso sem o prefixo 'get' (ex: transacao.valor()).
 * - Cria implementações perfeitas de equals(), hashCode() e toString().
 * 
 * 4. IMUTABILIDADE:
 * -----------------
 * Um objeto imutável não pode ter seus dados alterados após a criação.
 * VANTAGENS:
 * - Totalmente seguro para programação concorrente e assíncrona (Thread-Safe).
 * - Sem efeitos colaterais acidentais no sistema.
 */
public record TransacaoRecord(
    String id,
    String contaOrigem,
    String contaDestino,
    double valor,
    LocalDateTime dataHora
) {
    // ========================================================================
    // COMPACT CONSTRUCTOR (CONSTRUTOR COMPACTO)
    // ========================================================================
    // Recurso exclusivo dos Records:
    // Permite validar os dados recebidos antes de atribuir aos campos,
    // sem precisar repetir 'this.id = id; this.valor = valor;'.
    public TransacaoRecord {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser estritamente positivo!");
        }
        if (contaOrigem == null || contaDestino == null) {
            throw new IllegalArgumentException("As contas de origem e destino são obrigatórias.");
        }
    }
}

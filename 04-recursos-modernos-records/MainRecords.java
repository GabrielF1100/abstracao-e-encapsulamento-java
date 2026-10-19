package records;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

/**
 * Aula: Testando Records na Prática
 */
public class MainRecords {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("=== CRIANDO UMA TRANSAÇÃO BANCÁRIA (RECORD) ===");

        System.out.print("Conta de Origem: ");
        String origem = scanner.nextLine();

        System.out.print("Conta de Destino: ");
        String destino = scanner.nextLine();

        System.out.print("Valor da transferência (R$): ");
        double valor = scanner.nextDouble();

        String idTransacao = UUID.randomUUID().toString().substring(0, 8);

        // Criando a instância do Record
        TransacaoRecord transacao = new TransacaoRecord(
            idTransacao,
            origem,
            destino,
            valor,
            LocalDateTime.now()
        );

        System.out.println("\n--- DETALHES DA TRANSAÇÃO (GERADA PELO RECORD) ---");
        // Veja como os métodos de acesso não precisam do 'get'
        System.out.println("ID:            " + transacao.id());
        System.out.println("Origem:        " + transacao.contaOrigem());
        System.out.println("Destino:       " + transacao.contaDestino());
        System.out.println("Valor:         R$ " + transacao.valor());
        System.out.println("Data/Hora:     " + transacao.dataHora());

        // O toString() é gerado automaticamente pelo compilador!
        System.out.println("\ntoString() automático do Java:");
        System.out.println(transacao);

        scanner.close();
    }
}

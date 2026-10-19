package encapsulamento;

import java.util.Locale;
import java.util.Scanner;

/**
 * Aula: Testando o Encapsulamento
 * Demonstra como a classe exterior não consegue burlar o saldo da conta.
 */
public class MainEncapsulamento {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("=== SISTEMA BANCÁRIO (ENCAPSULAMENTO SEGURO) ===");
        System.out.print("Nome do titular: ");
        String titular = scanner.nextLine();

        System.out.print("Número da conta: ");
        String numConta = scanner.nextLine();

        System.out.print("Depósito de abertura (R$): ");
        double saldoInicial = scanner.nextDouble();

        ContaBancaria conta = new ContaBancaria(titular, numConta, saldoInicial);

        System.out.println("\nConta aberta para: " + conta.getTitular());
        System.out.println("Saldo seguro: R$ " + conta.getSaldo());

        // Operação 1: Depósito
        System.out.print("\nQuanto deseja depositar? R$ ");
        double dep = scanner.nextDouble();
        conta.depositar(dep);

        // Operação 2: Saque
        System.out.print("\nQuanto deseja sacar? R$ ");
        double saq = scanner.nextDouble();
        conta.sacar(saq);

        // RESSALVA PEDAGÓGICA:
        // A linha abaixo é PROIBIDA pelo compilador porque 'saldo' é private:
        // conta.saldo = 1000000.0; // ERRO: saldo has private access in ContaBancaria

        System.out.println("\nSaldo final verificado via Getter: R$ " + conta.getSaldo());

        scanner.close();
    }
}

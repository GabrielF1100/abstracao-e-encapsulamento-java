package construtores;

import java.util.Scanner;

/**
 * Aula: Testando Construtores
 */
public class MainConstrutores {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CADASTRO DE CLIENTES COM CONSTRUTORES ===");
        System.out.print("Informe o CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Informe o Nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Deseja informar o e-mail agora? (S/N): ");
        String opt = scanner.nextLine().trim().toUpperCase();

        Cliente cliente;
        if (opt.equals("S")) {
            System.out.print("Digite o e-mail: ");
            String email = scanner.nextLine();
            // Invocando o construtor completo
            cliente = new Cliente(cpf, nome, email);
        } else {
            // Invocando o construtor sobrecarregado (sem email)
            cliente = new Cliente(cpf, nome);
        }

        System.out.println("\nCliente instanciado com sucesso:");
        cliente.exibirFicha();

        scanner.close();
    }
}

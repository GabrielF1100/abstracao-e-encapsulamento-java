package estoque;

import java.util.Locale;
import java.util.Scanner;

/**
 * Exercício Prático: Sistema Interativo de Gerenciamento de Estoque
 */
public class GerenciadorEstoqueApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("=== CADASTRO DE PRODUTO NO ESTOQUE ===");
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço unitário (R$): ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade inicial em estoque: ");
        int qtd = scanner.nextInt();

        Produto produto = new Produto(codigo, nome, preco, qtd);

        System.out.println("\nProduto cadastrado com sucesso!");
        System.out.printf("Valor total do inventário deste item: R$ %.2f\n", produto.calcularValorTotalEmEstoque());

        // Testando operações
        System.out.print("\nQuantas unidades deram entrada (adicionar)? ");
        int entrada = scanner.nextInt();
        produto.adicionarEstoque(entrada);

        System.out.print("Quantas unidades foram vendidas (baixar)? ");
        int saida = scanner.nextInt();
        produto.baixarEstoque(saida);

        System.out.println("\n--- RELATÓRIO FINAL DO PRODUTO ---");
        System.out.println("Item:               " + produto.getNome());
        System.out.println("Saldo em estoque:   " + produto.getQuantidadeEstoque() + " unidades");
        System.out.printf("Patrimônio total:   R$ %.2f\n", produto.calcularValorTotalEmEstoque());

        scanner.close();
    }
}

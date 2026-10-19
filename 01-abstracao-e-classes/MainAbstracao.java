package abstracao;

import java.util.Scanner;

/**
 * Aula: Instanciação de Objetos e Execução
 * Demonstra a criação de múltiplos objetos a partir de um mesmo modelo (Classe).
 */
public class MainAbstracao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando a primeira instância do objeto na memória Heap
        Carro meuCarro = new Carro();

        System.out.println("=== 1. MODELANDO E INSTANCIANDO UM CARRO ===");
        System.out.print("Digite a marca do carro (ex: Toyota): ");
        meuCarro.marca = scanner.nextLine();

        System.out.print("Digite o modelo do carro (ex: Corolla): ");
        meuCarro.modelo = scanner.nextLine();

        System.out.println("\nCarro registrado com sucesso: " + meuCarro.marca + " " + meuCarro.modelo);

        // Interagindo com os métodos da abstração
        meuCarro.ligar();
        meuCarro.acelerar(40);
        meuCarro.acelerar(30);
        meuCarro.desligar(); // Vai avisar que está em movimento
        meuCarro.frear(70);
        meuCarro.desligar(); // Agora sim desliga

        scanner.close();
    }
}

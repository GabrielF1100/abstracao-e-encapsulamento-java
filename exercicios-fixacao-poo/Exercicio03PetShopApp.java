package exercicios_fixacao_poo;

import java.util.Scanner;

class MaquinaBanhoPet {
    private static final int MAX_AGUA = 30;
    private static final int MAX_SHAMPOO = 10;

    private int nivelAgua;
    private int nivelShampoo;
    private boolean temPet;
    private String nomePet;
    private boolean petLimpo;
    private boolean maquinaSuja;

    public MaquinaBanhoPet() {
        this.nivelAgua = 20;
        this.nivelShampoo = 6;
        this.temPet = false;
        this.nomePet = null;
        this.petLimpo = false;
        this.maquinaSuja = false;
    }

    public int getNivelAgua() {
        return nivelAgua;
    }

    public int getNivelShampoo() {
        return nivelShampoo;
    }

    public boolean isTemPet() {
        return temPet;
    }

    public String getNomePet() {
        return nomePet;
    }

    public boolean isMaquinaSuja() {
        return maquinaSuja;
    }

    public void colocarPet(String nome) {
        if (temPet) {
            System.out.printf("A maquina ja esta ocupada com o pet '%s'.\n", nomePet);
            return;
        }

        if (maquinaSuja) {
            System.out.println("A maquina precisa ser limpa antes de receber outro pet.");
            return;
        }

        this.temPet = true;
        this.nomePet = nome;
        this.petLimpo = false;
        System.out.printf("Pet '%s' colocado na maquina.\n", nome);
    }

    public void darBanho() {
        if (!temPet) {
            System.out.println("Nenhum pet na maquina para banho.");
            return;
        }

        if (petLimpo) {
            System.out.printf("O pet '%s' ja esta limpo.\n", nomePet);
            return;
        }

        if (nivelAgua < 10 || nivelShampoo < 2) {
            System.out.printf("Recursos insuficientes (Necessario: 10L agua, 2L shampoo. Atual: %dL agua, %dL shampoo).\n",
                    nivelAgua, nivelShampoo);
            return;
        }

        nivelAgua -= 10;
        nivelShampoo -= 2;
        petLimpo = true;
        System.out.printf("Banho concluido para '%s'. Nivel restante: %dL agua, %dL shampoo.\n",
                nomePet, nivelAgua, nivelShampoo);
    }

    public void retirarPet() {
        if (!temPet) {
            System.out.println("A maquina ja esta vazia.");
            return;
        }

        System.out.printf("Retirando pet '%s' da maquina.\n", nomePet);

        if (!petLimpo) {
            maquinaSuja = true;
            System.out.println("Aviso: O pet saiu sem banho. A maquina agora precisa ser limpa.");
        } else {
            System.out.println("Pet retirado com sucesso e limpo.");
        }

        this.temPet = false;
        this.nomePet = null;
        this.petLimpo = false;
    }

    public void abastecerAgua() {
        if (nivelAgua >= MAX_AGUA) {
            System.out.println("Nivel de agua ja esta no maximo (30L).");
            return;
        }

        int qtd = Math.min(2, MAX_AGUA - nivelAgua);
        nivelAgua += qtd;
        System.out.printf("Abastecido +%dL de agua. Total: %dL/%dL\n", qtd, nivelAgua, MAX_AGUA);
    }

    public void abastecerShampoo() {
        if (nivelShampoo >= MAX_SHAMPOO) {
            System.out.println("Nivel de shampoo ja esta no maximo (10L).");
            return;
        }

        int qtd = Math.min(2, MAX_SHAMPOO - nivelShampoo);
        nivelShampoo += qtd;
        System.out.printf("Abastecido +%dL de shampoo. Total: %dL/%dL\n", qtd, nivelShampoo, MAX_SHAMPOO);
    }

    public void limparMaquina() {
        if (temPet) {
            System.out.printf("Nao e possivel limpar a maquina com o pet '%s' dentro.\n", nomePet);
            return;
        }

        if (nivelAgua < 3 || nivelShampoo < 1) {
            System.out.printf("Recursos insuficientes para limpeza (Necessario: 3L agua, 1L shampoo. Atual: %dL agua, %dL shampoo).\n",
                    nivelAgua, nivelShampoo);
            return;
        }

        nivelAgua -= 3;
        nivelShampoo -= 1;
        maquinaSuja = false;
        System.out.println("Maquina limpa e liberada para uso.");
    }
}

public class Exercicio03PetShopApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MaquinaBanhoPet maquina = new MaquinaBanhoPet();

        int opcao = 0;
        while (opcao != 10) {
            System.out.println("\n--- MAQUINA DE BANHO PETSHOP ---");
            System.out.printf("Agua: %dL/30L | Shampoo: %dL/10L | Ocupada: %s | Precisa limpeza: %s\n",
                    maquina.getNivelAgua(),
                    maquina.getNivelShampoo(),
                    (maquina.isTemPet() ? maquina.getNomePet() : "Nao"),
                    (maquina.isMaquinaSuja() ? "Sim" : "Nao"));
            System.out.println("1. Dar banho no pet");
            System.out.println("2. Abastecer agua (+2L)");
            System.out.println("3. Abastecer shampoo (+2L)");
            System.out.println("4. Verificar nivel de agua");
            System.out.println("5. Verificar nivel de shampoo");
            System.out.println("6. Verificar se tem pet no banho");
            System.out.println("7. Colocar pet na maquina");
            System.out.println("8. Retirar pet da maquina");
            System.out.println("9. Limpar maquina");
            System.out.println("10. Sair");
            System.out.print("Opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> maquina.darBanho();
                case 2 -> maquina.abastecerAgua();
                case 3 -> maquina.abastecerShampoo();
                case 4 -> System.out.printf("Nivel de agua: %dL/30L\n", maquina.getNivelAgua());
                case 5 -> System.out.printf("Nivel de shampoo: %dL/10L\n", maquina.getNivelShampoo());
                case 6 -> {
                    if (maquina.isTemPet()) {
                        System.out.printf("Pet presente: %s\n", maquina.getNomePet());
                    } else {
                        System.out.println("Nenhum pet na maquina.");
                    }
                }
                case 7 -> {
                    System.out.print("Nome do pet: ");
                    String nome = scanner.nextLine().trim();
                    maquina.colocarPet(nome);
                }
                case 8 -> maquina.retirarPet();
                case 9 -> maquina.limparMaquina();
                case 10 -> System.out.println("Encerrando sistema.");
                default -> System.out.println("Opcao invalida.");
            }
        }

        scanner.close();
    }
}

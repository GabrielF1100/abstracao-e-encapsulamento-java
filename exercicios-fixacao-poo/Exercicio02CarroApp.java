package exercicios_fixacao_poo;

import java.util.Scanner;

class CarroManual {
    private boolean ligado;
    private int marcha;
    private int velocidade;

    public CarroManual() {
        this.ligado = false;
        this.marcha = 0;
        this.velocidade = 0;
    }

    public boolean isLigado() {
        return ligado;
    }

    public int getMarcha() {
        return marcha;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void ligar() {
        if (ligado) {
            System.out.println("O carro ja esta ligado.");
            return;
        }
        ligado = true;
        System.out.println("Carro ligado.");
    }

    public void desligar() {
        if (!ligado) {
            System.out.println("O carro ja esta desligado.");
            return;
        }

        if (marcha == 0 && velocidade == 0) {
            ligado = false;
            System.out.println("Carro desligado.");
        } else {
            System.out.printf("Para desligar, coloque em ponto morto (0) e pare o carro. Estado atual: Marcha %d, %d km/h.\n",
                    marcha, velocidade);
        }
    }

    public void trocarMarcha(int novaMarcha) {
        if (!ligado) {
            System.out.println("O carro precisa estar ligado para trocar de marcha.");
            return;
        }

        if (novaMarcha < 0 || novaMarcha > 6) {
            System.out.println("Marcha invalida (use de 0 a 6).");
            return;
        }

        if (Math.abs(novaMarcha - marcha) != 1) {
            System.out.printf("Nao e permitido pular marchas. Marcha atual: %d.\n", marcha);
            return;
        }

        if (!velocidadeCompativel(velocidade, novaMarcha)) {
            System.out.printf("Velocidade incompativel (%d km/h) para engatar a marcha %d.\n",
                    velocidade, novaMarcha);
            return;
        }

        marcha = novaMarcha;
        System.out.println("Marcha alterada para: " + (marcha == 0 ? "Ponto morto" : marcha + "a marcha"));
    }

    public void acelerar() {
        if (!ligado) {
            System.out.println("O carro esta desligado.");
            return;
        }

        if (marcha == 0) {
            System.out.println("O carro esta em ponto morto. Engate a 1a marcha para acelerar.");
            return;
        }

        if (velocidade >= 120) {
            System.out.println("Velocidade maxima atingida (120 km/h).");
            return;
        }

        int novaVelocidade = velocidade + 1;
        if (!velocidadeCompativel(novaVelocidade, marcha)) {
            System.out.printf("Limite da %da marcha atingido (%d km/h). Suba a marcha para continuar acelerando.\n",
                    marcha, velocidade);
            return;
        }

        velocidade = novaVelocidade;
        System.out.printf("Velocidade: %d km/h (Marcha %d)\n", velocidade, marcha);
    }

    public void diminuirVelocidade() {
        if (!ligado) {
            System.out.println("O carro esta desligado.");
            return;
        }

        if (velocidade <= 0) {
            System.out.println("O carro ja esta parado (0 km/h).");
            return;
        }

        int novaVelocidade = velocidade - 1;
        if (!velocidadeCompativel(novaVelocidade, marcha)) {
            System.out.printf("Velocidade muito baixa para a %da marcha (%d km/h). Reduza a marcha.\n",
                    marcha, velocidade);
            return;
        }

        velocidade = novaVelocidade;
        System.out.printf("Velocidade: %d km/h (Marcha %d)\n", velocidade, marcha);
    }

    public void virar(String direcao) {
        if (!ligado) {
            System.out.println("O carro esta desligado.");
            return;
        }

        if (velocidade >= 1 && velocidade <= 40) {
            System.out.printf("Virando para a %s a %d km/h.\n", direcao, velocidade);
        } else if (velocidade == 0) {
            System.out.println("O carro esta parado. Nao e possivel virar.");
        } else {
            System.out.printf("Velocidade muito alta para virar (%d km/h). Reduza para no maximo 40 km/h.\n", velocidade);
        }
    }

    /**
     * Faixas ajustadas para permitir a transicao suave nos pontos de troca
     * (20, 40, 60, 80 e 100 km/h).
     */
    private boolean velocidadeCompativel(int vel, int m) {
        return switch (m) {
            case 0 -> vel == 0;
            case 1 -> vel >= 0 && vel <= 20;
            case 2 -> vel >= 20 && vel <= 40;
            case 3 -> vel >= 40 && vel <= 60;
            case 4 -> vel >= 60 && vel <= 80;
            case 5 -> vel >= 80 && vel <= 100;
            case 6 -> vel >= 100 && vel <= 120;
            default -> false;
        };
    }
}

public class Exercicio02CarroApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarroManual carro = new CarroManual();

        int opcao = 0;
        while (opcao != 8) {
            System.out.println("\n--- CONTROLE DO CARRO ---");
            System.out.printf("Status: %s | Marcha: %s | Velocidade: %d km/h\n",
                    (carro.isLigado() ? "Ligado" : "Desligado"),
                    (carro.getMarcha() == 0 ? "Ponto Morto (0)" : carro.getMarcha()),
                    carro.getVelocidade());
            System.out.println("1. Ligar carro");
            System.out.println("2. Desligar carro");
            System.out.println("3. Acelerar (+1 km/h)");
            System.out.println("4. Diminuir velocidade (-1 km/h)");
            System.out.println("5. Trocar marcha");
            System.out.println("6. Virar para esquerda");
            System.out.println("7. Virar para direita");
            System.out.println("8. Sair");
            System.out.print("Opcao: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> carro.ligar();
                case 2 -> carro.desligar();
                case 3 -> carro.acelerar();
                case 4 -> carro.diminuirVelocidade();
                case 5 -> {
                    System.out.print("Informe a marcha (0 a 6): ");
                    int m = scanner.nextInt();
                    carro.trocarMarcha(m);
                }
                case 6 -> carro.virar("esquerda");
                case 7 -> carro.virar("direita");
                case 8 -> System.out.println("Saindo do simulador.");
                default -> System.out.println("Opcao invalida.");
            }
        }

        scanner.close();
    }
}

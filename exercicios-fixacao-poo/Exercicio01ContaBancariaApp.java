package exercicios_fixacao_poo;

import java.util.Locale;
import java.util.Scanner;

class ContaBancariaEspecial {
    private String titular;
    private double saldo;
    private double limiteChequeEspecial;
    private double chequeEspecialUsado;
    private double taxaPendente;

    public ContaBancariaEspecial(String titular, double depositoInicial) {
        this.titular = titular;
        this.saldo = depositoInicial;
        this.chequeEspecialUsado = 0.0;
        this.taxaPendente = 0.0;

        if (depositoInicial <= 500.0) {
            this.limiteChequeEspecial = 50.0;
        } else {
            this.limiteChequeEspecial = depositoInicial * 0.5;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public double getChequeEspecialDisponivel() {
        return limiteChequeEspecial - chequeEspecialUsado;
    }

    public double getChequeEspecialUsado() {
        return chequeEspecialUsado;
    }

    public double getTaxaPendente() {
        return taxaPendente;
    }

    public boolean estaUsandoChequeEspecial() {
        return chequeEspecialUsado > 0;
    }

    public boolean debitar(double valor, String operacao) {
        if (valor <= 0) {
            System.out.println("Valor invalido para " + operacao + ".");
            return false;
        }

        double totalDisponivel = saldo + getChequeEspecialDisponivel();
        if (valor > totalDisponivel) {
            System.out.printf("Saldo insuficiente. Disponivel: R$ %.2f (Saldo: R$ %.2f, Cheque: R$ %.2f)\n",
                    totalDisponivel, saldo, getChequeEspecialDisponivel());
            return false;
        }

        if (valor <= saldo) {
            saldo -= valor;
            System.out.printf("%s de R$ %.2f realizado com sucesso.\n", operacao, valor);
        } else {
            double restante = valor - saldo;
            saldo = 0.0;
            chequeEspecialUsado += restante;
            taxaPendente += restante * 0.20;

            System.out.printf("%s de R$ %.2f realizado utilizando R$ %.2f do cheque especial.\n",
                    operacao, valor, restante);
            System.out.printf("Taxa de 20%% sobre o cheque utilizado: R$ %.2f\n", restante * 0.20);
        }

        return true;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("O valor do deposito deve ser maior que zero.");
            return;
        }

        double valorRestante = valor;

        // Quita taxa do cheque especial se houver
        if (taxaPendente > 0) {
            if (valorRestante >= taxaPendente) {
                valorRestante -= taxaPendente;
                System.out.printf("Taxa de R$ %.2f do cheque especial quitada.\n", taxaPendente);
                taxaPendente = 0.0;
            } else {
                taxaPendente -= valorRestante;
                System.out.printf("Abatido R$ %.2f da taxa. Restante: R$ %.2f\n", valorRestante, taxaPendente);
                valorRestante = 0.0;
            }
        }

        // Recompoe cheque especial usado
        if (valorRestante > 0 && chequeEspecialUsado > 0) {
            if (valorRestante >= chequeEspecialUsado) {
                valorRestante -= chequeEspecialUsado;
                chequeEspecialUsado = 0.0;
                System.out.println("Cheque especial recomposto integralmente.");
            } else {
                chequeEspecialUsado -= valorRestante;
                System.out.printf("Cheque especial recomposto parcialmente. Em uso: R$ %.2f\n", chequeEspecialUsado);
                valorRestante = 0.0;
            }
        }

        // O que sobra vai para o saldo
        if (valorRestante > 0) {
            saldo += valorRestante;
        }

        System.out.printf("Deposito finalizado. Saldo: R$ %.2f | Cheque disponivel: R$ %.2f\n",
                saldo, getChequeEspecialDisponivel());
    }
}

public class Exercicio01ContaBancariaApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("=== ABERTURA DE CONTA BANCARIA ===");
        System.out.print("Nome do titular: ");
        String titular = scanner.nextLine();

        System.out.print("Valor do deposito inicial: R$ ");
        double depositoInicial = scanner.nextDouble();

        ContaBancariaEspecial conta = new ContaBancariaEspecial(titular, depositoInicial);
        System.out.printf("Conta aberta. Saldo: R$ %.2f | Limite de cheque: R$ %.2f\n",
                conta.getSaldo(), conta.getLimiteChequeEspecial());

        int opcao = 0;
        while (opcao != 7) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Consultar cheque especial");
            System.out.println("3. Depositar dinheiro");
            System.out.println("4. Sacar dinheiro");
            System.out.println("5. Pagar boleto");
            System.out.println("6. Verificar uso do cheque especial");
            System.out.println("7. Sair");
            System.out.print("Opcao: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.printf("Saldo: R$ %.2f\n", conta.getSaldo());
                    System.out.printf("Total disponivel (saldo + cheque): R$ %.2f\n",
                            conta.getSaldo() + conta.getChequeEspecialDisponivel());
                }
                case 2 -> {
                    System.out.printf("Limite total: R$ %.2f\n", conta.getLimiteChequeEspecial());
                    System.out.printf("Disponivel: R$ %.2f\n", conta.getChequeEspecialDisponivel());
                    System.out.printf("Utilizado: R$ %.2f\n", conta.getChequeEspecialUsado());
                    System.out.printf("Taxa pendente: R$ %.2f\n", conta.getTaxaPendente());
                }
                case 3 -> {
                    System.out.print("Valor do deposito: R$ ");
                    double v = scanner.nextDouble();
                    conta.depositar(v);
                }
                case 4 -> {
                    System.out.print("Valor do saque: R$ ");
                    double v = scanner.nextDouble();
                    conta.debitar(v, "Saque");
                }
                case 5 -> {
                    System.out.print("Valor do boleto: R$ ");
                    double v = scanner.nextDouble();
                    conta.debitar(v, "Pagamento de boleto");
                }
                case 6 -> {
                    if (conta.estaUsandoChequeEspecial()) {
                        System.out.printf("A conta esta usando o cheque especial (R$ %.2f em uso).\n",
                                conta.getChequeEspecialUsado());
                    } else {
                        System.out.println("A conta nao esta usando o cheque especial.");
                    }
                }
                case 7 -> System.out.println("Encerrando sessao.");
                default -> System.out.println("Opcao invalida.");
            }
        }

        scanner.close();
    }
}

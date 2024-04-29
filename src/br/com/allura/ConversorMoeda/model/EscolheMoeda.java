package br.com.allura.ConversorMoeda.model;

import java.util.Scanner;

public class EscolheMoeda {
    public static void enterValue(Scanner scanner) {
        try {
            System.out.println("Digite o código da moeda\n");
            System.out.println("Exemplo: USD, BRL, EUR, FKP, GBP...\n");
            System.out.print("Digite a moeda de origem: ");
            String originCurrency = scanner.next();
            System.out.print("Digite a moeda de destino: ");
            String destinationCurrency = scanner.next();
            Conversor.converteCoin(originCurrency, destinationCurrency, scanner);
        } catch (Exception e) {
            System.out.println("*********************** - ERRO - *******************************");
            System.out.println("Ocorreu um erro: " + e.getMessage());
            System.out.println("****************************************************************");
            // Finalizar a aplicação
            System.exit(1);
        }
    }
}

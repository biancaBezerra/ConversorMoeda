package br.com.allura.ConversorMoeda.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Conversor {
    public static void converteCoin(String originCurrency, String destinationCurrency, Scanner scanner) {
        try {
            String url_str = "https://v6.exchangerate-api.com/v6/33b50778f04f2272376bd475/latest/" + originCurrency;

            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");

            if (!conversionRates.has(destinationCurrency)) {
                System.out.println("Moeda de conversão final inválida.");
                return;
            }

            double exchangeRate = conversionRates.get(destinationCurrency).getAsDouble();

            System.out.print("Digite o valor que queira converter: ");
            double value = scanner.nextDouble();

            double convertedValue = value * exchangeRate;

            System.out.println("************* - RESULTADO DA CONVERSÃO - ***********************");
            System.out.println("           " + value + " " + originCurrency+ " Equivalem a " +
                    convertedValue + " " + destinationCurrency);
            System.out.println("****************************************************************");

            // Após exibir o resultado, aguarda a entrada do usuário para retornar ao menu principal
            System.out.println("\nPressione Enter para voltar ao menu principal...");
            scanner.nextLine(); // Limpa o buffer
            scanner.nextLine(); // Aguarda a entrada do usuário
        } catch (Exception e) {
            System.out.println("*********************** - ERRO - *******************************");
            System.out.println("Ocorreu um erro: " + e.getMessage());
            System.out.println("****************************************************************");
            // Finalizar a aplicação
            System.exit(1);
        }
    }

}

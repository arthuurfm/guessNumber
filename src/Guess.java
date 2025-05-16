import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Guess {
  public static void main(String[] args) {
    // lê um input;
    Scanner read = new Scanner(System.in);

    // cria um randomizador.
    Random random = new Random();
    String close = "0. Fechar";
    int matches = 0;
    int wins = 0;
    int wonFirstAttempt = 0;

    // cria uma lista de inteiros.
    ArrayList<Integer> numbers = new ArrayList<>();

    while (true) {
      int option;
      System.err.println();
      System.out.println(close);
      System.out.println("1. Jogar");
      System.out.println("2. Status");
      System.out.printf("Opção: ");
      option = read.nextInt();
      
      // se a opção for 0, fecha o programa.
      if (option == 0) {
        break;
      }

      if (option < 0 || option > 2) {
        System.out.println("\nERRO: essa opção não existe.");
      }

      if (option == 1) {
        // reset do jogo.
        int randomNumber = random.nextInt(1, 20);
        int attempts = 1;
        int totalAttempts = 5;
        int number = 0;
        numbers.clear();

        System.out.println(randomNumber);

        // jogo.
        while (true) { 
          System.out.println();
          System.out.println(close);
          // verifica se a lista está vazia para exibir os números que já foram chutados.
          if (!numbers.isEmpty()) {
            System.out.println("Chutes: " + numbers);
          }
          System.out.printf("Tentativa %d de %d.\n", attempts, totalAttempts);
          System.out.printf("Tente adivinhar o número de 1 ao 20: ");
          number = read.nextInt();

          // verifica se o número chutado é igual ao número "pensado", para acabar o jogo.
          if (number == randomNumber) {
            if (attempts == 1) {
              System.out.printf("DE PRIMEIRA!\n");
              wonFirstAttempt++;
            }
            System.out.printf("\nVocê acertou, o número era %d.\n", randomNumber);
            matches++;
            wins++;
            break;
          }
            
          // se for 0, volta pro menu.
          else if (number == 0) {
            break;
          }
            
          // se for um número fora das opções, emite erro.
          else if (number > 20 || number < 0) {
            System.out.println("Esse número ou opção não existe.");
          }

          else {
            // verifica se o número já foi chutado.
            if (!numbers.contains(number)) {
              System.out.println("\nERROU!");
              numbers.add(number);
              // se errar, adiciona o número a lista e diminuem as chances.
              if (attempts <= totalAttempts) {
                attempts++;              
              }
            }

            else {
              System.out.printf("\n%d já foi chutado.\n", number);
            }

            // se exceder as tentativas, acaba o jogo.
            if (attempts > totalAttempts) {
              System.out.println("Chutes: " + numbers);
              System.out.printf("\nO número era %d. Você não conseguiu acertar.\n", randomNumber);
              matches++;
              break;
            }
          }
        }
      }

      if (option == 2) {
        double winRate = 0;
        // só calcula o percentual, se tiver pelo menos uma vitória.
        if (wins > 0) {
            winRate = (wins * 100) / matches;
          }

          // formata o winRate para ficar em formato de percentual.
          NumberFormat formatedWinRate = NumberFormat.getPercentInstance();
          formatedWinRate.setMinimumFractionDigits(2);

          // status.
          System.out.printf("\nPartidas: %d\n", matches);
          System.out.printf("Vitórias: %d\n", wins);
          System.out.printf("Acertou na primeira: %d\n", wonFirstAttempt);
          System.out.printf("Percentual de vitórias: %s\n", formatedWinRate.format(winRate * 0.01));
      }
    }
  }
}
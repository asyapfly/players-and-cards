package by.vasin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static final String[] colors = {"R", "G", "B", "W"};
    public static String[] playersCards = new String[0];
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        while (true) {
            String input = in.nextLine();
            List<String> splittedInput = Arrays.asList(input.split(" "));

            String command = splittedInput.get(0);
            List<String> argumentsString = splittedInput.subList(1, splittedInput.size());
            List<Integer> arguments = argumentsString
                    .stream()
                    .map(Integer::parseInt)
                    .toList();

            switch (command) {
                case "start": {
                    if(arguments.size() != 2){
                        System.out.println("Введено неверное количество аргументов команды 'start'"
                                + "\n"
                                + "Команда 'start' принимает последовательно 2 целых числа через пробел. Пример: start 2 4"
                                + "\n"
                                + "Введите команду с корректным количеством аргументов");
                        break;
                    }
                    start(arguments.get(0), arguments.get(1));
                    break;
                }

                case "get-cards": {
                    if(arguments.size() != 1){
                        System.out.println("Введено неверное количество аргументов команды 'get-cards'"
                                + "\n"
                                + "Команда 'get-cards' принимает одно целое число через пробел. Пример: get-cards 3"
                                + "\n"
                                + "Введите команду с корректным количеством аргументов");
                        break;
                    }
                    getCards(arguments.get(0));
                    break;
                }

                default:
                    System.out.println("Команда не распознана. Возможные команды start и get-cards. Введите корректные данные");
                    break;
            }
        }
    }

    static String getRandomCard(){
        int index = (int)(Math.random()*4);
        int value = (int)(Math.random()*10) + 1;

        return colors[index] + value;
    }

    static void start(Integer cardsCount, Integer playersCount){
        playersCards = new String[playersCount];
        try {
            for (int playerIndex = 0; playerIndex < playersCount; playerIndex++) {
                StringBuilder cards = new StringBuilder();

                for (int cardIndex = 0; cardIndex < cardsCount - 1; cardIndex++) {
                    cards.append(getRandomCard()).append(" ");
                }
                cards.append(getRandomCard());

                playersCards[playerIndex] = cards.toString();
            }
        } catch (OutOfMemoryError oom) {
            System.out.println("OutOfMemory Слишком много карт и игроков. Так много разом все равно никто не играет :)");
            return;
        }
    }
    static void getCards(Integer player){
        Integer playerIndex = player - 1;

        if (playersCards.length < player || player == 0) {
            System.out.println("Игрока под номером " + player + " не существует");
            return;
        }
        System.out.println(player + " " + playersCards[playerIndex]);
    }
}
/*Реализолвать основной класс (возможны некоторые исправления в методе «Операция 1») в виде синглтона со следующими устанавливаемыми полями с помощью сеттеров и исправлениями в операции 1. В классе main поочередно создать 2 экземпляра класса и не устанавливая у второго указанное поле, продемонстрировать что оно соответствует полю первого экземпляра.
        Операция 1: без изменения. Поле: сдвиг (int)*/

import java.util.Scanner;

// Интерфейс обработчика текста
interface TextProcessor {
    String processText(String text);
}

// Базовый класс, реализующий интерфейс (синглтон)
class BaseTextProcessor implements TextProcessor {
    // Единственный экземпляр синглтона
    private static BaseTextProcessor instance;

    // Поле сдвиг
    private int shift;

    // Приватный конструктор (чтобы нельзя было создать экземпляр извне)
    private BaseTextProcessor() {}

    // Метод для получения экземпляра синглтона
    public static BaseTextProcessor getInstance() {
        if (instance == null) {
            instance = new BaseTextProcessor();
        }
        return instance;
    }

    // Сеттер для поля shift
    public void setShift(int shift) {
        this.shift = shift;
    }

    // Геттер для поля shift
    public int getShift() {
        return shift;
    }

    // Операция 1: Шифрование текста шифром Цезаря
    @Override
    public String processText(String text) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}

// Основной класс
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Создаем первый экземпляр синглтона
        BaseTextProcessor processor1 = BaseTextProcessor.getInstance();
        System.out.println("Введите сдвиг для первого экземпляра:");
        int shift1 = scanner.nextInt();
        processor1.setShift(shift1); // Устанавливаем сдвиг для первого экземпляра

        // Создаем второй экземпляр синглтона
        BaseTextProcessor processor2 = BaseTextProcessor.getInstance();
        System.out.println("Сдвиг второго экземпляра (без установки): " + processor2.getShift());

        // Демонстрация, что сдвиг одинаков для обоих экземпляров
        System.out.println("Сдвиг первого экземпляра: " + processor1.getShift());
        System.out.println("Сдвиг второго экземпляра: " + processor2.getShift());

        // Ввод текста и выполнение операции
        scanner.nextLine(); // Поглощаем оставшийся символ новой строки
        System.out.println("Введите текст для шифрования:");
        String text = scanner.nextLine();

        System.out.println("Зашифрованный текст: " + processor1.processText(text));
    }
}

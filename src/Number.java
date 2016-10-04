import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by GE70 on 17.09.2016.
 */
public class Number {
    private static Pattern pattern = Pattern.compile("\\[[0-1]+\\]");
    private static Pattern wrong = Pattern.compile("\\[*[0-9]+");
    private static Pattern delimiter = Pattern.compile("[^\\[\\]0-9]+");
    private static int K;

    public static void main(String[] args) throws Exception {
        int[] numbers = getNumbers();
        System.out.println(Arrays.toString(numbers));
        Arrays.sort(numbers);
        K = numbers[0];
        System.out.println("Минимальное число: " + numbers[0]);
        System.out.println("Максимальное число: " + numbers[2]);
        System.out.println("Среднее арифметическое: " + getAverage(numbers));
        int[] array = getRandomArray(100);
        printSpecialNumbers(array);
//        bubbleSort(array);
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void bubbleSort(int array[]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[i]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
    }

    private static void selectionSort(int array[]) {
        int left = 0;
        while (left < array.length) {
            int minI = left;
            int min = array[left];
            for (int i = left; i < array.length; i++) {
                if (array[i] < min) {
                    min = array[i];
                    minI = i;
                }
            }
            int temp = array[left];
            array[left] = array[minI];
            array[minI] = temp;
            left++;
        }
    }

//    // все строки вида [0101010101] подходят под этот шаблон
//    private static Pattern pattern = Pattern.compile("\\[[0-1]+\\]");
//    private static Pattern delimiter = Pattern.compile("[^\\[\\]0-9]+");
//    private static int K;

    private static boolean isPrime(int number) {
        if (number == 0 || number == 1)
            return false;
        int squareRoot = (int) Math.sqrt(number);
        for (int divider = 2; divider <= squareRoot; divider++) {
            if (number % divider == 0)
                return false;
        }
        return true;
    }

    public static int[] getNumbers() {
        System.out.println("Введите три числа: ");
        Scanner input = new Scanner(System.in);
        input.useDelimiter(delimiter);
        int numbers[] = new int[3];
        int index = 0;
        while (index < 3) {
            if (input.hasNextInt()) {
                numbers[index] = input.nextInt();
                index++;
                continue;
            }
            if (input.hasNext(pattern)) {
                String binary = input.next();
                binary = binary.substring(1, binary.length() - 1);//вынимает число из []
                numbers[index] = Integer.parseInt(binary, 2);//переводит двоичное в десятичное
                index++;
                continue;
            } else {
                input.useDelimiter("[^0-9]+");
                if (input.hasNext(wrong)) {
                    numbers[index] = input.nextInt();
                    index++;
                    input.useDelimiter(delimiter);
                    continue;
                }
                input.useDelimiter(delimiter);
            }
            input.next();
        }
        return numbers;
    }

    public static double getAverage(int[] numbers) {
        double average = 0.;
        for (int i = 0; i < numbers.length; i++) {
            average += numbers[i];
        }
        average /= 3;
        return average;
    }

    public static int[] getRandomArray(int size) {
        Random generator = new Random();
        int array[] = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = generator.nextInt(K + 1);
        }
        return array;
    }

    public static void printSpecialNumbers(int[] array) {

        int primeCount = 0;
        int k3Count = 0;
        for (int i = 0; i < array.length; i++) {
            if (isPrime(array[i]))
                primeCount++;
            if (array[i] % 3 == 0 && array[i] != 0)
                k3Count++;
        }
        System.out.println(Arrays.toString(array));
        System.out.println("Простые числа : " + primeCount);
        System.out.println("Числа кратные трем : " + k3Count);
    }


}
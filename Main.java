import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Main result = new Main();
        System.out.println("Input:");
        String expression = input.nextLine();
        String answer = result.calc(expression);
        System.out.println("Output:\n" + answer);
    }
    public static String calc(String input){
        boolean romanOrArab = false;
        String error = "throws Exception";
        Main romanExam = new Main();
        Main arabToRoman = new Main();
        int result = 0;
        String[] inputSplit = input.split(" ");
        if (inputSplit.length != 3){
            return error;
        }
        Integer a = 0;
        Integer b = 0;
        try {
            a = Integer.valueOf(inputSplit[0]);
            b = Integer.valueOf(inputSplit[2]);
        } catch (NumberFormatException e) {
            try {
                a = romanExam.romanToArab(inputSplit[0]);
                b = romanExam.romanToArab(inputSplit[2]);
                romanOrArab = true;
            } catch (NumberFormatException ex) {
                return error;
            }
        }
        if ((a < 1) || (a > 10) || (b < 1) || (b > 10)){
            return error;
        }

        String sign = inputSplit[1];
        switch (sign) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> result = a / b;
            default -> {
                return error;
            }
        }
        String output;
        if (romanOrArab){
            if(result < 1){
                return error;
            } else {
                output = arabToRoman.arabToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }

        return output;
    }

    Integer romanToArab(String romanInput){
        int result = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }
        return result;
    }
    String arabToRome(int arabInput){
        String result = "";
        int value = 0;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++){
            value = arabInput / arab[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
        return result;

    }

}
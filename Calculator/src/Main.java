import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите действие с двумя операндами через пробел");
        Scanner input = new Scanner(System.in);
        String inputMath = input.nextLine();
        int check = check(inputMath);
        String result = calc(inputMath, check);
        System.out.println(result);
    }
    static int check(String input) {
        boolean check1 = input.contains(" ");
        if (!check1) {
            System.out.println("Некорректный ввод. Добавьте пробелы");
            System.exit(0);
        }
        String[] inputMathElem = input.split(" ");
        int check2 = inputMathElem.length;
        if (!(check2 == 3)) {
            System.out.println("Некорректный ввод. Должно быть два операнда и один оператор");
            System.exit(0);
            }
        int check3 = 0;
        try {
            int a = Integer.parseInt(inputMathElem[0]), b = Integer.parseInt(inputMathElem[2]);
            check3 = 1;
        } catch (NumberFormatException e1) {
            try {
                RimNum rimN1 = RimNum.valueOf(inputMathElem[0]);
                RimNum rimN3 = RimNum.valueOf(inputMathElem[2]);
                check3 = 2;
            } catch (IllegalArgumentException e2) {
                System.out.println("Некорректный ввод. Оба операнда должны быть меньше 10 и либо из римской, либо из арабской системы");
                System.exit(0);
            }
        }
        return check3;
    }

    public static String calc(String input, int check) {
                String[] inputMathElem = input.split(" ");
                String inElem1 = inputMathElem[0];
                String inElem2 = inputMathElem[1];
                String inElem3 = inputMathElem[2];
                int a, b, result;
                String resultStr="";
                if (check==1) {
                    //арабская -------
                    a = Integer.parseInt(inElem1);
                    b = Integer.parseInt(inElem3);
                    result = calcSum(a, b, inElem2);
                    resultStr = Integer.toString(result);
                }{
                        //римская --------
                        RimNum rimN1 = RimNum.valueOf(inElem1);
                        RimNum rimN3 = RimNum.valueOf(inElem3);
                        a = Integer.parseInt(rimN1.getArab());
                        b = Integer.parseInt(rimN3.getArab());
                        result = calcSum(a, b, inElem2);


                        if (result < 1) {
                            System.out.println("Отрицательный результат - невозможно в римской системе");
                            System.exit(0);
                        }
                        RimNum[] rimnum = RimNum.values();
                        // String resultStr="";
                        if (result <= 10) {
                            resultStr = rimnum[result - 1].name();
                        } else if (result > 10 && result < 40) {
                            String res = "";
                            for (int i = 0; i < result / 10; i++) {
                                res = "X" + res;
                            }
                            if (result % 10 == 0) {
                                resultStr = res;
                            } else {
                                res = res + rimnum[(result % 10) - 1];
                                resultStr = res;
                            }
                        } else if (result >= 40 && result < 50) {
                            if (result % 10 == 0) {
                                resultStr = "XL";
                            } else {
                                resultStr = "XL" + rimnum[(result % 10) - 1];
                            }

                        } else if (result >= 50 && result < 90) {
                            String res = "L";
                            for (int i = 5; i < result / 10; i++) {
                                res = res + "X";
                            }
                            if (result % 10 == 0) {
                                resultStr = res;
                            } else {
                                res = res + rimnum[(result % 10) - 1];
                                resultStr = res;
                            }
                        } else if (result >= 90 && result < 100) {
                            if (result % 10 == 0) {
                                resultStr = "XL";
                            } else {
                                resultStr = "XC" + rimnum[(result % 10) - 1];
                            }
                        } else if (result == 100) {
                            resultStr = "C";
                        }
                        }
        return resultStr;
                }

    static int calcSum(int a, int b, String operator) {
        int c = 0;
        if (a > 10 || b > 10) {
            System.out.println("Некорректный ввод. Операнды не должны быть больше 10");
            System.exit(0);
        } else {
            switch (operator) {
                case "+" -> c = a + b;
                case "-" -> c = a - b;
                case "/" -> c = a / b;
                case "*" -> c = a * b;
                default -> {
                    System.out.println("Некорректный ввод. Использован неверный оператор");
                    System.exit(0);
                }

            }

        }
        return c;
    }
}




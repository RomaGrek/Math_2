import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста, введите номер типа уравнения:\n1 - Алгебраическое уравнение вида a*x^3 + b*x + c = 0\n" +
                "2 - Алгебраичекое уравнение вида b*x^3 + b*x^2 + c = 0\n" +
                "3 - Трансцендентное уравнение вида a*cos(x) + b*x + c = 0");
        int type = scanner.nextInt();
        if (type == 1) {
            double l, r;
            NonlinearEquation.firstDegreeOfUnknown = 3;
            NonlinearEquation.secondDegreeOfUnknown = 1;
            NonlinearEquation.thirdDegreeOfUnknown = 0;
            System.out.println("Введите через пробел коэффиценты для уравнения вида a*x^3 + b*x + c = 0");
            NonlinearEquation.a = scanner.nextDouble();
            NonlinearEquation.b = scanner.nextDouble();
            NonlinearEquation.c = scanner.nextDouble();
            System.out.println("Пожалуйста введите значение точности");
            NonlinearEquation.e = scanner.nextDouble();
            System.out.println("Пожалуйста введите через пробел левую и правую границу промежутка");
            while (true) {
                NonlinearEquation.leftPoint = scanner.nextDouble();
                NonlinearEquation.rightPoint = scanner.nextDouble();
                l = NonlinearEquation.leftPoint;
                r = NonlinearEquation.rightPoint;
                if (NonlinearEquation.check1()) {
                    break;
                }else {
                    System.out.println("Вы ввели некооректные значения промежутка!\nПожалуйста, повторите ввод промужутка...");
                }
            }

            NonlinearEquation.methodHalfOne();
            System.out.println("x = " + NonlinearEquation.x);
            System.out.println("F(x) = " + String.format("%.10f",NonlinearEquation.func));
            System.out.println("n = " + NonlinearEquation.n);
            NonlinearEquation.n = 0;

            NonlinearEquation.leftPoint = l;
            NonlinearEquation.rightPoint = r;
            NonlinearEquation.searchStartElementOne();

            System.out.println("x = " + NonlinearEquation.x);
            System.out.println("F(x) = " + String.format("%.10f",NonlinearEquation.func));
            System.out.println("n = " + NonlinearEquation.n);
        }else if (type == 2) {
            double l, r;
            NonlinearEquation.firstDegreeOfUnknown = 3;
            NonlinearEquation.secondDegreeOfUnknown = 2;
            NonlinearEquation.thirdDegreeOfUnknown = 0;
            System.out.println("Введите через пробел коэффиценты для уравнения вида a*x^3 + b*x + c = 0");
            NonlinearEquation.a = scanner.nextDouble();
            NonlinearEquation.b = scanner.nextDouble();
            NonlinearEquation.c = scanner.nextDouble();
            System.out.println("Пожалуйста введите значение точности");
            NonlinearEquation.e = scanner.nextDouble();
            System.out.println("Пожалуйста введите через пробел левую и правую границу промежутка");
            while (true) {
                NonlinearEquation.leftPoint = scanner.nextDouble();
                NonlinearEquation.rightPoint = scanner.nextDouble();
                l = NonlinearEquation.leftPoint;
                r = NonlinearEquation.rightPoint;
                if (NonlinearEquation.check3()) {
                    break;
                }else {
                    System.out.println("Вы ввели некооректные значения промежутка!\nПожалуйста, повторите ввод промужутка...");
                }
            }

            NonlinearEquation.methodHalfOne();
            System.out.println("x = " + NonlinearEquation.x);
            System.out.println("F(x) = " + String.format("%.10f",NonlinearEquation.func));
            System.out.println("n = " + NonlinearEquation.n);
            NonlinearEquation.n = 0;

            NonlinearEquation.leftPoint = l;
            NonlinearEquation.rightPoint = r;
            NonlinearEquation.searchStartElementOne();

            System.out.println("x = " + NonlinearEquation.x);
            System.out.println("F(x) = " + String.format("%.10f",NonlinearEquation.func));
            System.out.println("n = " + NonlinearEquation.n);
        }else if(type == 3) {
            double l, r;
            NonlinearEquation.firstDegreeOfUnknown = 1;
            NonlinearEquation.secondDegreeOfUnknown = 1;
            NonlinearEquation.thirdDegreeOfUnknown = 0;
            System.out.println("Введите через пробел коэффиценты для уравнения вида ");
            NonlinearEquation.a = scanner.nextDouble();
            NonlinearEquation.b = scanner.nextDouble();
            NonlinearEquation.c = scanner.nextDouble();
            System.out.println("Пожалуйста введите значение точности");
            NonlinearEquation.e = scanner.nextDouble();
            System.out.println("Пожалуйста введите через пробел левую и правую границу промежутка");
            while (true) {
                NonlinearEquation.leftPoint = scanner.nextDouble();
                NonlinearEquation.rightPoint = scanner.nextDouble();
                l = NonlinearEquation.leftPoint;
                r = NonlinearEquation.rightPoint;
                if (NonlinearEquation.check3()) {
                    break;
                }else {
                    System.out.println("Вы ввели некооректные значения промежутка!\nПожалуйста, повторите ввод промужутка...");
                }
            }

            NonlinearEquation.methodHalfTwo();
            System.out.println("x = " + NonlinearEquation.x);
            System.out.println("F(x) = " + String.format("%.10f",NonlinearEquation.func));
            System.out.println("n = " + NonlinearEquation.n);
            NonlinearEquation.n = 0;

            NonlinearEquation.leftPoint = l;
            NonlinearEquation.rightPoint = r;
            NonlinearEquation.searchStartElementTwo();

            System.out.println("x = " + NonlinearEquation.x);
            System.out.println("F(x) = " + String.format("%.10f",NonlinearEquation.func));
            System.out.println("n = " + NonlinearEquation.n);
        }else if (type == 4) {
            SystemNonlinearEquation.first = 1;
            SystemNonlinearEquation.second = 1;
            SystemNonlinearEquation.third = 0;
            SystemNonlinearEquation.first1 = 3;
            SystemNonlinearEquation.second2 = 1;
            SystemNonlinearEquation.third2 = 0;
            System.out.println("Пожалуйста введите коэфыыиценты a b c для уравнения вида a*cos(y)-b*x=c");
            SystemNonlinearEquation.a = scanner.nextDouble();
            SystemNonlinearEquation.b = scanner.nextDouble();
            SystemNonlinearEquation.c = scanner.nextDouble();
            System.out.println("Пожалуйста введите коэфыыиценты d e f для уравнения вида d*y^3-e*x=f");
            SystemNonlinearEquation.d = scanner.nextDouble();
            SystemNonlinearEquation.e = scanner.nextDouble();
            SystemNonlinearEquation.f = scanner.nextDouble();
            System.out.println("Пожалуйста введите значение точности");
            SystemNonlinearEquation.ex = scanner.nextDouble();

            SystemNonlinearEquation.iterateMethod();

            System.out.println("x = " + SystemNonlinearEquation.x);
            System.out.println("y = " + SystemNonlinearEquation.y);
            System.out.println("n = " + SystemNonlinearEquation.n);
        }
    }
}

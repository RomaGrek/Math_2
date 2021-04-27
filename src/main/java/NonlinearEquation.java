import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class NonlinearEquation {
    static double a;
    static double b;
    static double c;
    static int firstDegreeOfUnknown;
    static int secondDegreeOfUnknown;
    static int thirdDegreeOfUnknown;
    static double leftPoint;
    static double rightPoint;
    static double e; // эпсилон
    static int n = 0;
    static double x = 9999;
    static double func;

    public static double functionOne(double x) {
        return (a * Math.pow(x, firstDegreeOfUnknown) + b * Math.pow(x, secondDegreeOfUnknown) + c * Math.pow(x, thirdDegreeOfUnknown));
    }

    public static double functionTwo(double x) {
        return (a * Math.cos(Math.pow(x, firstDegreeOfUnknown)) + b * Math.pow(x, secondDegreeOfUnknown) + c * Math.pow(x, thirdDegreeOfUnknown));
    }


    public static void methodHalfOne() {
        double num = (rightPoint - leftPoint) / 0.1;
        double[] xData = new double[(int)num + 1];
        double[] yData = new double[(int)num + 1];
        int i = 0;
        int nameX = 1;
        String nameXstr;
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
        chart.addSeries("a", new double[]{leftPoint, leftPoint}, new double[]{0, functionOne(leftPoint)});
        chart.addSeries("b", new double[]{rightPoint, rightPoint}, new double[]{0, functionOne(rightPoint)});
        for (double x_ok = leftPoint; x_ok <= rightPoint + 0.1; x_ok = x_ok + 0.1) {
            xData[i] = x_ok;
            yData[i] = functionOne(x_ok);
            i++;
        }
        while (Math.abs(rightPoint - leftPoint) > e || Math.abs(functionOne(x)) > e) {
            x = (leftPoint + rightPoint) / 2;
            nameXstr = Integer.toString(nameX);
            chart.addSeries("x" + nameXstr, new double[]{x, x}, new double[]{0, functionOne(x)});
            nameX++;
            if ( functionOne(leftPoint) *
                    functionOne(x)> 0) {
                leftPoint = x;
            }else {
                rightPoint = x;
            }
            n++;

        }
        x = (leftPoint + rightPoint) / 2;
        func = functionOne(x);
        chart.addSeries("x-x", new double[]{-2.5, 2.5}, new double[]{0, 0});
        chart.addSeries("y-y", new double[]{0, 0}, new double[]{-5, 5});
        new SwingWrapper(chart).displayChart();
    }

    public static void methodHalfTwo() {
        double num = (rightPoint - leftPoint) / 0.1;
        double[] xData = new double[(int)num + 1];
        double[] yData = new double[(int)num + 1];
        int i = 0;
        int nameX = 1;
        String nameXstr;
        XYChart chart2 = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
        chart2.addSeries("a", new double[]{leftPoint, leftPoint}, new double[]{0, functionTwo(leftPoint)});
        chart2.addSeries("b", new double[]{rightPoint, rightPoint}, new double[]{0, functionTwo(rightPoint)});
        for (double x_ok = leftPoint; x_ok <= rightPoint + 0.1; x_ok = x_ok + 0.1) {
            xData[i] = x_ok;
            yData[i] = functionTwo(x_ok);
            i++;
        }
        while (Math.abs(leftPoint - rightPoint) > e && Math.abs(functionTwo(x)) > e) {
            x = (leftPoint + rightPoint) / 2;
            nameXstr = Integer.toString(nameX);
            chart2.addSeries("x" + nameXstr, new double[]{x, x}, new double[]{0, functionTwo(x)});
            nameX++;
            if ( functionTwo(leftPoint) *
                    functionTwo(x)> 0) {
                leftPoint = x;
            }else {
                rightPoint = x;
            }
            n++;
        }
        x = (leftPoint + rightPoint) / 2;
        func = functionTwo(x);
        chart2.addSeries("x-x", new double[]{-2.5, 2.5}, new double[]{0, 0});
        chart2.addSeries("y-y", new double[]{0, 0}, new double[]{-5, 5});
        new SwingWrapper(chart2).displayChart();
    }



    /* производная алгеьрачиеской фукнции*/
    public static double derivateOne(double x) {
        return a * firstDegreeOfUnknown * Math.pow(x, firstDegreeOfUnknown - 1)
                + b * secondDegreeOfUnknown * Math.pow(x, secondDegreeOfUnknown - 1)
                + c * thirdDegreeOfUnknown * Math.pow(x, thirdDegreeOfUnknown - 1);
    }
    /*Производная трансцендентой функции*/
    public static double derivateTwo(double x) {
        return (a * (-1) * Math.sin(x) * firstDegreeOfUnknown * Math.pow(x, firstDegreeOfUnknown - 1)
                + b * secondDegreeOfUnknown * Math.pow(x, secondDegreeOfUnknown - 1)
                + c * thirdDegreeOfUnknown * Math.pow(x, thirdDegreeOfUnknown));
    }
    /* двойная производная алгебраическо функции */
    public static double derivateOne2(double x) {
        return (a * firstDegreeOfUnknown * (firstDegreeOfUnknown - 1) * Math.pow(x, firstDegreeOfUnknown - 2) + b * secondDegreeOfUnknown
        * (secondDegreeOfUnknown - 1) * Math.pow(x, secondDegreeOfUnknown - 2) + c * thirdDegreeOfUnknown * (thirdDegreeOfUnknown - 1) * Math.pow(x, thirdDegreeOfUnknown - 2));
    }
    /* двойная производная трансциндентной функции */
    public static double derivateTwo2(double x) {
        return (a * (-1) * Math.cos(x));
    }
    /*Проверка постоянности знака произволной алгебраической функции*/
    public static boolean checkDerivativeOne() {
        double startDer = derivateOne(leftPoint);
        for (double xx = leftPoint + 0.1; xx <= rightPoint; xx = xx + 0.1) {
            if((derivateOne(xx)< 0 && startDer > 0) || (derivateOne(xx) > 0 && startDer < 0) || derivateOne(xx) == 0) {
                System.out.println("В промежутке который вы ввели знак производной не постоянный.\n" +
                        "Пожалуйста введите такой промежуток где производная функции постоянна");
                return false;
            }
        }
        return true;
    }

    /*Проверка постоянности знака произволной трансцедентной функции*/
    public static boolean checkDerativeTwo() {
        double startDeri = derivateTwo(leftPoint);
        for (double xx = leftPoint + 0.1; xx <= rightPoint; xx = xx + 0.1) {
            if ((derivateTwo(xx) > 0 && startDeri < 0) || (derivateTwo(xx) < 0 && startDeri > 0) || derivateTwo(xx) == 0.0) {
                return false;
            }
        }
        return true;
    }
    /* проверка знака двойной производной  - метод ньютона - алгебарической функции */
    public static boolean checkDerativeThree1() {
        double start = derivateOne2(leftPoint);
        for (double xx = leftPoint + 0.1; xx <= rightPoint; xx = xx + 0.1) {
            if ((derivateOne2(xx) > 0 && start < 0) || (derivateOne2(xx) < 0 && start > 0)) {
                return false;
            }
        }
        return true;
    }

    /* проверка знака двойной производной  - метод ньютона - трансисдентной функции функции */
    public static boolean checkDerativeThree2() {
        double start = derivateTwo2(leftPoint);
        System.out.println(start + "ssss ");
        for (double xx = leftPoint + 0.1; xx <= rightPoint; xx = xx + 0.1) {
            if ((derivateTwo2(xx) > 0 && start < 0) || (derivateTwo2(xx) < 0 && start > 0)) {
                System.out.println(derivateTwo2(xx));
                return false;
            }
        }
        return true;
    }

    /*Проверка промежутка для алгебарической функции*/
    public static boolean check1() {
        return (functionOne(leftPoint) * functionOne(rightPoint) < 0) && checkDerivativeOne();
    }
    /* Проверка промежутка для трансцедентрнйо функции*/
    public static boolean check2() {
        return ((functionTwo(leftPoint) * functionTwo(rightPoint) < 0) && checkDerativeTwo());
    }

    /* проверка промежутка для алгебрачиеской функции (метод ньютона) */
    public static boolean check3() {
        return (functionOne(leftPoint) *
                functionOne(rightPoint) < 0) && checkDerivativeOne() && checkDerativeThree1();
    }
    /* проверка промежутка для трансцендентной функции (метод ньютона) */
    public static boolean check4() {
        return (functionTwo(leftPoint) *
                functionTwo(rightPoint) < 0) && checkDerativeTwo();
    }
    /* Выбор начального приблеэения для метода ньютона алгебраической функции*/
    public static void searchStartElementOne() {
        if ((functionOne(leftPoint) > 0 &&  derivateOne2(leftPoint) > 0) ||  (functionOne(leftPoint) < 0 &&  derivateOne2(leftPoint) < 0)){
            methodNutonOne(leftPoint);
        }else {
            methodNutonOne(rightPoint);
        }
    }

    /* Выбор начального приблежения для метода ньютона трансцендентной функции*/
    public static void searchStartElementTwo() {
        if ((functionTwo(leftPoint) > 0 &&  derivateTwo2(leftPoint) > 0) ||  (functionTwo(leftPoint) < 0 &&  derivateTwo2(leftPoint) < 0)){
            methodNutonTwo(leftPoint);
        }else {
            methodNutonTwo(rightPoint);
        }
    }

    // сам метол ньютона для алгебараического уранения
    public static void methodNutonOne( double lol) {
        double num = (rightPoint - leftPoint) / 0.1;
        double[] xData = new double[(int)num + 1];
        double[] yData = new double[(int)num + 1];
        int i = 0;
        for (double x_ok = leftPoint; x_ok <= rightPoint + 0.1; x_ok = x_ok + 0.1) {
            xData[i] = x_ok;
            yData[i] = functionOne(x_ok);
            i++;
        }
        XYChart chart3 = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
        x = lol;
        double xi_1 = lol;
        while (Math.abs(functionOne(x)) > e) { // мб добавить условие для остаовки
            n++;
            x = xi_1 - (functionOne(xi_1)/derivateOne(xi_1));
            xi_1 = x;
        }
        func = functionOne(x);
        chart3.addSeries("x-x", new double[]{-2.5, 2.5}, new double[]{0, 0});
        chart3.addSeries("y-y", new double[]{0, 0}, new double[]{-5, 5});
        new SwingWrapper(chart3).displayChart();
    }

    public static void methodNutonTwo(double kek) {
        double num = (rightPoint - leftPoint) / 0.1;
        double[] xData = new double[(int)num + 1];
        double[] yData = new double[(int)num + 1];
        int i = 0;
        for (double x_ok = leftPoint; x_ok <= rightPoint + 0.1; x_ok = x_ok + 0.1) {
            xData[i] = x_ok;
            yData[i] = functionTwo(x_ok);
            i++;
        }
        XYChart chart4 = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
        x = kek;
        double xi_1 = kek;
        while (Math.abs(functionTwo(x)) > e) {
            n++;
            x = xi_1 - (functionTwo(xi_1) / derivateTwo(xi_1));
            xi_1 = x;
        }
        func = functionTwo(x);
        chart4.addSeries("x-x", new double[]{-2.5, 2.5}, new double[]{0, 0});
        chart4.addSeries("y-y", new double[]{0, 0}, new double[]{-5, 5});
        new SwingWrapper(chart4).displayChart();
    }




}

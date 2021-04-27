import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class SystemNonlinearEquation {
    static double a, b, c, d, e, f;
    static int first, second, third, first1, second2, third2;
    static double ex;
    static double n = 0;
    static double x, y, x0  = 0, y0 = 0;
    static double func;
    static double d1, d2;

    public static double function1() {
        return a * Math.pow(Math.cos(y), first) + b * Math.pow(x, second) + c * (-1) * Math.pow(x, third);
    }
    public static double function2() {
        return d * Math.pow(y, first1) + e * Math.pow(x, second2) + f * (-1) * Math.pow(x, third2);
    }

    public static double function1for(double yy) {
        return Math.pow((a * Math.pow(Math.cos(yy), first) + c * (-1) * Math.pow(yy, third))/((-1)*b), 1.0/second);
    }

    public static double function2for(double xx) {
        return Math.pow((e * (-1) * Math.pow(xx, second2) + f * Math.pow(xx, third2))/d, 1.0/first1);
    }




    public static void iterateMethod() {
        double[] xData = new double[60];
        double[] yData2 = new double[60];
        double[] yData = new double[60];
        double[] xData2 = new double[60];
        int i = 0;
        XYChart chart = QuickChart.getChart("1", "X", "Y", "y(x)1", xData, yData);
        for (double x_ok = -3; x_ok <  3; x_ok = x_ok + 0.1) {
            xData[i] = x_ok;
            yData[i] = function2for(x_ok);
            i++;
        }
        i = 0;
        for (double y_ok = -3; y_ok <  3; y_ok = y_ok + 0.1) {
            yData2[i] = y_ok;
            xData2[i] = function1for(y_ok);
            i++;
        }
        chart.addSeries("x-x", new double[]{-2.5, 2.5}, new double[]{0, 0});
        chart.addSeries("y-y", new double[]{0, 0}, new double[]{-5, 5});
        chart.addSeries("y(x)2", xData2, yData2);
        new SwingWrapper(chart).displayChart();
        do {
            x = Math.pow((a * Math.pow(Math.cos(y0), first) + c * (-1) * Math.pow(x0, third))/((-1)*b), 1.0/second);
            y = Math.pow((e * (-1) * Math.pow(x0, second2) + f * Math.pow(x0, third2))/d, 1.0/first1);
            d1 = function1();
            d2 = function2();
            x0 = x;
            y0 = y;
            n++;
        }while (Math.abs(d1) > ex && Math.abs(d2) > ex);

    }

}

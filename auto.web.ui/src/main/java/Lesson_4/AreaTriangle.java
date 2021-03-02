package Lesson_4;

public class AreaTriangle {

    public static double getAreaTriangle(int a, int b, int c){
        double p = (a + b + c) / 2;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return s;
    }
}

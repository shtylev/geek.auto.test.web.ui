package Lesson_4;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAreaTriangle {

    private static Logger logger = LoggerFactory.getLogger(TestAreaTriangle.class);

    @ParameterizedTest
    @CsvSource({"2, 2, 2"})
    public void areaTriangleTest(int a, int b, int c){
        double result = AreaTriangle.getAreaTriangle(a, b, c); //1.7320508075688772
        Assertions.assertEquals(1.7320508075688772, result);
    }
}

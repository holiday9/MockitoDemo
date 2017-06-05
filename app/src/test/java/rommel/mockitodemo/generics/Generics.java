package rommel.mockitodemo.generics;

/**
 * Created by yuan on 2017/6/5.
 */

public class Generics {
    // (1)处的声明告诉编译器(2)(3)处的T是泛型。
    // (3)处的声明告诉编译器(4)处的T的类型在我这里
    // (3)决定了(4)(2)的类型
    public static <T>/*(1)*/ T/*(2)*/ getObject(Class<T>/*(3)*/ c) throws IllegalAccessException, InstantiationException {
        T/*(4)*/ instance = c.newInstance();

        return instance;
    }
}


















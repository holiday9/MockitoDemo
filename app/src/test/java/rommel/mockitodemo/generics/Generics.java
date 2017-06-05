package rommel.mockitodemo.generics;

/**
 * Created by yuan on 2017/6/5.
 */

public class Generics {
    public static <T> T getObject(Class<T> c) throws IllegalAccessException, InstantiationException {
        T instance = c.newInstance();

        return instance;
    }
}


















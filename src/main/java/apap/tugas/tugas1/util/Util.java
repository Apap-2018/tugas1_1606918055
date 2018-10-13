package apap.tugas.tugas1.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class Util {

    public static Object instanceCreator(String className, Map<String, Object> attrs) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
        Class<?> klass = Class.forName(className);
        Object instance = klass.newInstance();

        for(Map.Entry<String, Object> entry : attrs.entrySet()) {
            setAttr(instance, entry.getKey(), entry.getValue());
        }

        return instance;
    }

    private static void setAttr(Object instance, String attrName, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String methodName = "set" + attrName.substring(0, 1).toUpperCase() + attrName.substring(1);
        Method method = instance.getClass().getMethod(methodName, Object.class);
        method.invoke(instance, value);
    }
}

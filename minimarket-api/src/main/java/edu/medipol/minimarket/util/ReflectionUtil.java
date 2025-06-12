package edu.medipol.minimarket.util;

import edu.medipol.minimarket.annotation.Loggable;
import java.lang.reflect.Field;

public class ReflectionUtil {
    public static void logAnnotatedFields(Object object) throws IllegalAccessException {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Loggable.class)) {
                field.setAccessible(true);
                System.out.println("[LOGGABLE] Field: " + field.getName() + ", Value: " + field.get(object));
            }
        }
    }
}

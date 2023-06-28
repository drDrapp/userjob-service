package org.drdrapp.userjobservice.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> getDifferentFieldNames(Object obj1, Object obj2) throws IllegalAccessException {
        List<String> differentFields = new ArrayList<>();

        Class<?> clazz = obj1.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value1 = field.get(obj1);
            Object value2 = field.get(obj2);
            if (value1 == null && value2 == null) {
                continue;
            }
            if (value1 == null || !value1.equals(value2)) {
                String simpleName = clazz.getSimpleName();
                simpleName = simpleName.replace("Request","");
                simpleName = simpleName.replace("Dto","");
                differentFields.add(simpleName + '.' + field.getName());
            }
        }
        return differentFields;
    }
}
package ru.innotech.education.validator;

import ru.innotech.education.annotation.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class Validator {

    public static void checkStatic(Method method) throws ValidateException {
        if (!Modifier.isStatic(method.getModifiers())){
            throw new ValidateException(method.getName() + " не статический метод. Проверьте правильность использования аннотации");
        }
    }

    public static void checkPriority(Method method) throws Exception {
        if ( method.getDeclaredAnnotation(Test.class).priority() < 1 || method.getDeclaredAnnotation(Test.class).priority() > 10){
            throw new ValidateException(method.getName() + " неверное значение приоритета " + method.getDeclaredAnnotation(Test.class).priority() + ". Проверьте правильность использования аннотации");
        }
    }

    public static void checkCountMethods(List<Method> list, String name) throws Exception {
        if (list.stream().count() > 1){
            throw new ValidateException("Количество методов c аннотацией " + name + " больше 1. Проверьте правильность использования аннотации");
        }
    }
}

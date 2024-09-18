package ru.innotech.education;

import ru.innotech.education.annotation.*;
import ru.innotech.education.validator.Validator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {

    static Method beforeTest = null;
    static Method afterTest = null;

    static void runTests(Class c) throws Exception {
        List<Method> queue1 = new ArrayList<>();
        Queue<Method> queue2 = new PriorityQueue<>(
                (m1, m2) -> Integer.compare(m2.getDeclaredAnnotation(Test.class).priority(),
                        m1.getDeclaredAnnotation(Test.class).priority())
        );
        List<Method> queue3 = new ArrayList<>();
        Method m;

        Constructor<?> conEmpty = c.getConstructor();
        Object result = conEmpty.newInstance();
        Method[] methods = c.getDeclaredMethods();

        for (Method method : methods) {
            if(method.getDeclaredAnnotation(BeforeTest.class)!= null){
                beforeTest = method;
            } else if(method.getDeclaredAnnotation(AfterTest.class)!= null){
                afterTest = method;
            } else if (method.getDeclaredAnnotation(BeforeSuite.class) != null) {
                Validator.checkStatic(method);
                queue1.add(method);
            } else if (method.getDeclaredAnnotation(AfterSuite.class) != null) {
                Validator.checkStatic(method);
                queue3.add(method);
            } else {
                Validator.checkPriority(method);
                queue2.add(method);
            }
        }

        Validator.checkCountMethods(queue1, "BeforeSuite");
        Validator.checkCountMethods(queue3, "AfterSuite");


        invokeMethod(queue1.get(0), result);
        while ((m = queue2.poll()) != null) {
            invokeMethod(m, result);
        }
        invokeMethod(queue3.get(0), result);
    }

    static void invokeMethod(Method method, Object result) throws InvocationTargetException, IllegalAccessException {
        if (beforeTest != null){
            beforeTest.invoke(result);
        }
        method.invoke(result);
        if (afterTest != null){
            afterTest.invoke(result);
        }
    }
}

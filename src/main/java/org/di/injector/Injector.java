package org.di.injector;

import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Injector {
    private final Map<Class<?>, Object> context = new HashMap<>();
    private final String PACKAGE_NAME = "org.di";
    private static Injector instance;

    private Injector() {
        getClasses().stream().filter(a -> !a.isAnnotation()).forEach(this::registerBean);
    }

    public static Injector getInstance() {
        if (instance == null) {
            instance = new Injector();
        }
        return instance;
    }

    @SneakyThrows
    private Object registerBean(Class<?> beanClass){

        Constructor<?> constructor = beanClass.getDeclaredConstructors()[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] args = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (context.containsKey(parameterType)) {
                args[i] = context.get(parameterType);
            } else {
                args[i] = registerBean(parameterType);
            }

        }
        Object bean = constructor.newInstance(args);
        context.put(beanClass, bean);
        return bean;
    }

    private Set<Class<?>> getClasses() {
        Reflections reflections = new Reflections(PACKAGE_NAME);
        return reflections.getTypesAnnotatedWith(Component.class);
    }

    public Object getBean(Class<?> beanClass) {
        return context.get(beanClass);
    }

    public void printContext() {
        context.forEach((key, value) -> System.out.println("Class: " + key + " | Obj: " + value));
    }
}

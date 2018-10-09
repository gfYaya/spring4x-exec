package com.smart.intopass;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//Added by intopass, 注解只是个标记
public class BeanFactory {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean(Demo.class);
        beanFactory.registerBean(UserDao.class);

        Demo bean = beanFactory.getBean(Demo.class);
        System.out.println(bean.getUserDao());
    }

    private Set<Class<?>> beanSet = new HashSet<>();

    public <T> T getBean(Class<T> requiredType) {
        List<Class<?>> findList = beanSet.stream().filter(requiredType::isAssignableFrom)
                .collect(Collectors.toList());
        if (findList.size() == 0) {
            throw new RuntimeException("找不到指定类型的Bean");
        } else if (findList.size() > 1) {
            throw new RuntimeException("找到多个指定类型的Bean");
        } else {
            try {
                Class<?> aClass = findList.get(0);
                Object instance = aClass.newInstance();

                Arrays.stream(aClass.getDeclaredMethods())
                        .filter(method -> method.getName().matches("set.+"))
                        .forEach(method -> {
                            Inject inject = method.getAnnotation(Inject.class);
                            //判断该方法上面是否有这个注解标记,如果有则执行injectSetter
                            if (inject != null) {
                                injectSetter(instance, method);
                            }
                        });

                return (T) instance;
            } catch (Exception e) {
                throw new RuntimeException("构造Bean的过程中发生异常", e);
            }
        }
    }

    private void injectSetter(Object instance, Method method) {
        Arrays.stream(method.getParameterTypes()).forEach(paramClass -> {
            try {
                Object bean = getBean(paramClass);
                method.invoke(instance, bean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void registerBean(Class<?> registerClass) {
        beanSet.add(registerClass);
    }

    public static class Demo {
        private UserDao userDao;

        @Inject
        public void setUserDao(UserDao userDao) {
            this.userDao = userDao;
        }

        public UserDao getUserDao() {
            return userDao;
        }
    }

    public static class UserDao {
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Inject {
    }

}
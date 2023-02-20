package com.ancen.testreflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AncenReflection {

    /*
     * 构造函数
     * 无参
     * 一个参数
     * 多个参数
     * */

    // 无参
    public static Object create(String className) {
        Class[] paramTypes = new Class[]{};
        Object[] paramValues = new Object[]{};
        return create(className, paramTypes, paramValues);
    }

    // 无参
    public static Object create(Class cls) {
        Class[] paramTypes = new Class[]{};
        Object[] paramValues = new Object[]{};
        return create(cls, paramTypes, paramValues);
    }

    // 一个参数
    public static Object create(String className, Class paramType, Object paramValue) {
        Class[] paramTypes = new Class[]{paramType};
        Object[] paramValues = new Object[]{paramValue};
        return create(className, paramTypes, paramValues);
    }

    // 一个参数
    public static Object create(Class cls, Class paramType, Object paramValue) {
        Class[] paramTypes = new Class[]{paramType};
        Object[] paramValues = new Object[]{paramValue};
        return create(cls, paramTypes, paramValues);
    }

    // 多个参数
    public static Object create(String className, Class[] paramTypes, Object[] paramValues) {
        try {
            Class cls = Class.forName(className);
            Constructor ctor = cls.getDeclaredConstructor(paramTypes);
            return ctor.newInstance(paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 多个参数
    public static Object create(Class cls, Class[] paramTypes, Object[] paramValues) {
        try {
            Constructor ctor = cls.getDeclaredConstructor(paramTypes);
            return ctor.newInstance(paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 调用实例方法
     * 无参
     * 一个参数
     * 多个参数
     * */

    // 无参
    public static Object invokeInstanceMethod(Object obj, String methodName) {
        Class[] paramTypes = new Class[]{};
        Object[] paramValues = new Object[]{};
        return invokeInstanceMethod(obj, methodName, paramTypes, paramValues);
    }

    // 一个参数
    public static Object invokeInstanceMethod(Object obj, String methodName, Class paramType, Object paramValue) {
        Class[] paramTypes = new Class[]{paramType};
        Object[] paramValues = new Object[]{paramValue};
        return invokeInstanceMethod(obj, methodName, paramTypes, paramValues);
    }

    // 多个参数
    public static Object invokeInstanceMethod(Object obj, String methodName, Class[] paramTypes, Object[] paramValues) {
        if (obj == null) {
            return null;
        }
        try {
            // 调用private方法
            Method method = obj.getClass().getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return method.invoke(obj, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 调用静态方法
     * 无参
     * 一个参数
     * 多个参数
     * */

    // 无参
    public static Object invokeStaticMethod(String className, String methodName) {
        Class[] paramTypes = new Class[]{};
        Object[] paramValues = new Object[]{};
        return invokeStaticMethod(className, methodName, paramTypes, paramValues);
    }

    // 无参
    public static Object invokeStaticMethod(Class cls, String methodName) {
        Class[] paramTypes = new Class[]{};
        Object[] paramValues = new Object[]{};
        return invokeStaticMethod(cls, methodName, paramTypes, paramValues);
    }

    // 一个参数
    public static Object invokeStaticMethod(String className, String methodName, Class paramType, Object paramValue) {
        Class[] paramTypes = new Class[]{paramType};
        Object[] paramValues = new Object[]{paramValue};
        return invokeStaticMethod(className, methodName, paramTypes, paramValues);
    }

    // 一个参数
    public static Object invokeStaticMethod(Class cls, String methodName, Class paramType, Object paramValue) {
        Class[] paramTypes = new Class[]{paramType};
        Object[] paramValues = new Object[]{paramValue};
        return invokeStaticMethod(cls, methodName, paramTypes, paramValues);
    }

    // 多个参数
    public static Object invokeStaticMethod(String className, String methodName, Class[] paramTypes, Object[] paramValues) {
        try {
            Class cls = Class.forName(className);
            Method method = cls.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return method.invoke(null, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 多个参数
    public static Object invokeStaticMethod(Class cls, String methodName, Class[] paramTypes, Object[] paramValues) {
        try {
            Method method = cls.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return method.invoke(null, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 调用静态方法
     * 区分静态和实例
     * 传入参数：String className；Class cls；Object obj
     * */

    public static Object getFieldObject(String className, Object obj, String fieldName) {
        try {
            Class cls = Class.forName(className);
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getFieldObject(Class cls, Object obj, String filedName) {
        try {
            Field field = cls.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldObject(String className, Object obj, String fieldName, Object fieldValue) {
        try {
            Class cls = Class.forName(className);
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFieldObject(Class cls, Object obj, String filedName, Object fieldValue) {
        try {
            Field field = cls.getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(obj, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFieldObject(Object obj, String fieldName, Object fieldValue) {
        setFieldObject(obj.getClass(), obj, fieldName, fieldValue);
    }

    public static Object getStaticFieldObject(String className, String fieldName) {
        return getFieldObject(className, null, fieldName);
    }

    public static Object getStaticFieldObject(Class cls, String fieldName) {
        return getFieldObject(cls, null, fieldName);
    }

    public static Object getStaticFieldObject(Object obj, String fieldName) {
        return getFieldObject(obj.getClass(), null, fieldName);
    }

    public static void setStaticFieldObject(String classname, String fieldName, Object fieldValue) {
        setFieldObject(classname, null, fieldName, fieldValue);
    }

    public static void setStaticFieldObject(Class cls, String fieldName, Object fieldValue) {
        setFieldObject(cls, null, fieldName, fieldValue);
    }

    // 暂时不允许通过实例访问setStaticFieldObject来设置静态变量
}

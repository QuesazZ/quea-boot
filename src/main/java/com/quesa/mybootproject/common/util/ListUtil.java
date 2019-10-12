package com.quesa.mybootproject.common.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * lzm<br>
 * 通用工具类之按对象中某属性排序
 */
public class ListUtil {
    public static final String DESC = "desc";
    public static final String ASC = "asc";

    /**
     * 对list中的元素按升序排列.
     *
     * @param list  排序集合
     * @param field 排序字段
     * @return
     */
    public static List<?> sort(List<?> list, final String field) {
        return sort(list, field, null);
    }

    /**
     * 对list中的元素进行排序.
     *
     * @param list  排序集合
     * @param field 排序字段
     * @param sort  排序方式: ListUtil.DESC(降序) ListUtil.ASC(升序).
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<?> sort(List<?> list, final String field,
                               final String sort) {
        if (list == null || list.size() <= 0) {
            return list;
        }
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    if (a instanceof Map) {
                        if (((Map) a).get(field) == null && ((Map) a).get(field) == null) {
                            return 0;
                        }
                        if (((Map) a).get(field) == null) {
                            return -1;
                        }
                        if (((Map) b).get(field) == null) {
                            return 1;
                        }
                        return ((Map) a).get(field).toString().compareTo(((Map) b).get(field).toString());
                    }
                    Field f = a.getClass().getDeclaredField(field);
                    f.setAccessible(true);
                    Class<?> type = f.getType();
                    if (StringUtil.isEmpty(f.get(a)) && StringUtil.isEmpty(f.get(b))) {
                        return 0;
                    }
                    if (StringUtil.isEmpty(f.get(a))) {
                        return -1;
                    }
                    if (StringUtil.isEmpty(f.get(b))) {
                        return 1;
                    }
                    if (type == int.class) {
                        ret = ((Integer) f.getInt(a)).compareTo(f
                                .getInt(b));
                    } else if (type == double.class) {
                        ret = ((Double) f.getDouble(a)).compareTo(f
                                .getDouble(b));
                    } else if (type == long.class) {
                        ret = ((Long) f.getLong(a)).compareTo(f
                                .getLong(b));
                    } else if (type == float.class) {
                        ret = ((Float) f.getFloat(a)).compareTo(f
                                .getFloat(b));
                    } else if (type == Date.class) {
                        ret = ((Date) f.get(a)).compareTo((Date) f.get(b));
                    } else if (isImplementsOf(type, Comparable.class)) {
                        ret = ((Comparable) f.get(a)).compareTo(f
                                .get(b));
                    } else {
                        try {
                            ret = Double.valueOf((String) f.get(a)).compareTo(
                                    Double.valueOf((String) f.get(b)));
                        } catch (Exception e) {
                            ret = String.valueOf(f.get(a)).compareTo(
                                    String.valueOf(f.get(b)));
                        }
                    }

                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (sort != null && sort.toLowerCase().equals(DESC)) {
                    return -ret;
                } else {
                    return ret;
                }

            }
        });
        return list;
    }

    /**
     * 对list中的元素按fields和sorts进行排序,
     * fields[i]指定排序字段,sorts[i]指定排序方式.如果sorts[i]为空则默认按升序排列.
     *
     * @param list   排序集合
     * @param fields 排序字段-数组（一个或多个）
     * @param sorts  排序规则-数组（一个或多个）
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<?> sort(List<?> list, String[] fields, String[] sorts) {
        if (fields != null && fields.length > 0) {
            for (int i = fields.length - 1; i >= 0; i--) {
                final String field = fields[i];
                String tmpSort = ASC;
                if (sorts != null && sorts.length > i && sorts[i] != null) {
                    tmpSort = sorts[i];
                }
                final String sort = tmpSort;
                Collections.sort(list, new Comparator() {
                    @Override
                    public int compare(Object a, Object b) {
                        int ret = 0;
                        try {
                            Field f = a.getClass().getDeclaredField(field);
                            f.setAccessible(true);
                            Class<?> type = f.getType();
                            if (type == int.class) {
                                ret = ((Integer) f.getInt(a))
                                        .compareTo(f.getInt(b));
                            } else if (type == double.class) {
                                ret = ((Double) f.getDouble(a))
                                        .compareTo(f.getDouble(b));
                            } else if (type == long.class) {
                                ret = ((Long) f.getLong(a)).compareTo(f
                                        .getLong(b));
                            } else if (type == float.class) {
                                ret = ((Float) f.getFloat(a))
                                        .compareTo(f.getFloat(b));
                            } else if (type == Date.class) {
                                ret = ((Date) f.get(a)).compareTo((Date) f
                                        .get(b));
                            } else if (isImplementsOf(type, Comparable.class)) {
                                ret = ((Comparable) f.get(a))
                                        .compareTo(f.get(b));
                            } else {
                                ret = String.valueOf(f.get(a)).compareTo(
                                        String.valueOf(f.get(b)));
                            }

                        } catch (SecurityException e) {
                            e.printStackTrace();
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        if (sort != null && sort.toLowerCase().equals(DESC)) {
                            return -ret;
                        } else {
                            return ret;
                        }
                    }
                });
            }
        }
        return list;
    }

    /**
     * 默认按正序排列
     *
     * @param list   排序集合
     * @param method 排序方法 "getXxx()"
     * @return
     */
    public static List<?> sortByMethod(List<?> list, final String method) {
        return sortByMethod(list, method, null);
    }

    /**
     * 集合排序
     *
     * @param list   排序集合
     * @param method 排序方法 "getXxx()"
     * @param sort   排序方式: ListUtil.DESC(降序) ListUtil.ASC(升序).
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<?> sortByMethod(List<?> list, final String method,
                                       final String sort) {
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    Method m = a.getClass().getMethod(method, null);
                    m.setAccessible(true);
                    Class<?> type = m.getReturnType();
                    if (type == int.class) {
                        ret = ((Integer) m.invoke(a, null))
                                .compareTo((Integer) m.invoke(b, null));
                    } else if (type == double.class) {
                        ret = ((Double) m.invoke(a, null)).compareTo((Double) m
                                .invoke(b, null));
                    } else if (type == long.class) {
                        ret = ((Long) m.invoke(a, null)).compareTo((Long) m
                                .invoke(b, null));
                    } else if (type == float.class) {
                        ret = ((Float) m.invoke(a, null)).compareTo((Float) m
                                .invoke(b, null));
                    } else if (type == Date.class) {
                        ret = ((Date) m.invoke(a, null)).compareTo((Date) m
                                .invoke(b, null));
                    } else if (isImplementsOf(type, Comparable.class)) {
                        ret = ((Comparable) m.invoke(a, null))
                                .compareTo(m.invoke(b, null));
                    } else {
                        ret = String.valueOf(m.invoke(a, null)).compareTo(
                                String.valueOf(m.invoke(b, null)));
                    }

                    if (isImplementsOf(type, Comparable.class)) {
                        ret = ((Comparable) m.invoke(a, null))
                                .compareTo(m.invoke(b, null));
                    } else {
                        ret = String.valueOf(m.invoke(a, null)).compareTo(
                                String.valueOf(m.invoke(b, null)));
                    }

                } catch (NoSuchMethodException ne) {
                    System.out.println(ne);
                } catch (IllegalAccessException ie) {
                    System.out.println(ie);
                } catch (InvocationTargetException it) {
                    System.out.println(it);
                }

                if (sort != null && sort.toLowerCase().equals(DESC)) {
                    return -ret;
                } else {
                    return ret;
                }
            }
        });
        return list;
    }

    @SuppressWarnings("unchecked")
    public static List<?> sortByMethod(List<?> list, final String[] methods,
                                       final String[] sorts) {
        if (methods != null && methods.length > 0) {
            for (int i = methods.length - 1; i >= 0; i--) {
                final String method = methods[i];
                String tmpSort = ASC;
                if (sorts != null && sorts.length > i && sorts[i] != null) {
                    tmpSort = sorts[i];
                }
                final String sort = tmpSort;
                Collections.sort(list, new Comparator() {
                    @Override
                    public int compare(Object a, Object b) {
                        int ret = 0;
                        try {
                            Method m = a.getClass().getMethod(method, null);
                            m.setAccessible(true);
                            Class<?> type = m.getReturnType();
                            if (type == int.class) {
                                ret = ((Integer) m.invoke(a, null))
                                        .compareTo((Integer) m.invoke(b, null));
                            } else if (type == double.class) {
                                ret = ((Double) m.invoke(a, null))
                                        .compareTo((Double) m.invoke(b, null));
                            } else if (type == long.class) {
                                ret = ((Long) m.invoke(a, null))
                                        .compareTo((Long) m.invoke(b, null));
                            } else if (type == float.class) {
                                ret = ((Float) m.invoke(a, null))
                                        .compareTo((Float) m.invoke(b, null));
                            } else if (type == Date.class) {
                                ret = ((Date) m.invoke(a, null))
                                        .compareTo((Date) m.invoke(b, null));
                            } else if (isImplementsOf(type, Comparable.class)) {
                                ret = ((Comparable) m.invoke(a, null))
                                        .compareTo(m.invoke(b,
                                                null));
                            } else {
                                ret = String.valueOf(m.invoke(a, null))
                                        .compareTo(
                                                String.valueOf(m
                                                        .invoke(b, null)));
                            }

                        } catch (NoSuchMethodException ne) {
                            System.out.println(ne);
                        } catch (IllegalAccessException ie) {
                            System.out.println(ie);
                        } catch (InvocationTargetException it) {
                            System.out.println(it);
                        }

                        if (sort != null && sort.toLowerCase().equals(DESC)) {
                            return -ret;
                        } else {
                            return ret;
                        }
                    }
                });
            }
        }
        return list;
    }

    /**
     * 判断对象实现的所有接口中是否包含szInterface
     *
     * @param clazz
     * @param szInterface
     * @return
     */
    public static boolean isImplementsOf(Class<?> clazz, Class<?> szInterface) {
        boolean flag = false;

        Class<?>[] face = clazz.getInterfaces();
        for (Class<?> c : face) {
            if (c == szInterface) {
                flag = true;
            } else {
                flag = isImplementsOf(c, szInterface);
            }
        }

        if (!flag && null != clazz.getSuperclass()) {
            return isImplementsOf(clazz.getSuperclass(), szInterface);
        }

        return flag;
    }

    /**
     * 在对象集合中获取指定字段集合
     *
     * @param objList
     * @param fieldName
     * @return
     */
    public static List<String> getFieldList(List<?> objList, String fieldName) {
        if (CollectionUtils.isNotEmpty(objList)) {
            return  (List<String>) CollectionUtils.collect(objList, arg0 -> {
                try {
                    return arg0.getClass().getDeclaredMethod("get" + StringUtil.capitalize(fieldName)).invoke(arg0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return new ArrayList<>();
            });
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 在对象集合中获取指定字段集合
     *
     * @param jsonArray
     * @param fieldName
     * @return
     */
    public static List<String> getFieldList(JSONArray jsonArray, String fieldName) {
        List<String> list = new ArrayList<>();
        try {
            list = (List<String>) CollectionUtils.collect(jsonArray, new Transformer() {
                @Override
                public Object transform(Object arg0) {
                    try {
                        return ((JSONObject) arg0).getString(fieldName);
                    } catch (Exception e) {
                    }
                    return "";
                }
            });
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    /**
     * 在对象集合中获取指定字段集合
     *
     * @param jsonArray
     * @param fieldName
     * @return
     */
    public static List<Object> getFieldListForObject(JSONArray jsonArray, String fieldName, Class clazz) {
        try {
            List<Object> list = (List<Object>) CollectionUtils.collect(jsonArray, new Transformer() {
                @Override
                public Object transform(Object arg0) {
                    try {
                        return ((JSONObject)((JSONObject) arg0).get(fieldName)).toJavaObject(clazz);
                    } catch (Exception e) {
                    }
                    return null;
                }
            });
            return list;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 将集合分割成指多个集合
     *
     * @param list
     * @param len
     * @return
     */
    public static List<List<?>> splitList(List<?> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<?>> result = new ArrayList<List<?>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<?> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    /**
     * 判断list是否不为空
     *
     * @param list
     * @return
     */
    public static boolean isNotEmpty(List<?> list) {
        if (null != list && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 判断list是否为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List<?> list) {
        if (null == list || list.isEmpty()) {
            return true;
        }
        return false;
    }

}

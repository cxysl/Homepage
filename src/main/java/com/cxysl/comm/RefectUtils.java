package com.cxysl.comm;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RefectUtils {
	/**
	 * 得到 对象属性集合
	 * @return
	 */
	public static Map<String, Object> getMap(Object target) {
		Map<String, Object> map = new HashMap<String, Object>();
		getFiledName(map, target, target.getClass());
		return map;
	}

	public static void getFiledName(Map<String, Object> map, Object o,
			Class<?> clazz) {
		try {
			Field[] fields = clazz.getDeclaredFields();
			Object value = null;
			for (Field field : fields) {
				value = getFieldValueByName(field, o, clazz);
				if (value != null) {
					map.put(field.getName(), value);
				}
			}
			if (clazz.getSuperclass() != null) {
				getFiledName(map, o, clazz.getSuperclass());
			}
		} catch (Exception e) {

		}
	}
	
	
	public static Object getFieldValueByName(String fieldName, Object o,
			Class<?> clazz) {
		Object value = null;
		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true); // 设置私有属性范围
			value = field.get(o);
		} catch (Exception e) {
			if (clazz.getSuperclass() != null) {
				value = getFieldValueByName(fieldName, o, clazz.getSuperclass());
			}
		}
		return value;
	}
	
	public static Object getFieldValueByName(Field field, Object o,
			Class<?> clazz) {
		Object value = null;
		try {
			field.setAccessible(true); // 设置私有属性范围
			value = field.get(o);
		} catch (Exception e) {
			if (clazz.getSuperclass() != null) {
				value = getFieldValueByName(field, o, clazz.getSuperclass());
			}
		}
		return value;
	}

	/**
	 * 使用反射对操作对象设置值
	 * 
	 * @param fieldName
	 *            属性名称
	 * @param source
	 *            操作对象
	 * @param value
	 *            属性值
	 * @param clazz
	 */
	public static void setFieldValue(String fieldName, Object source, Object value,
			Class<?> clazz) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true); // 设置私有属性范围
			field.set(source, value);
		} catch (Exception e) {
			if (clazz.getSuperclass() != null) {
				setFieldValue(fieldName, source, value, clazz.getSuperclass());
			}
		}
	}

	/**
	 * 
	 * @param source
	 *            需要设置属性值的对象
	 * @param target
	 *            目标对象
	 */
	public static void setFieldValues(Object source, Object target) {
		Map<String, Object> map = getMap(target);
		for (String fileName : map.keySet()) {
			setFieldValue(fileName, source, map.get(fileName), source.getClass());
		}
	}

}

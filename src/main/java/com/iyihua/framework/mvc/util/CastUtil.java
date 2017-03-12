package com.iyihua.framework.mvc.util;

/**
 * 转型操作工具类
 * 
 * @author iyihua
 *
 */
public final class CastUtil {

	/**
	 * 转为String型
	 * 
	 * @param obj
	 * @return
	 */
	public static String castString(Object obj) {
		return CastUtil.castString(obj, "");
	}

	/**
	 * 转为String型(提供默认值)
	 * 
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static String castString(Object obj, String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 转为Double型
	 * 
	 * @param obj
	 * @return
	 */
	public static double castDouble(Object obj) {
		return CastUtil.castDouble(obj, 0);
	}

	/**
	 * 转为Double型(提供默认值)
	 * 
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static double castDouble(Object obj, double defaultValue) {
		double doubleValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					doubleValue = Double.parseDouble(strValue);
				} catch (Exception e) {
					doubleValue = defaultValue;
				}
			}
		}
		return doubleValue;
	}

	/**
	 * 转为Long型
	 * 
	 * @param obj
	 * @return
	 */
	public static long castLong(Object obj) {
		return CastUtil.castLong(obj, 0);
	}

	/**
	 * 转为Long型(提供默认值)
	 * 
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static long castLong(Object obj, long defaultValue) {
		long longValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isEmpty(strValue)) {
				try {
					longValue = Long.parseLong(strValue);
				} catch (Exception e) {
					longValue = defaultValue;
				}
			}
		}
		return longValue;
	}

	/**
	 * 转为Int型
	 * 
	 * @param obj
	 * @return
	 */
	public static int castInt(Object obj) {
		return CastUtil.castInt(obj, 0);
	}

	/**
	 * 转为Int型(提供默认值)
	 * 
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static int castInt(Object obj, int defaultValue) {
		int intValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isEmpty(strValue)) {
				try {
					intValue = Integer.parseInt(strValue);
				} catch (Exception e) {
					intValue = defaultValue;
				}
			}
		}
		return intValue;
	}

	/**
	 * 转为Boolean型
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean castBoolean(Object obj) {
		return CastUtil.castBoolean(obj, false);
	}

	/**
	 * 转为Boolean型(提供默认值)
	 * 
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static boolean castBoolean(Object obj, boolean defaultValue) {
		boolean booleanValue = defaultValue;
		if (obj != null) {
			Boolean.parseBoolean(castString(obj));
		}
		return booleanValue;
	}
}

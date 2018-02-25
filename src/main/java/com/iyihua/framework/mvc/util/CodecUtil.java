package com.iyihua.framework.mvc.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iyihua.framework.mvc.ConfigConstant;

/**
 * 编码与解码操作工具类
 *
 * @since 1.0
 */
public class CodecUtil {

	private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);

	/**
	 * 将 URL 编码
	 */
	public static String encodeURL(String str) {
		String target;
		try {
			target = URLEncoder.encode(str, ConfigConstant.UTF_8);
		} catch (Exception e) {
			logger.error("编码出错！", e);
			throw new RuntimeException(e);
		}
		return target;
	}

	/**
	 * 将 URL 解码
	 */
	public static String decodeURL(String str) {
		String target;
		try {
			target = URLDecoder.decode(str, ConfigConstant.UTF_8);
		} catch (Exception e) {
			logger.error("解码出错！", e);
			throw new RuntimeException(e);
		}
		return target;
	}

}

package com.wenyuankeji.spring.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
public class SensitiveWordUtil {
	public static int minMatchTYpe = 1; // 最小匹配规则
	public static int maxMatchType = 2; // 最大匹配规则

	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
	 * 中 = { isEnd = 0 国 = {<br>
	 * isEnd = 1 人 = {isEnd = 0 民 = {isEnd = 1} } 男 = { isEnd = 0 人 = { isEnd =
	 * 1 } } } } 五 = { isEnd = 0 星 = { isEnd = 0 红 = { isEnd = 0 旗 = { isEnd = 1
	 * } } } }
	 * @param keyWordSet 敏感词库
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap addSensitiveWordToHashMap(Set<String> keyWordSet) {
		HashMap sensitiveWordMap = new HashMap(keyWordSet.size()); // 初始化敏感词容器，减少扩容操作
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		// 迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while (iterator.hasNext()) {
			key = iterator.next(); // 关键字
			nowMap = sensitiveWordMap;
			for (int i = 0; i < key.length(); i++) {
				char keyChar = key.charAt(i); // 转换成char型
				Object wordMap = nowMap.get(keyChar); // 获取

				if (wordMap != null) { // 如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				} else { // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String, String>();
					newWorMap.put("isEnd", "0"); // 不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}

				if (i == key.length() - 1) {
					nowMap.put("isEnd", "1"); // 最后一个
				}
			}
		}
		return sensitiveWordMap;
	}

	/**
	 * 判断文字是否包含敏感字符
	 * @param txt 文字
	 * @param matchType	匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @return 若包含返回true，否则返回false
	 */
	public static boolean isContaintSensitiveWord(String txt, int matchType,
			Set<String> set) {
		boolean flag = false;
		for (int i = 0; i < txt.length(); i++) {
			int matchFlag = CheckSensitiveWord(txt, i, matchType, set);		// 判断是否包含敏感字符
			if (matchFlag > 0) {		// 大于0存在，返回true
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 获取文字中的敏感词
	 * @param txt 文字
	 * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @return
	 */
	public static String getSensitiveWord(String txt, int matchType, Set<String> set){
		String message = "";
		Set<String> sensitiveWordList = new HashSet<String>();		
		for(int i = 0 ; i < txt.length() ; i++){
			int length = CheckSensitiveWord(txt, i, matchType, set);    //判断是否包含敏感字符
			if(length > 0){    //存在,加入list中
				sensitiveWordList.add(txt.substring(i, i+length));
				i = i + length - 1;    //减1的原因，是因为for会自增
			}
		}
				
		Iterator<String> iterator = sensitiveWordList.iterator();
		while (iterator.hasNext()) {
			message += "【" + iterator.next() + "】";
		}
		return message;
	}
	
	/**
	 * 获取替换字符串
	 * @param replaceChar
	 * @param length
	 * @return
	 */
	public static String getReplaceChars(String replaceChar,int length){
		String resultReplace = replaceChar;
		for(int i = 1 ; i < length ; i++){
			resultReplace += replaceChar;
		}
		return resultReplace;
	}

	/**
	 * 检查文字中是否包含敏感字符，检查规则如下
	 * @param txt
	 * @param beginIndex
	 * @param matchType
	 * @return，如果存在，则返回敏感词字符的长度，不存在返回0
	 */
	@SuppressWarnings({ "rawtypes" })
	public static int CheckSensitiveWord(String txt, int beginIndex,
			int matchType, Set<String> set) {
		boolean flag = false;		// 敏感词结束标识位：用于敏感词只有1位的情况
		int matchFlag = 0;		// 匹配标识数默认为0
		char word = 0;
		Map nowMap = addSensitiveWordToHashMap(set);
		for (int i = beginIndex; i < txt.length(); i++) {
			word = txt.charAt(i);
			nowMap = (Map) nowMap.get(word);	// 获取指定key
			if (nowMap != null) {				// 存在，则判断是否为最后一个
				matchFlag++;					// 找到相应key，匹配标识+1
				if ("1".equals(nowMap.get("isEnd"))) {		// 如果为最后一个匹配规则,结束循环，返回匹配标识数
					flag = true;							// 结束标志位为true
					if (minMatchTYpe == matchType) {		// 最小规则，直接返回,最大规则还需继续查找
						break;
					}
				}
			} else {		// 不存在，直接返回
				break;
			}
		}
		if (matchFlag < 2 || !flag) {		// 长度必须大于等于1，为词
			matchFlag = 0;
		}
		return matchFlag;
	}
	
}
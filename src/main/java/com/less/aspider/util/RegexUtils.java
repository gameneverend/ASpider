package com.less.aspider.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by deeper on 2017/12/20.
 */

public class RegexUtils {

    private Pattern regex;

    public static RegexUtils create(String expr) {
        return new RegexUtils(expr);
    }

    public RegexUtils(String regexStr){
        init(regexStr);
    }

    private void init(String regexStr){
        try {
            this.regex = Pattern.compile(regexStr);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("invalid regex " + regexStr, e);
        }
    }

    /**
     * 获取正则表达式所有结果中指定的group
     * @param html
     * @return
     */
    public List<String> selectList(String html,int group) {
        List<String> list = new ArrayList<String>();
        Matcher matcher = regex.matcher(html);
        while (matcher.find()) {
            String result = matcher.group(group);
            list.add(result);
        }
        return list;
    }

    /**
     * 获取正则表达式所有结果中的所有group
     * @param html
     * @return
     */
    public List<String[]> selectList(String html) {
        List<String[]> list = new ArrayList<String[]>();
        Matcher matcher = regex.matcher(html);
        while (matcher.find()) {
            String[] groups = new String[matcher.groupCount() + 1];
            for (int i = 0; i < groups.length; i++) {
                groups[i] = matcher.group(i);
            }
            list.add(groups);
        }
        return list;
    }

    /**
     * 只获取正则表达式的第一个结果中指定的group
     * @param html
     * @return
     */
    public String selectSingle(String html,int group) {
        Matcher matcher = regex.matcher(html);
        if (matcher.find()) {
            String result = matcher.group(group);
            return result;
        }
        return "";
    }

    /**
     * 只获取正则表达式的第一个结果
     * @param html
     * @return
     */
    public String[] selectSingle(String html) {
        Matcher matcher = regex.matcher(html);
        if (matcher.find()) {
            String[] groups = new String[matcher.groupCount() + 1];
            for (int i = 0; i < groups.length; i++) {
                groups[i] = matcher.group(i);
            }
            return groups;
        }
        return new String[]{""};
    }

    public boolean matchers(String html){
        Matcher matcher = regex.matcher(html);
        return matcher.matches();
    }
}

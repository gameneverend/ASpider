package com.less.aspider.util;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by deeper on 2017/12/15.
 */

public class UrlUtils {

    /**
     *
     * @param url url
     * @return new url
     * @deprecated
     */
    public static String encodeIllegalCharacterInUrl(String url) {
        return url.replace(" ", "%20");
    }

    public static String fixIllegalCharacterInUrl(String url) {
        //TODO more charator support
        return url.replace(" ", "%20").replaceAll("#+", "#");
    }

    private static Pattern patternForProtocal = Pattern.compile("[\\w]+://");

    private static final Pattern patternForCharset = Pattern.compile("charset\\s*=\\s*['\"]*([^\\s;'\"]*)", Pattern.CASE_INSENSITIVE);

    public static String getCharset(String contentType) {
        Matcher matcher = patternForCharset.matcher(contentType);
        if (matcher.find()) {
            String charset = matcher.group(1);
            if (Charset.isSupported(charset)) {
                return charset;
            }
        }
        return null;
    }
}

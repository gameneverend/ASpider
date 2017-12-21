package com.less.aspider.pipeline;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 多文件pipeline
 * Created by deeper on 2017/12/21.
 */

public class FilePipeline implements Pipeline {

    @Override
    public void process(Map<String, Object> fields) {
        String path = DigestUtils.md5Hex(fields.get("url").toString()) + ".html";
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(path)),"UTF-8"));
            for (Map.Entry<String, Object> entry : fields.entrySet()) {
                printWriter.println(entry.getKey() + ":\t" + entry.getValue());
            }
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.less.aspider.pipeline;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

import static org.apache.commons.io.FileUtils.getFile;

/**
 * 单文件pipeline(synchronized)
 * Created by deeper on 2017/12/21.
 */

public class OneFilePipeline implements Pipeline {
    private PrintWriter printWriter;

    public OneFilePipeline(String path){
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFile(path)), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void process(Map<String, Object> fields) {
        synchronized (OneFilePipeline.class) {
            for (Map.Entry<String, Object> entry : fields.entrySet()) {
                printWriter.println(entry.getKey() + ":\t" + entry.getValue());
            }
            printWriter.flush();
        }
    }
}

package com.less.aspider.pipeline;

import java.util.Map;

/**
 *
 * @author deeper
 * @date 2017/12/17
 */

public class ConsolePipeline implements Pipeline {

    @Override
    public void process(Map<String, Object> fields) {
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            System.err.println(entry.getKey() + ":\t" + entry.getValue());
        }
    }
}

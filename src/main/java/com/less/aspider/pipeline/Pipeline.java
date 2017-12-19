package com.less.aspider.pipeline;

import java.util.Map;

/**
 * Created by deeper on 2017/12/17.
 */

public interface Pipeline {
    void process(Map<String, Object> fields);
}

package com.trip.commons.core.converter;

import com.trip.commons.core.utils.JsonUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 使用Gson转化Spring数据
 *
 * @author fqh
 * @create 2016-12-27 6:49
 */
public class GsonMessageConverter extends GsonHttpMessageConverter {

    public GsonMessageConverter() {
        super.setGson(JsonUtils.getGson());
    }

    protected void writeInternal(Object o, Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        super.writeInternal(o, null, outputMessage);
    }

}

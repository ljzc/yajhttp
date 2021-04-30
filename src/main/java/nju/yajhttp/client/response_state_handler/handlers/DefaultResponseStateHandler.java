package nju.yajhttp.client.response_state_handler.handlers;

import nju.yajhttp.message.Request;
import nju.yajhttp.message.Response;
import nju.yajhttp.message.Status;
import nju.yajhttp.message.Util;

import java.nio.charset.StandardCharsets;

public class DefaultResponseStateHandler extends ResponseStateHandler{
    public DefaultResponseStateHandler() {
        super(null);
    }

    @Override
    public String handle(Request oldRequest, Response response) {
        StringBuilder builder = new StringBuilder("没有针对").append(response.status().toString()).append("的处理器，使用默认处理器直接输出响应body部分！\n");

        try {
            throw new RuntimeException(builder.toString());
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }

        return bytes2Str(response.toBytes());
    }
}

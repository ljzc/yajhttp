package nju.yajhttp.client.command_handler_pipeline.handlerimp;

import nju.yajhttp.client.command_handler_pipeline.ConstructRequestCommandHandler;
import nju.yajhttp.client.command_handler_pipeline.Priority;
import nju.yajhttp.message.Request;

import java.util.Arrays;

public class HeaderCommandHandler extends ConstructRequestCommandHandler {
    public HeaderCommandHandler() {
        super(Priority.POSTPROCESS, "H", "header", true, "header", "指定发送请求时的 header，可以多次使用以指定多个 header");
    }

    @Override
    public Request handle(String[] args, Request request) {
        //todo

        return null;
    }
}

package nju.yajhttp.client.command_handler_pipeline.handlerimp;

import nju.yajhttp.client.command_handler_pipeline.CommandHandler;
import nju.yajhttp.client.command_handler_pipeline.ConstructRequestCommandHandler;
import nju.yajhttp.client.command_handler_pipeline.Priority;
import nju.yajhttp.message.Request;

import java.util.Arrays;

public class DataCommandHandler extends ConstructRequestCommandHandler {
    public DataCommandHandler() {
        super(Priority.PROCESSING, "d", "data", true, "data", "指定post请求以及body");
    }

    @Override
    public Request handle(String[] args, Request request) {
        //todo


        return null;
    }
}

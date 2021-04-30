package nju.yajhttp.client.command_handler_pipeline.handlerimp;

import nju.yajhttp.client.command_handler_pipeline.ConstructRequestCommandHandler;
import nju.yajhttp.client.command_handler_pipeline.Priority;
import nju.yajhttp.message.Request;

import java.util.Arrays;

public class UserCommandHandler extends ConstructRequestCommandHandler {
    public UserCommandHandler() {
        super(Priority.PROCESSING, "u", "user", true, "username:password", "指定用户名和密码");
    }

    @Override
    public Request handle(String[] args, Request request) {
        //todo

        return null;
    }
}

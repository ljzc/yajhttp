package nju.yajhttp.client.command_handler_pipeline.handlerimp;

import nju.yajhttp.client.command_handler_pipeline.ConstructRequestCommandHandler;
import nju.yajhttp.client.command_handler_pipeline.Priority;
import nju.yajhttp.message.Header;
import nju.yajhttp.message.Request;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class UserCommandHandler extends ConstructRequestCommandHandler {
    public UserCommandHandler() {
        super(Priority.PROCESSING, "u", "user", true, "username:password", "指定用户名和密码");
    }

    @Override
    public Request handle(String[] args, Request request) {
        //todo
        String nameAndPsw = args[args.length - 1]; //有多个的时候取最后一个参数作为最终结果
        request.header(new Header("Authorization", "Basic " + Base64.getEncoder().encodeToString(nameAndPsw.getBytes(StandardCharsets.US_ASCII))));
        return request;
    }
}

package nju.yajhttp.client.command_handler_pipeline.handlerimp;

import nju.yajhttp.client.command_handler_pipeline.BeforePrintingCommendHandler;
import nju.yajhttp.client.command_handler_pipeline.Priority;

import java.util.Arrays;

public class OutputCommandHandler extends BeforePrintingCommendHandler {
    public OutputCommandHandler() {
        super(Priority.PROCESSING, "o", null, true, "file", "输出到文件");
    }

    @Override
    public String handle(String[] args, String responseContent) {
        //todo

        return null;
    }
}

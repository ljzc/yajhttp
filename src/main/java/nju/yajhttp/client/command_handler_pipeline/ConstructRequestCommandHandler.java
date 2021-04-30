package nju.yajhttp.client.command_handler_pipeline;

import nju.yajhttp.message.Request;
import org.apache.commons.cli.CommandLine;

public abstract class ConstructRequestCommandHandler extends CommandHandler{
    public ConstructRequestCommandHandler(Priority priority, String shortOpt, String longOpt, boolean hasArg, String argName, String description) {
        super(priority, shortOpt, longOpt, hasArg, argName, description);
    }

    public abstract Request handle(String[] args, Request request);
}

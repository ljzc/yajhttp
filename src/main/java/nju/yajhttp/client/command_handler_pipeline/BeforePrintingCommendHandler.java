package nju.yajhttp.client.command_handler_pipeline;

import org.apache.commons.cli.CommandLine;

public abstract class BeforePrintingCommendHandler extends CommandHandler{
    public BeforePrintingCommendHandler(Priority priority, String shortOpt, String longOpt, boolean hasArg, String argName, String description) {
        super(priority, shortOpt, longOpt, hasArg, argName, description);
    }

    public abstract String handle(String[] args, String responseContent);
}

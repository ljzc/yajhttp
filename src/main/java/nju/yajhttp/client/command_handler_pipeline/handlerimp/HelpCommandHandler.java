package nju.yajhttp.client.command_handler_pipeline.handlerimp;

import nju.yajhttp.client.command_handler_pipeline.CommandHandler;
import nju.yajhttp.client.command_handler_pipeline.Priority;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class HelpCommandHandler extends CommandHandler {
    private String usage;
    private Options options;
    public HelpCommandHandler(String usage) {
        super(Priority.PREPROCESS, "h", "help", false, null, "打印帮助信息");
        this.usage = usage;
    }

    public void handle(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(usage, options);
    }
}

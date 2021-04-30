package nju.yajhttp.client.command_handler_pipeline;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

public abstract class CommandHandler implements Comparable<CommandHandler>{
    private final Priority priority;
    private final String shortOpt;
    private final String longOpt;
    private final boolean hasArg;
    private final String argName;
    private final String description;


    public CommandHandler(Priority priority, String shortOpt, String longOpt, boolean hasArg, String argName, String description) {
        this.priority = priority;
        this.shortOpt = shortOpt;
        this.longOpt = longOpt;
        this.hasArg = hasArg;
        this.argName = argName;
        this.description = description;
    }

    @Override
    public int compareTo(CommandHandler o) {
        return this.priority.compareTo(o.priority);
    }

    public Option getOption(){
        return Option
                .builder(shortOpt)
                .longOpt(longOpt)
                .hasArg(hasArg)
                .argName(argName)
                .desc(description)
                .build();
    }

    public String getShortOpt() {
        return shortOpt;
    }
}

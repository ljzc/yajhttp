package nju.yajhttp.client.command_handler_pipeline;


import lombok.Cleanup;
import nju.yajhttp.client.command_handler_pipeline.handlerimp.HelpCommandHandler;
import nju.yajhttp.client.response_state_handler.ResponseStateHandlerMapper;

import nju.yajhttp.message.*;
import org.apache.commons.cli.*;
import org.reflections.Reflections;

import java.io.IOException;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class CommandHandlerPipeline {
    public static final String USAGE = "Client [options] [url...]";
    public static final String AUTO_SCAN_PACKAGE_NAME = "nju.yajhttp.client.command_handler_pipeline.handlerimp";

    private ArrayList<ConstructRequestCommandHandler> constructRequestCommandHandlers;
    private ArrayList<BeforePrintingCommendHandler> beforePrintingCommendHandlers;
    private HelpCommandHandler helpCommandHandler;

    private Options options;

    public static CommandHandlerPipeline getInstance() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return new CommandHandlerPipeline();
    }



    private static <T extends CommandHandler> ArrayList<T> loadHandlers(String packageName, Class<T> superClass, Options options) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ArrayList<T> handlers = new ArrayList<>();
        Reflections reflections = new Reflections(packageName);
        for(Class<? extends T> cls : reflections.getSubTypesOf(superClass)){
            T handler = cls.getDeclaredConstructor().newInstance();
            options.addOption(handler.getOption());
            handlers.add(handler);
        }
        handlers.sort(CommandHandler::compareTo);
        return  handlers;
    }

    private CommandHandlerPipeline() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        options = new Options();
        helpCommandHandler = new HelpCommandHandler(USAGE);
        options.addOption(helpCommandHandler.getOption());
        constructRequestCommandHandlers = loadHandlers(AUTO_SCAN_PACKAGE_NAME, ConstructRequestCommandHandler.class, options);
        beforePrintingCommendHandlers = loadHandlers(AUTO_SCAN_PACKAGE_NAME, BeforePrintingCommendHandler.class, options);
    }


    public void handleCommand(String[] args) throws IOException {
        try {
            CommandLine commandLine = new DefaultParser().parse(options, args);
            if(commandLine.hasOption(helpCommandHandler.getShortOpt())){
                helpCommandHandler.handle(options);
                return;
            }
            String[] targetHosts = commandLine.getArgs();
            StringBuilder outputMessage = new StringBuilder();
            for(String targetHost : targetHosts){
                URI uri = new URI(targetHost);
                Request request = new Request()
                        .method(Method.GET)
                        .version(Version.HTTP1_0)
                        .uri(uri).header("Host", uri.host());

                for(ConstructRequestCommandHandler handler : constructRequestCommandHandlers){
                    if(commandLine.hasOption(handler.getShortOpt())){
                       request = handler.handle(commandLine.getOptionValues(handler.getShortOpt()), request);
                    }
                }

                var port = 0;
                switch (uri.scheme()) {
                    case "http":
                        port = 80;
                        break;
                    default:
                        throw new Error();
                }

                @Cleanup
                Socket socket = new Socket(uri.host(), port);
                OutputStream os = socket.getOutputStream();
                request.write(os);
                Response response = Response.read(socket.getInputStream());

                outputMessage.append(ResponseStateHandlerMapper.getInstance().map(response.status()).handle(request, response));
            }

            String toPrint = outputMessage.toString();
            for(BeforePrintingCommendHandler handler : beforePrintingCommendHandlers){
                if(commandLine.hasOption(handler.getShortOpt())){
                    toPrint = handler.handle(commandLine.getOptionValues(handler.getShortOpt()), toPrint);
                }
            }
            System.out.println(toPrint);
        } catch (ParseException e) {
            helpCommandHandler.handle(options);
        } catch (UnknownHostException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }








}

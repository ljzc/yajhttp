package nju.yajhttp.client.response_state_handler.handlers.handlerimp;

import lombok.SneakyThrows;
import nju.yajhttp.client.Util;
import nju.yajhttp.client.response_state_handler.ResponseStateHandlerMapper;
import nju.yajhttp.client.response_state_handler.handlers.ResponseStateHandler;
import nju.yajhttp.message.Request;
import nju.yajhttp.message.Response;
import nju.yajhttp.message.Status;
import nju.yajhttp.message.URI;

public class FoundResponseStateHandler extends ResponseStateHandler {
    public FoundResponseStateHandler() {
        super(Status.FOUND);
    }

    @Override
    @SneakyThrows
    public String handle(Request oldRequest, Response response) {
        //todo

        URI newUri;
        String location = response.header("Location");
        if(location.charAt(0) == '/'){
            newUri = oldRequest.uri().relative(location);
        }else {
            newUri = new URI(location);
        }
        oldRequest.uri(newUri);
        Response newResponse = Util.sendRequest(newUri, oldRequest);

        return ResponseStateHandlerMapper.getInstance().map(newResponse.status()).handle(oldRequest, newResponse);
    }
}

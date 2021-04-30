package nju.yajhttp.client.response_state_handler.handlers.handlerimp;

import nju.yajhttp.client.response_state_handler.handlers.ResponseStateHandler;
import nju.yajhttp.message.Request;
import nju.yajhttp.message.Response;
import nju.yajhttp.message.Status;

public class NotModifiedResponseStateHandler extends ResponseStateHandler {
    public NotModifiedResponseStateHandler() {
        super(Status.NOT_MODIFIED);
    }

    @Override
    public String handle(Request oldRequest, Response response) {
        //todo
        return status.toString();
    }
}

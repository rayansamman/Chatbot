package response.factory;

import response.Response;

public interface ResponseFactory {
    Response createGreetingResponse();
    Response createFarewellResponse();
    Response createHelpResponse();
}

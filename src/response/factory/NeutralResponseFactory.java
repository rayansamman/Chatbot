package response.factory;

import response.Response;
import response.types.FarewellResponse;
import response.types.GreetingResponse;
import response.types.HelpResponse;

public class NeutralResponseFactory implements ResponseFactory {

    @Override
    public Response createGreetingResponse() {
        return new GreetingResponse("Hello! How can I assist you?");
    }

    @Override
    public Response createFarewellResponse() {
        return new FarewellResponse("Goodbye! Have a great day!");
    }

    @Override
    public Response createHelpResponse() {
        return new HelpResponse();
    }
}

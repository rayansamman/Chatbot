package response.factory;

import response.Response;
import response.types.FarewellResponse;
import response.types.GreetingResponse;
import response.types.HelpResponse;

public class HappyResponseFactory implements ResponseFactory {

    @Override
    public Response createGreetingResponse() {
        return new GreetingResponse("Hey there! So happy to see you!");
    }

    @Override
    public Response createFarewellResponse() {
        return new FarewellResponse("Goodbye! Hope to see you soon!");
    }

    @Override
    public Response createHelpResponse() {
        return new HelpResponse();
    }
}

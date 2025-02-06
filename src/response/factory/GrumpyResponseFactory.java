package response.factory;

import response.Response;
import response.types.FarewellResponse;
import response.types.GreetingResponse;
import response.types.HelpResponse;

public class GrumpyResponseFactory implements ResponseFactory {

    @Override
    public Response createGreetingResponse() {
        return new GreetingResponse("Oh... it's you. What do you want?");
    }

    @Override
    public Response createFarewellResponse() {
        return new FarewellResponse("Finally, you're leaving. Bye.");
    }

    @Override
    public Response createHelpResponse() {
        return new HelpResponse();
    }
}

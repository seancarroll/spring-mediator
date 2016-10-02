package mediator;

/**
 * Implementation of the dispatcher (aka command bus) that dispatches requests
 * to the handlers subscribed to the specific type of request. Interceptors may
 * be configured to add processing to requests regardless of their type
 *
 */
public class RequestDispatcherImpl implements RequestDispatcher {

    private final RequestHandlerProvider requestHandlerProvider;

    /**
     * 
     * @param requestHandlerProvider
     * @param validator
     */
    public RequestDispatcherImpl(RequestHandlerProvider requestHandlerProvider) {
        this.requestHandlerProvider = requestHandlerProvider;
    }

    /**
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T send(Request request) {

        RequestHandler<Request, ?> handler = requestHandlerProvider.getRequestHandler(request);
        T response = (T) handler.handle(request);

        return response;
    }

}

package mediator;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class RequestDispatcherImplTest extends BaseTest {

	@Mock
	private RequestHandlerProviderImpl requestHandlerProvider;
	private RequestDispatcherImpl dispatcher;
	
	@Before
	public void setUp() {
		dispatcher = new RequestDispatcherImpl(requestHandlerProvider);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldExecutePrePostHandlersInCorrectOrder() {

		RequestHandler<Request, ?> handler = mock(RequestHandler.class);
		
		Mockito.<RequestHandler<Request, ?>>when(requestHandlerProvider.getRequestHandler(any(Request.class))).thenReturn(handler);
		
		dispatcher.send(any(Request.class));
		
		verify(handler, times(1)).handle(any(Request.class));
	}

}

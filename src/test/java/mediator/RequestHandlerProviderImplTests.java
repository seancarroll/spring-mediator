package mediator;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.StaticApplicationContext;

public class RequestHandlerProviderImplTests extends BaseTest {

	private StaticApplicationContext applicationContext;	
	private RequestHandlerProviderImpl provider;
	
	@Before
	public void setUp() {
		applicationContext = new StaticApplicationContext();
		provider = new RequestHandlerProviderImpl(applicationContext.getBeanFactory());		
	}

	@Test(expected = NoHandlerForRequestException.class)
	public void getHandlerShouldThrowIfHandlerNotFound() {
		provider.getRequestHandler(new NoHandlerRequest());
	}
	
	@Test
	public void shouldReturnHandlerWhenFound() {
		applicationContext.registerSingleton(FakeRequestHandler.class.getName(), FakeRequestHandler.class);
		ContextRefreshedEvent contextRefreshEvent = new ContextRefreshedEvent(applicationContext);
		provider.onApplicationEvent(contextRefreshEvent);
		
		RequestHandler<Request, Object> handler = provider.getRequestHandler(new FakeRequest());
		
		assertNotNull(handler);
		assertTrue(FakeRequestHandler.class.isAssignableFrom(handler.getClass()));
	}
	
	@Test
	public void onApplicationEventShouldAddAllIRequestHandlers() {
		applicationContext.registerSingleton(FakeRequestHandler.class.getName(), FakeRequestHandler.class);
		ContextRefreshedEvent contextRefreshEvent = new ContextRefreshedEvent(applicationContext);
		provider.onApplicationEvent(contextRefreshEvent);
		
		Map<Class<?>, RequestHandler<Request, Object>> handlers = provider.getHandlers();
		
		assertEquals(1, handlers.size());
	}

	private static class NoHandlerRequest implements Request {
		
	}
	
	private static class FakeRequest implements Request {		
	}
		
	private static class FakeRequestHandler implements RequestHandler<FakeRequest, Void> {

		@Override
		public Void handle(FakeRequest request) {
			return null;
		}
		
	}
}

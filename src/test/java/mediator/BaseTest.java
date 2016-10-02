package mediator;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTest {

	//@Mock
	//private Clock clock;

	//protected DateTime now;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);

		//App.initialize(applicationSettings, clock);

		//now = DateTime.now();
		//when(App.clock().now()).thenReturn(now);
	}

}

package mediator;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReflectionUtilsTests {

	@Test
	public void shouldReturnGenericClass() {
		Class<?> clazz = ReflectionUtils.getTypeArgumentForGenericInterface(DummyClass.class, DummyGenericInterface.class);
		assertEquals(String.class, clazz);
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowIfSpecifiedInterfaceNotOnClass() {
		ReflectionUtils.getTypeArgumentForGenericInterface(DummyClass.class, String.class);		
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldThrowIfNotGenericInterface() {
		ReflectionUtils.getTypeArgumentForGenericInterface(DummyClass.class, DummyInterface.class);
	}
	
	private static interface DummyInterface {
		
	}
	
	private static interface DummyGenericInterface<T> {
		
	}
	
	private static class DummyClass implements DummyInterface, DummyGenericInterface<String> {
		
	}
}
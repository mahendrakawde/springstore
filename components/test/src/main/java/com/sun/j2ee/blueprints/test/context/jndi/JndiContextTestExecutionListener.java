package com.sun.j2ee.blueprints.test.context.jndi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.util.Assert;

import com.sun.j2ee.blueprints.test.annotation.JndiConfig;

public class JndiContextTestExecutionListener extends AbstractTestExecutionListener {

	private Map<Object, JndiContext> jndiContextCache = new HashMap<Object, JndiContext>();
	
	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		Object instance = testContext.getTestInstance();
		Class instanceClass = testContext.getTestClass();
		Assert.notNull(instanceClass, "Test Instance Class cannot be null");
		List<Method> methods = getAnnotatedMethods(instanceClass, JndiConfig.class);
		if (methods.size() > 2) {
			throw new IllegalStateException(methods.size() + " methods found with @JndiConfig, just one is allowed.");
		}
		
		if (!methods.isEmpty()) {
			Method m = methods.get(0);
			
			Class[] types = m.getParameterTypes();
			if ( types.length != 1 || (types.length == 1 && !SimpleNamingContextBuilder.class.isAssignableFrom(types[0]))) {
				throw new IllegalStateException("Method annotated with @JndiConfig must take 1 parameter of type SimpleNamingContextBuilder.");
			}

			JndiContext jndiContext = new JndiContext(m);
			jndiContextCache.put(instance, jndiContext);
			if (!jndiContext.isDynamic()) {
				startJndiContext(testContext);
			}
		}
	}
	
	void startJndiContext(TestContext testContext) throws Exception {
		Object instance = testContext.getTestInstance();
		JndiContext jndiContext = jndiContextCache.get(instance);
		if (jndiContext != null && !jndiContext.isStarted()) {
			Method jndiConfigMethod = jndiContext.getJndiConfigMethod();
			SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
			jndiConfigMethod.invoke(instance, builder);			
			jndiContext.started();
		}
	}
	
	void stopJndiContext(TestContext testContext) {
		Object instance = testContext.getTestInstance();
		JndiContext jndiContext = jndiContextCache.get(instance);
		if (jndiContext != null && jndiContext.isStarted()) {
			SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.getCurrentContextBuilder();
			if (builder != null) {
				builder.clear();
				builder.deactivate();
			}			
			jndiContext.stopped();
		}
		
	}
	
	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {		
		startJndiContext(testContext);
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		Object instance = testContext.getTestInstance();
		JndiContext jndiContext = jndiContextCache.get(instance);
		if (jndiContext.isDynamic()) {
			stopJndiContext(testContext);
		}
	}
	
	
	/**
	 * Gets all methods in the supplied {@link Class class} and its superclasses
	 * which are annotated with the supplied <code>annotationType</code> but
	 * which are not <em>shadowed</em> by methods overridden in subclasses.
	 * <p>Note: This code has been borrowed from
	 * {@link org.junit.internal.runners.TestClass#getAnnotatedMethods(Class)}
	 * and adapted.
	 * @param clazz the class for which to retrieve the annotated methods
	 * @param annotationType the annotation type for which to search
	 * @return all annotated methods in the supplied class and its superclasses
	 */
	private List<Method> getAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotationType) {
		List<Method> results = new ArrayList<Method>();
		for (Class<?> eachClass : getSuperClasses(clazz)) {
			Method[] methods = eachClass.getDeclaredMethods();
			for (Method eachMethod : methods) {
				Annotation annotation = eachMethod.getAnnotation(annotationType);
				if (annotation != null && !isShadowed(eachMethod, results)) {
					results.add(eachMethod);
				}
			}
		}
		return results;
	}

	/**
	 * Gets all superclasses of the supplied {@link Class class}, including the
	 * class itself. The ordering of the returned list will begin with the
	 * supplied class and continue up the class hierarchy.
	 * <p>Note: This code has been borrowed from
	 * {@link org.junit.internal.runners.TestClass#getSuperClasses(Class)} and
	 * adapted.
	 * @param clazz the class for which to retrieve the superclasses.
	 * @return all superclasses of the supplied class.
	 */
	private List<Class<?>> getSuperClasses(Class<?> clazz) {
		ArrayList<Class<?>> results = new ArrayList<Class<?>>();
		Class<?> current = clazz;
		while (current != null) {
			results.add(current);
			current = current.getSuperclass();
		}
		return results;
	}

	/**
	 * Determines if the supplied {@link Method method} is <em>shadowed</em>
	 * by a method in supplied {@link List list} of previous methods.
	 * <p>Note: This code has been borrowed from
	 * {@link org.junit.internal.runners.TestClass#isShadowed(Method,List)}.
	 * @param method the method to check for shadowing
	 * @param previousMethods the list of methods which have previously been processed
	 * @return <code>true</code> if the supplied method is shadowed by a
	 * method in the <code>previousMethods</code> list
	 */
	private boolean isShadowed(Method method, List<Method> previousMethods) {
		for (Method each : previousMethods) {
			if (isShadowed(method, each)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines if the supplied {@link Method current method} is
	 * <em>shadowed</em> by a {@link Method previous method}.
	 * <p>Note: This code has been borrowed from
	 * {@link org.junit.internal.runners.TestClass#isShadowed(Method,Method)}.
	 * @param current the current method
	 * @param previous the previous method
	 * @return <code>true</code> if the previous method shadows the current one
	 */
	private boolean isShadowed(Method current, Method previous) {
		if (!previous.getName().equals(current.getName())) {
			return false;
		}
		if (previous.getParameterTypes().length != current.getParameterTypes().length) {
			return false;
		}
		for (int i = 0; i < previous.getParameterTypes().length; i++) {
			if (!previous.getParameterTypes()[i].equals(current.getParameterTypes()[i])) {
				return false;
			}
		}
		return true;
	}


	private static class JndiContext {
		
		private final Method jndiMethod;
		private boolean started = false;
		
		public JndiContext(Method jndiMethod) {
			super();
			this.jndiMethod=jndiMethod;
		}
		
		public Method getJndiConfigMethod() {
			return this.jndiMethod;
		}
		
		public boolean isDynamic() {
			JndiConfig config = jndiMethod.getAnnotation(JndiConfig.class);
			return config.dynamic();
		}
		
		public boolean isStarted() {
			return started;
		}
		
		public void started() {
			started = true;
		}
		
		public void stopped() {
			started = false;
		}
	}
	
}

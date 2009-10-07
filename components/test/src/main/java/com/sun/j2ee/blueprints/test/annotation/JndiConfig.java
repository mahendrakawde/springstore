package com.sun.j2ee.blueprints.test.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Test annotation which indicates that a method can be used to setup the JndiContext
 * for testing. 
 * 
 *  If <code>dynamic</code> is <b>true</b> then the JndiContext is reinitialized
 *  before each test method.
 * 
 * @author Marten Deinum
 *
 */
@Target( { ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JndiConfig {

	public boolean dynamic() default false;
}

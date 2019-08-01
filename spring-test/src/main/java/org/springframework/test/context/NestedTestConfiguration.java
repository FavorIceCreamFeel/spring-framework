/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.test.context;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @NestedTestConfiguration} is a type-level annotation that is used to
 * configure how Spring test configuration annotations are processed within
 * enclosing class hierarchies (i.e., for <em>inner</em> test classes).
 *
 * <p>If {@code @NestedTestConfiguration} is not <em>present</em> or
 * <em>meta-present</em> on a test class, configuration from the test class will
 * not propagate to inner test classes (see {@link EnclosingConfiguration#OVERRIDE}).
 * Consequently, inner test classes will have to declare their own Spring test
 * configuration annotations. If you wish for an inner test class to inherit
 * configuration from its enclosing class, annotate the enclosing class with
 * {@code @NestedTestConfiguration(EnclosingConfiguration.INHERIT)}.
 *
 * <p>This annotation may be used as a <em>meta-annotation</em> to create custom
 * <em>composed annotations</em>.
 *
 * <p>As of Spring Framework 5.3, the use of this annotation typically only makes
 * sense in conjunction with {@link org.junit.jupiter.api.Nested @Nested} test
 * classes in JUnit Jupiter.
 *
 * @author Sam Brannen
 * @since 5.3
 * @see EnclosingConfiguration#INHERIT
 * @see EnclosingConfiguration#OVERRIDE
 * @see ContextConfiguration @ContextConfiguration
 * @see ContextHierarchy @ContextHierarchy
 * @see ActiveProfiles @ActiveProfiles
 * @see TestPropertySource @TestPropertySource
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NestedTestConfiguration {

	/**
	 * Configures the {@link EnclosingConfiguration} mode.
	 */
	EnclosingConfiguration value();


	/**
	 * Enumeration of <em>modes</em> that dictate how test configuration from
	 * enclosing classes is processed for inner test classes.
	 */
	enum EnclosingConfiguration {

		/**
		 * Indicates that test configuration for an inner test class should be
		 * <em>inherited</em> from its {@linkplain Class#getEnclosingClass()
		 * enclosing class}, as if the enclosing class were a superclass.
		 */
		INHERIT,

		/**
		 * Indicates that test configuration for an inner test class should
		 * <em>override</em> configuration from its
		 * {@linkplain Class#getEnclosingClass() enclosing class}.
		 */
		OVERRIDE

	}

}

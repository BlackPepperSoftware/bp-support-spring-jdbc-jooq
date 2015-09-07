/*
 * Copyright 2014 Black Pepper Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.blackpepper.support.spring.jdbc.jooq;

import java.util.List;

import org.hamcrest.Matchers;
import org.jooq.Field;
import org.junit.Test;

import static java.util.Arrays.asList;

import static org.jooq.impl.DSL.fieldByName;
import static org.junit.Assert.assertThat;

public class FieldsTest {
	
	@Test
	public void asWithFieldReturnsAliasedField() {
		List<? extends Field<?>> fields = asList(fieldByName(Long.class, "x"));
		
		List<Field<?>> actual = Fields.as(fields, "y");
		
		assertThat(actual, Matchers.<Field<?>>contains(fieldByName(Long.class, "y_x")));
	}
	
	@Test
	public void asWithFieldsReturnsAliasedFields() {
		List<? extends Field<?>> fields = asList(fieldByName(Long.class, "x"), fieldByName(Long.class, "y"));
		
		List<Field<?>> actual = Fields.as(fields, "z");
		
		assertThat(actual, Matchers.<Field<?>>contains(
			fieldByName(Long.class, "x").as("z_x"),
			fieldByName(Long.class, "y").as("z_y")
		));
	}
}

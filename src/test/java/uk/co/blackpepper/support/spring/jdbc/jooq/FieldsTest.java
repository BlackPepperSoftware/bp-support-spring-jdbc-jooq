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

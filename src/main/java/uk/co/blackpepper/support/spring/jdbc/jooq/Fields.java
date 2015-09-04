package uk.co.blackpepper.support.spring.jdbc.jooq;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Field;

public final class Fields {
	
	private Fields() {
		throw new AssertionError();
	}

	public static List<Field<?>> as(List<? extends Field<?>> fields, String aliasPrefix) {
		List<Field<?>> aliasedFields = new ArrayList<>();
		
		for (Field<?> field : fields) {
			aliasedFields.add(field.as(aliasPrefix + "_" + field.getName()));
		}
		
		return aliasedFields;
	}
}

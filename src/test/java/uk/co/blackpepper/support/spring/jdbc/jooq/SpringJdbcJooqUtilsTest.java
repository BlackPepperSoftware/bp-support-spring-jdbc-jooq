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

import java.sql.Types;
import java.util.Collections;
import java.util.List;

import org.jooq.Param;
import org.jooq.Query;
import org.junit.Test;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SqlProvider;

import com.google.common.collect.Maps;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import static org.hamcrest.CoreMatchers.is;
import static org.jooq.impl.DSL.param;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpringJdbcJooqUtilsTest {
	
	@Test
	public void newPreparedStatementCreatorReturnsPreparedStatementCreator() {
		Query query = mock(Query.class);
		when(query.getParams()).thenReturn(Maps.<String, Param<?>>newHashMap());
		when(query.getBindValues()).thenReturn(Collections.emptyList());
		when(query.getSQL()).thenReturn("x");
		
		PreparedStatementCreator actual = SpringJdbcJooqUtils.newPreparedStatementCreator(query);
		
		// The concrete class implements this interface, so we can make a meaningful assertion
		String sql = ((SqlProvider) actual).getSql();
		assertThat(sql, is("x"));
	}
	
	@Test
	public void getSqlTypesWithParamReturnsSqlType() {
		List<? extends Param<?>> fields = singletonList(param("x", String.class));
		
		int[] actual = SpringJdbcJooqUtils.getSqlTypes(fields);
		
		assertThat(actual, is(new int[] {Types.VARCHAR}));
	}
	
	@Test
	public void getSqlTypesWithParamsReturnsSqlTypes() {
		List<? extends Param<?>> fields = asList(param("x", String.class), param("y", Long.class));
		
		int[] actual = SpringJdbcJooqUtils.getSqlTypes(fields);
		
		assertThat(actual, is(new int[] {Types.VARCHAR, Types.BIGINT}));
	}
}

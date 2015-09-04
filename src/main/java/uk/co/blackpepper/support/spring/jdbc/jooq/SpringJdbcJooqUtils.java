package uk.co.blackpepper.support.spring.jdbc.jooq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jooq.Param;
import org.jooq.Query;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.primitives.Ints;

import uk.co.blackpepper.support.spring.jdbc.JdbcUtils;

import static org.springframework.jdbc.core.StatementCreatorUtils.javaTypeToSqlParameterType;

public final class SpringJdbcJooqUtils {
	
	private SpringJdbcJooqUtils() {
		throw new AssertionError();
	}
	
	public static PreparedStatementCreator newPreparedStatementCreator(Query query) {
		int[] types = getSqlTypes(query.getParams().values());
		Object[] params = query.getBindValues().toArray();
		
		return JdbcUtils.newPreparedStatementCreator(query.getSQL(), types, params);
	}
	
	@VisibleForTesting
	static int[] getSqlTypes(Collection<? extends Param<?>> params) {
		List<Integer> types = new ArrayList<>(params.size());
		
		for (Param<?> param : params) {
			types.add(javaTypeToSqlParameterType(param.getType()));
		}
		
		return Ints.toArray(types);
	}
}

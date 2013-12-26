package com.base.db;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by kai.wang on 11/20/13.
 */

public class QueryHelper {

	private static final Log logger = LogFactory.getLog(QueryHelper.class);


	private final static ScalarHandler scaleHandler = new ScalarHandler() {
		@Override
		public Object handle(ResultSet rs) throws SQLException {
			Object obj = super.handle(rs);
			if (obj instanceof BigInteger) return ((BigInteger) obj).longValue();
			return obj;
		}
	};


	/**
	 * 查询（返回 Array）
	 *
	 * @param runner
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Object[] queryArray(QueryRunner runner, String sql, Object... params) {
		Object[] result = null;
		try {
			result = runner.query(sql, new ArrayHandler(), params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		printSQL(sql);
		return result;
	}

	/**
	 * 查询（返回 ArrayList）
	 *
	 * @param runner
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Object[]> queryArrayList(QueryRunner runner, String sql, Object... params) {
		List<Object[]> result = null;
		try {
			result = runner.query(sql, new ArrayListHandler(), params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		printSQL(sql);
		return result;
	}

	/**
	 * 查询（返回 Map）
	 *
	 * @param runner
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Map<String, Object> queryMap(QueryRunner runner, String sql, Object... params) {
		Map<String, Object> result = null;
		try {
			result = runner.query(sql, new MapHandler(), params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		printSQL(sql);
		return result;
	}

	/**
	 * 查询（返回 MapList）
	 *
	 * @param runner
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> queryMapList(QueryRunner runner, String sql, Object... params) {
		List<Map<String, Object>> result = null;
		try {
			result = runner.query(sql, new MapListHandler(), params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		printSQL(sql);
		return result;
	}

	/**
	 * 查询（返回 Bean）
	 *
	 * @param runner
	 * @param cls
	 * @param map
	 * @param sql
	 * @param params
	 * @param <T>
	 * @return
	 */
	public static <T> T queryBean(QueryRunner runner, Class<T> cls, Map<String, String> map, String sql, Object... params) {
		T result = null;
		try {
			if (MapUtils.isNotEmpty(map)) {
				result = runner.query(sql, new BeanHandler<T>(cls, new BasicRowProcessor(new BeanProcessor(map))), params);
			} else {
				result = runner.query(sql, new BeanHandler<T>(cls), params);
			}
			printSQL(sql);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 查询（返回 BeanList）
	 *
	 * @param runner
	 * @param cls
	 * @param sql
	 * @param params
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> queryBeanList(QueryRunner runner, Class<T> cls, String sql, Object... params) {
		List<T> result = null;
		try {
			result = runner.query(sql, new BeanListHandler<T>(cls), params);
			printSQL(sql);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 查询指定列名的值（单条数据）
	 *
	 * @param runner
	 * @param column
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Object queryColumn(QueryRunner runner, String column, String sql, Object... params) {
		Object result = null;
		try {
			result = runner.query(sql, new ScalarHandler<Object>(column), params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		printSQL(sql);
		return result;
	}

	/**
	 * 查询指定列名的值（多条数据）
	 *
	 * @param runner
	 * @param column
	 * @param sql
	 * @param params
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> queryColumnList(QueryRunner runner, String column, String sql, Object... params) {
		List<T> result = null;
		try {
			result = runner.query(sql, new ColumnListHandler<T>(column), params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		printSQL(sql);
		return result;
	}

	/**
	 * 查询指定列名对应的记录映射
	 *
	 * @param runner
	 * @param column
	 * @param sql
	 * @param params
	 * @param <T>
	 * @return
	 */
	public static <T> Map<T, Map<String, Object>> queryKeyMap(QueryRunner runner, String column, String sql, Object... params) {
		Map<T, Map<String, Object>> result = null;
		try {
			result = runner.query(sql, new KeyedHandler<T>(column), params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		printSQL(sql);
		return result;
	}

	/**
	 * 更新（包括 UPDATE、INSERT、DELETE，返回受影响的行数）
	 *
	 * @param runner
	 * @param conn   可以为空
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int update(QueryRunner runner, Connection conn, String sql, Object... params) {
		int result = 0;
		try {
			if (conn != null) {
				result = runner.update(conn, sql, params);
			} else {
				result = runner.update(sql, params);
			}
			printSQL(sql);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		return result;
	}


	private static void printSQL(String sql) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("SQL: " + sql);
//		}
		logger.debug("SQL: " + sql);
	}

	/**
	 * 查询count
	 * @param runner
	 * @param sql
	 * @param params
	 * @return
	 */
	public static long queryCount(QueryRunner runner, String sql, Object... params) {
		long count = 0;
		try {
			Number num = ((Number) runner.query(sql, scaleHandler, params)).intValue();
			count = num == null ? -1 : num.longValue();
			printSQL(sql);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		return count;
	}

	/**
	 * 查询数字类型
	 * @param runner
	 * @param sql
	 * @param params
	 * @return
	 */
	public static long queryNumber(QueryRunner runner, Connection conn,String sql, Object... params) {
		long result = 0;
		try {
			Number num = null;
			if (conn != null) {
				num = ((Number) runner.query(conn,sql,scaleHandler, params)).intValue();
			} else {
				num = ((Number) runner.query(sql, scaleHandler, params)).intValue();
			}
			result = num == null ? -1 : num.longValue();
			printSQL(sql);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		return result;
	}

}
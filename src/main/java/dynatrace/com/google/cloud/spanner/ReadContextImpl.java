package dynatrace.com.google.cloud.spanner;

import com.dynatrace.oneagent.sdk.api.DatabaseRequestTracer;
import com.dynatrace.oneagent.sdk.api.infos.DatabaseInfo;
import com.google.api.core.ApiFuture;
import com.google.cloud.spanner.AsyncResultSet;
import com.google.cloud.spanner.Key;
import com.google.cloud.spanner.KeySet;
import com.google.cloud.spanner.Options.QueryOption;
import com.google.cloud.spanner.Options.ReadOption;
import com.google.cloud.spanner.ReadContext;
import com.google.cloud.spanner.ResultSet;
import com.google.cloud.spanner.Statement;
import com.google.cloud.spanner.Struct;

class ReadContextImpl implements ReadContext {
	
	private final ReadContext context;
	final DatabaseInfo databaseInfo;
	
	ReadContextImpl(ReadContext context, DatabaseInfo databaseInfo) {
		this.context = context;
		this.databaseInfo = databaseInfo;
	}

	@Override
	public ResultSet executeQuery(Statement statement, QueryOption... options) {
		DatabaseRequestTracer tracer = SpannerImpl.oneAgentSDK.traceSqlDatabaseRequest(databaseInfo, statement.toString());
		tracer.start();
		try {
			return context.executeQuery(statement, options);			
		} catch (RuntimeException | Error e) {
			tracer.error(e);
			throw e;
		} finally {
			tracer.end();
		}
	}

	@Override
	public AsyncResultSet executeQueryAsync(Statement statement, QueryOption... options) {
		DatabaseRequestTracer tracer = SpannerImpl.oneAgentSDK.traceSqlDatabaseRequest(databaseInfo, statement.toString());
		tracer.start();
		try {
			return context.executeQueryAsync(statement, options);
		} catch (RuntimeException | Error e) {
			tracer.error(e);
			throw e;
		} finally {
			tracer.end();
		}
	}

	@Override
	public ResultSet read(String table, KeySet keys, Iterable<String> columns, ReadOption... options) {
		return context.read(table, keys, columns, options);
	}

	@Override
	public AsyncResultSet readAsync(String table, KeySet keys, Iterable<String> columns, ReadOption... options) {
		return context.readAsync(table, keys, columns, options);
	}

	@Override
	public ResultSet readUsingIndex(String table, String index, KeySet keys, Iterable<String> columns, ReadOption... options) {
		return context.readUsingIndex(table, index, keys, columns, options);
	}

	@Override
	public AsyncResultSet readUsingIndexAsync(String table, String index, KeySet keys, Iterable<String> columns, ReadOption... options) {
		return context.readUsingIndexAsync(table, index, keys, columns, options);
	}

	@Override
	public Struct readRow(String table, Key key, Iterable<String> columns) {
		return context.readRow(table, key, columns);
	}

	@Override
	public ApiFuture<Struct> readRowAsync(String table, Key key, Iterable<String> columns) {
		return context.readRowAsync(table, key, columns);
	}

	@Override
	public Struct readRowUsingIndex(String table, String index, Key key, Iterable<String> columns) {
		return context.readRowUsingIndex(table, index, key, columns);
	}

	@Override
	public ApiFuture<Struct> readRowUsingIndexAsync(String table, String index, Key key, Iterable<String> columns) {
		return context.readRowUsingIndexAsync(table, index, key, columns);
	}

	@Override
	public ResultSet analyzeQuery(Statement statement, QueryAnalyzeMode queryMode) {
		return context.analyzeQuery(statement, queryMode);
	}

	@Override
	public void close() {
		context.close();
	}

}

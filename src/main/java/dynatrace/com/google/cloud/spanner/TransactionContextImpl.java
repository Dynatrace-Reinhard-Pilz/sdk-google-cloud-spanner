package dynatrace.com.google.cloud.spanner;

import com.dynatrace.oneagent.sdk.api.DatabaseRequestTracer;
import com.dynatrace.oneagent.sdk.api.infos.DatabaseInfo;
import com.google.api.core.ApiFuture;
import com.google.cloud.spanner.Mutation;
import com.google.cloud.spanner.Options.UpdateOption;
import com.google.cloud.spanner.Statement;
import com.google.cloud.spanner.TransactionContext;

class TransactionContextImpl extends ReadContextImpl implements TransactionContext {
	
	private final TransactionContext context;
	private final DatabaseInfo databaseInfo;
	
	TransactionContextImpl(TransactionContext context, DatabaseInfo databaseInfo) {
		super(context, databaseInfo);
		this.context = context;
		this.databaseInfo = databaseInfo;
	}

	@Override
	public long executeUpdate(Statement statement, UpdateOption... options) {
		DatabaseRequestTracer tracer = SpannerImpl.oneAgentSDK.traceSqlDatabaseRequest(databaseInfo, statement.toString());
		tracer.start();
		try {
			return context.executeUpdate(statement, options);
		} catch (RuntimeException | Error e) {
			tracer.error(e);
			throw e;
		} finally {
			tracer.end();
		}
	}

	@Override
	public ApiFuture<Long> executeUpdateAsync(Statement statement, UpdateOption... options) {
		DatabaseRequestTracer tracer = SpannerImpl.oneAgentSDK.traceSqlDatabaseRequest(databaseInfo, statement.toString());
		tracer.start();
		try {
			return context.executeUpdateAsync(statement, options);
		} catch (RuntimeException | Error e) {
			tracer.error(e);
			throw e;
		} finally {
			tracer.end();
		}
	}

	@Override
	public long[] batchUpdate(Iterable<Statement> statements, UpdateOption... options) {
		for (Statement statement : statements) {
			DatabaseRequestTracer tracer = SpannerImpl.oneAgentSDK.traceSqlDatabaseRequest(databaseInfo, statement.toString());
			tracer.start();
			tracer.end();
		}
		return context.batchUpdate(statements, options);
	}

	@Override
	public ApiFuture<long[]> batchUpdateAsync(Iterable<Statement> statements, UpdateOption... options) {
		for (Statement statement : statements) {
			DatabaseRequestTracer tracer = SpannerImpl.oneAgentSDK.traceSqlDatabaseRequest(databaseInfo, statement.toString());
			tracer.start();
			tracer.end();
		}
		return context.batchUpdateAsync(statements, options);
	}

	@Override
	public void buffer(Mutation mutation) {
		context.buffer(mutation);
	}

	@Override
	public void buffer(Iterable<Mutation> mutations) {
		context.buffer(mutations);
	}

}

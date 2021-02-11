package dynatrace.com.google.cloud.spanner;

import com.dynatrace.oneagent.sdk.api.infos.DatabaseInfo;
import com.google.cloud.Timestamp;
import com.google.cloud.spanner.AsyncRunner;
import com.google.cloud.spanner.AsyncTransactionManager;
import com.google.cloud.spanner.CommitResponse;
import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.Mutation;
import com.google.cloud.spanner.Options.TransactionOption;
import com.google.cloud.spanner.Options.UpdateOption;
import com.google.cloud.spanner.ReadContext;
import com.google.cloud.spanner.ReadOnlyTransaction;
import com.google.cloud.spanner.SpannerException;
import com.google.cloud.spanner.Statement;
import com.google.cloud.spanner.TimestampBound;
import com.google.cloud.spanner.TransactionManager;
import com.google.cloud.spanner.TransactionRunner;

final class DatabaseClientImpl implements DatabaseClient {

	private final DatabaseClient client;
	private final DatabaseInfo databaseInfo;
	
	public DatabaseClientImpl(DatabaseClient client, DatabaseInfo databaseInfo) {
		this.client = client;
		this.databaseInfo = databaseInfo;
	}

	@Override
	public ReadContext singleUse() {
		return new ReadContextImpl(client.singleUse(), databaseInfo);
	}

	@Override
	public ReadContext singleUse(TimestampBound bound) {
		return new ReadContextImpl(client.singleUse(bound), databaseInfo);
	}

	@Override
	public ReadOnlyTransaction singleUseReadOnlyTransaction() {
		return new ReadOnlyTransactionImpl(client.singleUseReadOnlyTransaction(), databaseInfo);
	}

	@Override
	public ReadOnlyTransaction singleUseReadOnlyTransaction(TimestampBound bound) {
		return new ReadOnlyTransactionImpl(client.singleUseReadOnlyTransaction(bound), databaseInfo);
	}

	@Override
	public ReadOnlyTransaction readOnlyTransaction() {
		return new ReadOnlyTransactionImpl(client.readOnlyTransaction(), databaseInfo);
	}

	@Override
	public ReadOnlyTransaction readOnlyTransaction(TimestampBound bound) {
		return new ReadOnlyTransactionImpl(client.readOnlyTransaction(bound), databaseInfo);
	}

	@Override
	public TransactionRunner readWriteTransaction(TransactionOption... options) {
		return new TransactionRunnerImpl(client.readWriteTransaction(options), databaseInfo);
	}
	
	@Override
	public Timestamp write(Iterable<Mutation> mutations) throws SpannerException {
		return client.write(mutations);
	}

	@Override
	public CommitResponse writeWithOptions(Iterable<Mutation> mutations, TransactionOption... options) throws SpannerException {
		return client.writeWithOptions(mutations, options);
	}

	@Override
	public Timestamp writeAtLeastOnce(Iterable<Mutation> mutations) throws SpannerException {
		return client.writeAtLeastOnce(mutations);
	}

	@Override
	public CommitResponse writeAtLeastOnceWithOptions(Iterable<Mutation> mutations, TransactionOption... options) throws SpannerException {
		return client.writeAtLeastOnceWithOptions(mutations, options);
	}


	@Override
	public TransactionManager transactionManager(TransactionOption... options) {
		return client.transactionManager(options);
	}

	@Override
	public AsyncRunner runAsync(TransactionOption... options) {
		return client.runAsync(options);
	}

	@Override
	public AsyncTransactionManager transactionManagerAsync(TransactionOption... options) {
		return client.transactionManagerAsync(options);
	}

	@Override
	public long executePartitionedUpdate(Statement stmt, UpdateOption... options) {
		return client.executePartitionedUpdate(stmt, options);
	}

}

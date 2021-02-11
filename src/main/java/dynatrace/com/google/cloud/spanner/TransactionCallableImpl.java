package dynatrace.com.google.cloud.spanner;

import com.dynatrace.oneagent.sdk.api.infos.DatabaseInfo;
import com.google.cloud.spanner.TransactionContext;
import com.google.cloud.spanner.TransactionRunner.TransactionCallable;

class TransactionCallableImpl<T> implements TransactionCallable<T> {
	
	private final TransactionCallable<T> callable;
	private final DatabaseInfo databaseInfo;
	
	TransactionCallableImpl(TransactionCallable<T> callable, DatabaseInfo databaseInfo) {
		this.callable = callable;
		this.databaseInfo = databaseInfo;
	}

	@Override
	public T run(TransactionContext transaction) throws Exception {
		return callable.run(new TransactionContextImpl(transaction, databaseInfo));
	}

}

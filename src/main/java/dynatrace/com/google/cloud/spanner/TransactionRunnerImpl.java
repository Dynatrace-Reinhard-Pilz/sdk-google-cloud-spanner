package dynatrace.com.google.cloud.spanner;

import com.dynatrace.oneagent.sdk.api.infos.DatabaseInfo;
import com.google.cloud.Timestamp;
import com.google.cloud.spanner.TransactionRunner;

class TransactionRunnerImpl implements TransactionRunner {
	
	private final TransactionRunner runner;
	private final DatabaseInfo databaseInfo;
	
	TransactionRunnerImpl(TransactionRunner runner, DatabaseInfo databaseInfo) {
		this.runner = runner;
		this.databaseInfo = databaseInfo;
	}

	@Override
	public <T> T run(TransactionCallable<T> callable) {
		return runner.run(new TransactionCallableImpl<T>(callable, databaseInfo));
	}

	@Override
	public Timestamp getCommitTimestamp() {
		return runner.getCommitTimestamp();
	}

	@Override
	public TransactionRunner allowNestedTransaction() {
		return new TransactionRunnerImpl(runner.allowNestedTransaction(), databaseInfo);
	}

}

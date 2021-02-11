package dynatrace.com.google.cloud.spanner;

import com.dynatrace.oneagent.sdk.api.infos.DatabaseInfo;
import com.google.cloud.Timestamp;
import com.google.cloud.spanner.ReadOnlyTransaction;

class ReadOnlyTransactionImpl extends ReadContextImpl  implements ReadOnlyTransaction {

	final ReadOnlyTransaction transaction;
	
	ReadOnlyTransactionImpl(ReadOnlyTransaction transaction, DatabaseInfo databaseInfo) {
		super(transaction, databaseInfo);
		this.transaction = transaction;
	}


	@Override
	public Timestamp getReadTimestamp() {
		return transaction.getReadTimestamp();
	}
}

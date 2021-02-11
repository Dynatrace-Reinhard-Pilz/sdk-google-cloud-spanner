package dynatrace.com.google.cloud.spanner;

import com.dynatrace.oneagent.sdk.api.infos.DatabaseInfo;
import com.google.cloud.spanner.BatchClient;
import com.google.cloud.spanner.BatchReadOnlyTransaction;
import com.google.cloud.spanner.BatchTransactionId;
import com.google.cloud.spanner.TimestampBound;

class BatchClientImpl implements BatchClient {
	
	private final BatchClient client;
	private final DatabaseInfo databaseInfo;
	
	BatchClientImpl(BatchClient client, DatabaseInfo databaseInfo) {
		this.client = client;
		this.databaseInfo = databaseInfo;
	}

	@Override
	public BatchReadOnlyTransaction batchReadOnlyTransaction(TimestampBound bound) {
		return new BatchReadOnlyTransactionImpl(client.batchReadOnlyTransaction(bound), databaseInfo);
	}

	@Override
	public BatchReadOnlyTransaction batchReadOnlyTransaction(BatchTransactionId batchTransactionId) {
		return new BatchReadOnlyTransactionImpl(client.batchReadOnlyTransaction(batchTransactionId), databaseInfo);
	}

}

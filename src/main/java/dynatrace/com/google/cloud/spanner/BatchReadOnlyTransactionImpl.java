package dynatrace.com.google.cloud.spanner;

import java.util.List;

import com.dynatrace.oneagent.sdk.api.infos.DatabaseInfo;
import com.google.cloud.spanner.BatchReadOnlyTransaction;
import com.google.cloud.spanner.BatchTransactionId;
import com.google.cloud.spanner.KeySet;
import com.google.cloud.spanner.Options.QueryOption;
import com.google.cloud.spanner.Options.ReadOption;
import com.google.cloud.spanner.Partition;
import com.google.cloud.spanner.PartitionOptions;
import com.google.cloud.spanner.ResultSet;
import com.google.cloud.spanner.SpannerException;
import com.google.cloud.spanner.Statement;

class BatchReadOnlyTransactionImpl extends ReadOnlyTransactionImpl implements BatchReadOnlyTransaction {
	
	BatchReadOnlyTransactionImpl(BatchReadOnlyTransaction transaction, DatabaseInfo databaseInfo) {
		super(transaction, databaseInfo);
	}

	@Override
	public List<Partition> partitionRead(PartitionOptions partitionOptions, String table, KeySet keys, Iterable<String> columns, ReadOption... options) throws SpannerException {
		return ((BatchReadOnlyTransaction) transaction).partitionRead(partitionOptions, table, keys, columns, options);
	}

	@Override
	public List<Partition> partitionReadUsingIndex(PartitionOptions partitionOptions, String table, String index, KeySet keys, Iterable<String> columns, ReadOption... options) throws SpannerException {
		return ((BatchReadOnlyTransaction) transaction).partitionReadUsingIndex(partitionOptions, table, index, keys, columns, options);
	}

	@Override
	public List<Partition> partitionQuery(PartitionOptions partitionOptions, Statement statement, QueryOption... options) throws SpannerException {
		return ((BatchReadOnlyTransaction) transaction).partitionQuery(partitionOptions, statement, options);
	}

	@Override
	public ResultSet execute(Partition partition) throws SpannerException {
		return ((BatchReadOnlyTransaction) transaction).execute(partition);
	}

	@Override
	public BatchTransactionId getBatchTransactionId() {
		return ((BatchReadOnlyTransaction) transaction).getBatchTransactionId();
	}

}

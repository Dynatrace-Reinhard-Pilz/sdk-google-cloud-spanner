package dynatrace.com.google.cloud.spanner;

import com.dynatrace.oneagent.sdk.OneAgentSDKFactory;
import com.dynatrace.oneagent.sdk.api.OneAgentSDK;
import com.dynatrace.oneagent.sdk.api.enums.ChannelType;
import com.dynatrace.oneagent.sdk.api.infos.DatabaseInfo;
import com.google.api.gax.core.ExecutorProvider;
import com.google.cloud.spanner.BatchClient;
import com.google.cloud.spanner.DatabaseAdminClient;
import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.InstanceAdminClient;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;

class SpannerImpl implements Spanner {
	
	private static final String DATABASE_VENDOR = "Cloud Spanner";
	
	static final OneAgentSDK oneAgentSDK = OneAgentSDKFactory.createInstance();
	
	private final Spanner spanner;
	
	SpannerImpl(Spanner spanner) {
		this.spanner = spanner;
	}
	
	private DatabaseInfo createDatabaseInfo(DatabaseId db) {
		return  SpannerImpl.oneAgentSDK.createDatabaseInfo(db.getDatabase(), DATABASE_VENDOR, ChannelType.TCP_IP, "grpc://google.spanner.v1.Spanner");
	}

	@Override
	public DatabaseClient getDatabaseClient(DatabaseId db) {
		return new DatabaseClientImpl(spanner.getDatabaseClient(db), createDatabaseInfo(db));
	}

	@Override
	public BatchClient getBatchClient(DatabaseId db) {
		return new BatchClientImpl(spanner.getBatchClient(db), createDatabaseInfo(db));
	}

	@Override
	public DatabaseAdminClient getDatabaseAdminClient() {
		return spanner.getDatabaseAdminClient();
//		return new DatabaseAdminClientImpl(spanner.getDatabaseAdminClient());
	}

	@Override
	public InstanceAdminClient getInstanceAdminClient() {
		return spanner.getInstanceAdminClient();
//		return new InstanceAdminClientImpl(spanner.getInstanceAdminClient());
	}

	@Override
	public SpannerOptions getOptions() {
		return spanner.getOptions();
	}

	@Override
	public void close() {
		spanner.close();
	}

	@Override
	public boolean isClosed() {
		return spanner.isClosed();
	}

	@Override
	public ExecutorProvider getAsyncExecutorProvider() {
		return spanner.getAsyncExecutorProvider();
	}

}

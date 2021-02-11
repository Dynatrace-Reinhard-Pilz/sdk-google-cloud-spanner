package dynatrace.com.google.cloud.spanner;

import java.util.List;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.paging.Page;
import com.google.cloud.Policy;
import com.google.cloud.Timestamp;
import com.google.cloud.spanner.Backup;
import com.google.cloud.spanner.Backup.Builder;
import com.google.cloud.spanner.BackupId;
import com.google.cloud.spanner.Database;
import com.google.cloud.spanner.DatabaseAdminClient;
import com.google.cloud.spanner.Options.ListOption;
import com.google.cloud.spanner.SpannerException;
import com.google.longrunning.Operation;
import com.google.spanner.admin.database.v1.CreateBackupMetadata;
import com.google.spanner.admin.database.v1.CreateDatabaseMetadata;
import com.google.spanner.admin.database.v1.RestoreDatabaseMetadata;
import com.google.spanner.admin.database.v1.UpdateDatabaseDdlMetadata;

final class DatabaseAdminClientImpl implements DatabaseAdminClient {
	
	private final DatabaseAdminClient client;
	
	DatabaseAdminClientImpl(DatabaseAdminClient client) {
		this.client = client;
	}

	@Override
	public OperationFuture<Database, CreateDatabaseMetadata> createDatabase(String instanceId, String databaseId, Iterable<String> statements) throws SpannerException {
		return client.createDatabase(instanceId, databaseId, statements);
	}

	@Override
	public Builder newBackupBuilder(BackupId id) {
		return client.newBackupBuilder(id);
	}

	@Override
	public OperationFuture<Backup, CreateBackupMetadata> createBackup(String sourceInstanceId, String backupId,	String databaseId, Timestamp expireTime) throws SpannerException {
		return client.createBackup(sourceInstanceId, backupId, databaseId, expireTime);
	}

	@Override
	public OperationFuture<Database, RestoreDatabaseMetadata> restoreDatabase(String backupInstanceId, String backupId,	String restoreInstanceId, String restoreDatabaseId) throws SpannerException {
		return client.restoreDatabase(backupInstanceId, backupId, restoreInstanceId, restoreDatabaseId);
	}

	@Override
	public Page<Operation> listDatabaseOperations(String instanceId, ListOption... options) {
		return client.listDatabaseOperations(instanceId, options);
	}

	@Override
	public Page<Operation> listBackupOperations(String instanceId, ListOption... options) {
		return client.listBackupOperations(instanceId, options);
	}

	@Override
	public Database getDatabase(String instanceId, String databaseId) throws SpannerException {
		return client.getDatabase(instanceId, databaseId);
	}

	@Override
	public Backup getBackup(String instanceId, String backupId) throws SpannerException {
		return client.getBackup(instanceId, backupId);
	}

	@Override
	public OperationFuture<Void, UpdateDatabaseDdlMetadata> updateDatabaseDdl(String instanceId, String databaseId,	Iterable<String> statements, String operationId) throws SpannerException {
		return client.updateDatabaseDdl(instanceId, databaseId, statements, operationId);
	}

	@Override
	public void dropDatabase(String instanceId, String databaseId) throws SpannerException {
		client.dropDatabase(instanceId, databaseId);
	}

	@Override
	public List<String> getDatabaseDdl(String instanceId, String databaseId) {
		return client.getDatabaseDdl(instanceId, databaseId);
	}

	@Override
	public Page<Database> listDatabases(String instanceId, ListOption... options) {
		return client.listDatabases(instanceId, options);
	}

	@Override
	public Page<Backup> listBackups(String instanceId, ListOption... options) {
		return client.listBackups(instanceId, options);
	}

	@Override
	public Backup updateBackup(String instanceId, String backupId, Timestamp expireTime) {
		return client.updateBackup(instanceId, backupId, expireTime);
	}

	@Override
	public void deleteBackup(String instanceId, String backupId) {
		client.deleteBackup(instanceId, backupId);
	}

	@Override
	public void cancelOperation(String name) {
		client.cancelOperation(name);
	}

	@Override
	public Operation getOperation(String name) {
		return client.getOperation(name);
	}

	@Override
	public Policy getDatabaseIAMPolicy(String instanceId, String databaseId) {
		return client.getDatabaseIAMPolicy(instanceId, databaseId);
	}

	@Override
	public Policy setDatabaseIAMPolicy(String instanceId, String databaseId, Policy policy) {
		return client.setDatabaseIAMPolicy(instanceId, databaseId, policy);
	}

	@Override
	public Iterable<String> testDatabaseIAMPermissions(String instanceId, String databaseId, Iterable<String> permissions) {
		return client.testDatabaseIAMPermissions(instanceId, databaseId, permissions);
	}

	@Override
	public Policy getBackupIAMPolicy(String instanceId, String backupId) {
		return client.getBackupIAMPolicy(instanceId, backupId);
	}

	@Override
	public Policy setBackupIAMPolicy(String instanceId, String backupId, Policy policy) {
		return client.setBackupIAMPolicy(instanceId, backupId, policy);
	}

	@Override
	public Iterable<String> testBackupIAMPermissions(String instanceId, String backupId, Iterable<String> permissions) {
		return client.testBackupIAMPermissions(instanceId, backupId, permissions);
	}

}

package dynatrace.com.google.cloud.spanner;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.paging.Page;
import com.google.cloud.Policy;
import com.google.cloud.spanner.Instance;
import com.google.cloud.spanner.Instance.Builder;
import com.google.cloud.spanner.InstanceAdminClient;
import com.google.cloud.spanner.InstanceConfig;
import com.google.cloud.spanner.InstanceId;
import com.google.cloud.spanner.InstanceInfo;
import com.google.cloud.spanner.InstanceInfo.InstanceField;
import com.google.cloud.spanner.Options.ListOption;
import com.google.cloud.spanner.SpannerException;
import com.google.longrunning.Operation;
import com.google.spanner.admin.instance.v1.CreateInstanceMetadata;
import com.google.spanner.admin.instance.v1.UpdateInstanceMetadata;

final class InstanceAdminClientImpl implements InstanceAdminClient {

	private final InstanceAdminClient client;
	
	InstanceAdminClientImpl(InstanceAdminClient client) {
		this.client = client;
	}

	@Override
	public InstanceConfig getInstanceConfig(String configId) throws SpannerException {
		return client.getInstanceConfig(configId);
	}

	@Override
	public Page<InstanceConfig> listInstanceConfigs(ListOption... options) throws SpannerException {
		return client.listInstanceConfigs(options);
	}

	@Override
	public OperationFuture<Instance, CreateInstanceMetadata> createInstance(InstanceInfo instance) throws SpannerException {
		return client.createInstance(instance);
	}

	@Override
	public Instance getInstance(String instanceId) throws SpannerException {
		return client.getInstance(instanceId);
	}

	@Override
	public Page<Instance> listInstances(ListOption... options) throws SpannerException {
		return client.listInstances(options);
	}

	@Override
	public void deleteInstance(String instanceId) throws SpannerException {
		client.deleteInstance(instanceId);
	}

	@Override
	public OperationFuture<Instance, UpdateInstanceMetadata> updateInstance(InstanceInfo instance, InstanceField... fieldsToUpdate) {
		return client.updateInstance(instance, fieldsToUpdate);
	}

	@Override
	public Policy getInstanceIAMPolicy(String instanceId) {
		return client.getInstanceIAMPolicy(instanceId);
	}

	@Override
	public Policy setInstanceIAMPolicy(String instanceId, Policy policy) {
		return client.setInstanceIAMPolicy(instanceId, policy);
	}

	@Override
	public Iterable<String> testInstanceIAMPermissions(String instanceId, Iterable<String> permissions) {
		return client.testInstanceIAMPermissions(instanceId, permissions);
	}

	@Override
	public Builder newInstanceBuilder(InstanceId id) {
		return client.newInstanceBuilder(id);
	}

	@Override
	public void cancelOperation(String name) {
		client.cancelOperation(name);
	}

	@Override
	public Operation getOperation(String name) {
		return client.getOperation(name);
	}
}

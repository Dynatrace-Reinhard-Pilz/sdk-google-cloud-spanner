package dynatrace.com.google.cloud.spanner;

import java.util.Objects;

import com.google.cloud.spanner.Spanner;

public final class Tracing {
	
	private Tracing() {
		// preven instantiation
	}

	public static Spanner of(Spanner spanner) {
		Objects.requireNonNull(spanner);
		
		if (spanner instanceof SpannerImpl) {
			return spanner;
		}
		
		return new SpannerImpl(spanner);
	}
}

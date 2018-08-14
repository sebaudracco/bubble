package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.firebase.jobdispatcher.JobTrigger.ExecutionWindowTrigger;

final class JobCoder {
    private final boolean includeExtras;
    private final String prefix;

    JobCoder(String prefix, boolean includeExtras) {
        this.includeExtras = includeExtras;
        this.prefix = prefix;
    }

    @NonNull
    Bundle encode(@NonNull JobParameters jobParameters, @NonNull Bundle data) {
        if (data == null) {
            throw new IllegalArgumentException("Unexpected null Bundle provided");
        }
        data.putInt(this.prefix + "persistent", jobParameters.getLifetime());
        data.putBoolean(this.prefix + "recurring", jobParameters.isRecurring());
        data.putBoolean(this.prefix + "replace_current", jobParameters.shouldReplaceCurrent());
        data.putString(this.prefix + "tag", jobParameters.getTag());
        data.putString(this.prefix + NotificationCompat.CATEGORY_SERVICE, jobParameters.getService());
        data.putInt(this.prefix + "constraints", Constraint.compact(jobParameters.getConstraints()));
        if (this.includeExtras) {
            data.putBundle(this.prefix + "extras", jobParameters.getExtras());
        }
        encodeTrigger(jobParameters.getTrigger(), data);
        encodeRetryStrategy(jobParameters.getRetryStrategy(), data);
        return data;
    }

    JobInvocation decodeIntentBundle(@NonNull Bundle bundle) {
        if (bundle == null) {
            Log.e("FJD.ExternalReceiver", "Unexpected null Bundle provided");
            return null;
        }
        Bundle taskExtras = bundle.getBundle("extras");
        if (taskExtras != null) {
            return decode(taskExtras).build();
        }
        return null;
    }

    @Nullable
    public JobInvocation$Builder decode(@NonNull Bundle data) {
        if (data == null) {
            throw new IllegalArgumentException("Unexpected null Bundle provided");
        }
        boolean recur = data.getBoolean(this.prefix + "recurring");
        boolean replaceCur = data.getBoolean(this.prefix + "replace_current");
        int lifetime = data.getInt(this.prefix + "persistent");
        int[] constraints = Constraint.uncompact(data.getInt(this.prefix + "constraints"));
        JobTrigger trigger = decodeTrigger(data);
        RetryStrategy retryStrategy = decodeRetryStrategy(data);
        String tag = data.getString(this.prefix + "tag");
        String service = data.getString(this.prefix + NotificationCompat.CATEGORY_SERVICE);
        if (tag == null || service == null || trigger == null || retryStrategy == null) {
            return null;
        }
        JobInvocation$Builder builder = new JobInvocation$Builder();
        builder.setTag(tag);
        builder.setService(service);
        builder.setTrigger(trigger);
        builder.setRetryStrategy(retryStrategy);
        builder.setRecurring(recur);
        builder.setLifetime(lifetime);
        builder.setConstraints(constraints);
        builder.setReplaceCurrent(replaceCur);
        builder.addExtras(data);
        return builder;
    }

    @NonNull
    private JobTrigger decodeTrigger(Bundle data) {
        switch (data.getInt(this.prefix + "trigger_type")) {
            case 1:
                return Trigger.executionWindow(data.getInt(this.prefix + "window_start"), data.getInt(this.prefix + "window_end"));
            case 2:
                return Trigger.NOW;
            default:
                if (Log.isLoggable("FJD.ExternalReceiver", 3)) {
                    Log.d("FJD.ExternalReceiver", "Unsupported trigger.");
                }
                return null;
        }
    }

    private void encodeTrigger(JobTrigger trigger, Bundle data) {
        if (trigger == Trigger.NOW) {
            data.putInt(this.prefix + "trigger_type", 2);
        } else if (trigger instanceof ExecutionWindowTrigger) {
            ExecutionWindowTrigger t = (ExecutionWindowTrigger) trigger;
            data.putInt(this.prefix + "trigger_type", 1);
            data.putInt(this.prefix + "window_start", t.getWindowStart());
            data.putInt(this.prefix + "window_end", t.getWindowEnd());
        } else {
            throw new IllegalArgumentException("Unsupported trigger.");
        }
    }

    private RetryStrategy decodeRetryStrategy(Bundle data) {
        int policy = data.getInt(this.prefix + "retry_policy");
        if (policy == 1 || policy == 2) {
            return new RetryStrategy(policy, data.getInt(this.prefix + "initial_backoff_seconds"), data.getInt(this.prefix + "maximum_backoff_seconds"));
        }
        return RetryStrategy.DEFAULT_EXPONENTIAL;
    }

    private void encodeRetryStrategy(RetryStrategy retryStrategy, Bundle data) {
        if (retryStrategy == null) {
            retryStrategy = RetryStrategy.DEFAULT_EXPONENTIAL;
        }
        data.putInt(this.prefix + "retry_policy", retryStrategy.getPolicy());
        data.putInt(this.prefix + "initial_backoff_seconds", retryStrategy.getInitialBackoff());
        data.putInt(this.prefix + "maximum_backoff_seconds", retryStrategy.getMaximumBackoff());
    }
}

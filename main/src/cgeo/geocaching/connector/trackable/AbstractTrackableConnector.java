package cgeo.geocaching.connector.trackable;

import cgeo.geocaching.AbstractLoggingActivity;
import cgeo.geocaching.models.Trackable;
import cgeo.geocaching.models.TrackableLog;
import cgeo.geocaching.connector.AbstractConnector;
import cgeo.geocaching.connector.UserAction;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Observable;

import java.util.Collections;
import java.util.List;

public abstract class AbstractTrackableConnector implements TrackableConnector {

    @Override
    public int getPreferenceActivity() {
        return 0;
    }

    @Override
    public boolean isLoggable() {
        return false;
    }

    @Override
    public boolean canHandleTrackable(@Nullable final String geocode, @Nullable final TrackableBrand brand) {
        if (brand == null || brand == TrackableBrand.UNKNOWN) {
            return canHandleTrackable(geocode);
        }
        return brand == getBrand() && canHandleTrackable(geocode);
    }

    @Override
    public boolean hasTrackableUrls() {
        return true;
    }

    @Override
    @Nullable
    public String getTrackableCodeFromUrl(@NonNull final String url) {
        return null;
    }

    @Override
    @Nullable
    public String getTrackableTrackingCodeFromUrl(@NonNull final String url) {
        return null;
    }

    @Override
    @NonNull
    public List<UserAction> getUserActions() {
        return AbstractConnector.getDefaultUserActions();
    }

    @Override
    @NonNull
    public String getUrl(@NonNull final Trackable trackable) {
        throw new IllegalStateException("this trackable does not have a corresponding URL");
    }

    @Override
    @NonNull
    public String getHostUrl() {
        return "https://" + getHost();
    }

    @Override
    @NonNull
    public String getTestUrl() {
        return getHostUrl();
    }

    @Override
    @Nullable
    public String getProxyUrl() {
        return null;
    }

    @Override
    @NonNull
    public List<Trackable> searchTrackables(final String geocode) {
        return Collections.emptyList();
    }

    @Override
    @NonNull
    public List<Trackable> loadInventory() {
        return Collections.emptyList();
    }

    @Override
    public boolean isGenericLoggable() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public boolean isRegistered() {
        return false;
    }

    @Override
    public boolean recommendLogWithGeocode() {
        return false;
    }

    @Override
    @NonNull
    public Observable<TrackableLog> trackableLogInventory() {
        return Observable.empty();
    }

    @Override
    public int getTrackableLoggingManagerLoaderId() {
        return 0;
    }

    @Override
    public AbstractTrackableLoggingManager getTrackableLoggingManager(final AbstractLoggingActivity activity) {
        return null;
    }
}

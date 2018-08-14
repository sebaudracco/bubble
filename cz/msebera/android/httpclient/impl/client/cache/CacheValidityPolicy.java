package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import java.util.Date;

@Immutable
class CacheValidityPolicy {
    public static final long MAX_AGE = 2147483648L;

    CacheValidityPolicy() {
    }

    public long getCurrentAgeSecs(HttpCacheEntry entry, Date now) {
        return getCorrectedInitialAgeSecs(entry) + getResidentTimeSecs(entry, now);
    }

    public long getFreshnessLifetimeSecs(HttpCacheEntry entry) {
        long maxage = getMaxAge(entry);
        if (maxage > -1) {
            return maxage;
        }
        Date dateValue = entry.getDate();
        if (dateValue == null) {
            return 0;
        }
        Date expiry = getExpirationDate(entry);
        if (expiry == null) {
            return 0;
        }
        return (expiry.getTime() - dateValue.getTime()) / 1000;
    }

    public boolean isResponseFresh(HttpCacheEntry entry, Date now) {
        return getCurrentAgeSecs(entry, now) < getFreshnessLifetimeSecs(entry);
    }

    public boolean isResponseHeuristicallyFresh(HttpCacheEntry entry, Date now, float coefficient, long defaultLifetime) {
        return getCurrentAgeSecs(entry, now) < getHeuristicFreshnessLifetimeSecs(entry, coefficient, defaultLifetime);
    }

    public long getHeuristicFreshnessLifetimeSecs(HttpCacheEntry entry, float coefficient, long defaultLifetime) {
        Date dateValue = entry.getDate();
        Date lastModifiedValue = getLastModifiedValue(entry);
        if (dateValue == null || lastModifiedValue == null) {
            return defaultLifetime;
        }
        long diff = dateValue.getTime() - lastModifiedValue.getTime();
        if (diff < 0) {
            return 0;
        }
        return (long) (((float) (diff / 1000)) * coefficient);
    }

    public boolean isRevalidatable(HttpCacheEntry entry) {
        return (entry.getFirstHeader("ETag") == null && entry.getFirstHeader("Last-Modified") == null) ? false : true;
    }

    public boolean mustRevalidate(HttpCacheEntry entry) {
        return hasCacheControlDirective(entry, HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE);
    }

    public boolean proxyRevalidate(HttpCacheEntry entry) {
        return hasCacheControlDirective(entry, HeaderConstants.CACHE_CONTROL_PROXY_REVALIDATE);
    }

    public boolean mayReturnStaleWhileRevalidating(HttpCacheEntry entry, Date now) {
        for (Header h : entry.getHeaders("Cache-Control")) {
            for (HeaderElement elt : h.getElements()) {
                if (HeaderConstants.STALE_WHILE_REVALIDATE.equalsIgnoreCase(elt.getName())) {
                    try {
                        if (getStalenessSecs(entry, now) <= ((long) Integer.parseInt(elt.getValue()))) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
        return false;
    }

    public boolean mayReturnStaleIfError(HttpRequest request, HttpCacheEntry entry, Date now) {
        long stalenessSecs = getStalenessSecs(entry, now);
        return mayReturnStaleIfError(request.getHeaders("Cache-Control"), stalenessSecs) || mayReturnStaleIfError(entry.getHeaders("Cache-Control"), stalenessSecs);
    }

    private boolean mayReturnStaleIfError(Header[] headers, long stalenessSecs) {
        boolean result = false;
        for (Header h : headers) {
            for (HeaderElement elt : h.getElements()) {
                if (HeaderConstants.STALE_IF_ERROR.equals(elt.getName())) {
                    try {
                        if (stalenessSecs <= ((long) Integer.parseInt(elt.getValue()))) {
                            result = true;
                            break;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
        return result;
    }

    @Deprecated
    protected Date getDateValue(HttpCacheEntry entry) {
        return entry.getDate();
    }

    protected Date getLastModifiedValue(HttpCacheEntry entry) {
        Header dateHdr = entry.getFirstHeader("Last-Modified");
        if (dateHdr == null) {
            return null;
        }
        return DateUtils.parseDate(dateHdr.getValue());
    }

    protected long getContentLengthValue(HttpCacheEntry entry) {
        long j = -1;
        Header cl = entry.getFirstHeader("Content-Length");
        if (cl != null) {
            try {
                j = Long.parseLong(cl.getValue());
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    protected boolean hasContentLengthHeader(HttpCacheEntry entry) {
        return entry.getFirstHeader("Content-Length") != null;
    }

    protected boolean contentLengthHeaderMatchesActualLength(HttpCacheEntry entry) {
        return !hasContentLengthHeader(entry) || getContentLengthValue(entry) == entry.getResource().length();
    }

    protected long getApparentAgeSecs(HttpCacheEntry entry) {
        Date dateValue = entry.getDate();
        if (dateValue == null) {
            return MAX_AGE;
        }
        long diff = entry.getResponseDate().getTime() - dateValue.getTime();
        if (diff >= 0) {
            return diff / 1000;
        }
        return 0;
    }

    protected long getAgeValue(HttpCacheEntry entry) {
        long ageValue = 0;
        for (Header hdr : entry.getHeaders("Age")) {
            long hdrAge;
            try {
                hdrAge = Long.parseLong(hdr.getValue());
                if (hdrAge < 0) {
                    hdrAge = MAX_AGE;
                }
            } catch (NumberFormatException e) {
                hdrAge = MAX_AGE;
            }
            if (hdrAge > ageValue) {
                ageValue = hdrAge;
            }
        }
        return ageValue;
    }

    protected long getCorrectedReceivedAgeSecs(HttpCacheEntry entry) {
        long apparentAge = getApparentAgeSecs(entry);
        long ageValue = getAgeValue(entry);
        return apparentAge > ageValue ? apparentAge : ageValue;
    }

    protected long getResponseDelaySecs(HttpCacheEntry entry) {
        return (entry.getResponseDate().getTime() - entry.getRequestDate().getTime()) / 1000;
    }

    protected long getCorrectedInitialAgeSecs(HttpCacheEntry entry) {
        return getCorrectedReceivedAgeSecs(entry) + getResponseDelaySecs(entry);
    }

    protected long getResidentTimeSecs(HttpCacheEntry entry, Date now) {
        return (now.getTime() - entry.getResponseDate().getTime()) / 1000;
    }

    protected long getMaxAge(HttpCacheEntry entry) {
        long maxage = -1;
        for (Header hdr : entry.getHeaders("Cache-Control")) {
            for (HeaderElement elt : hdr.getElements()) {
                if ("max-age".equals(elt.getName()) || "s-maxage".equals(elt.getName())) {
                    try {
                        long currMaxAge = Long.parseLong(elt.getValue());
                        if (maxage == -1 || currMaxAge < maxage) {
                            maxage = currMaxAge;
                        }
                    } catch (NumberFormatException e) {
                        maxage = 0;
                    }
                }
            }
        }
        return maxage;
    }

    protected Date getExpirationDate(HttpCacheEntry entry) {
        Header expiresHeader = entry.getFirstHeader("Expires");
        if (expiresHeader == null) {
            return null;
        }
        return DateUtils.parseDate(expiresHeader.getValue());
    }

    public boolean hasCacheControlDirective(HttpCacheEntry entry, String directive) {
        for (Header h : entry.getHeaders("Cache-Control")) {
            for (HeaderElement elt : h.getElements()) {
                if (directive.equalsIgnoreCase(elt.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public long getStalenessSecs(HttpCacheEntry entry, Date now) {
        long age = getCurrentAgeSecs(entry, now);
        long freshness = getFreshnessLifetimeSecs(entry);
        if (age <= freshness) {
            return 0;
        }
        return age - freshness;
    }
}

package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;

@Deprecated
@VisibleForTesting
public interface Person extends Freezable<Person> {

    @Deprecated
    @VisibleForTesting
    public interface AgeRange extends Freezable<AgeRange> {
        @Deprecated
        int getMax();

        @Deprecated
        int getMin();

        @Deprecated
        boolean hasMax();

        @Deprecated
        boolean hasMin();
    }

    @Deprecated
    @VisibleForTesting
    public interface Cover extends Freezable<Cover> {

        @Deprecated
        @VisibleForTesting
        public interface CoverInfo extends Freezable<CoverInfo> {
            @Deprecated
            int getLeftImageOffset();

            @Deprecated
            int getTopImageOffset();

            @Deprecated
            boolean hasLeftImageOffset();

            @Deprecated
            boolean hasTopImageOffset();
        }

        @Deprecated
        @VisibleForTesting
        public interface CoverPhoto extends Freezable<CoverPhoto> {
            @Deprecated
            int getHeight();

            @Deprecated
            String getUrl();

            @Deprecated
            int getWidth();

            @Deprecated
            boolean hasHeight();

            @Deprecated
            boolean hasUrl();

            @Deprecated
            boolean hasWidth();
        }

        @Deprecated
        public static final class Layout {
            @Deprecated
            public static final int BANNER = 0;

            private Layout() {
            }
        }

        @Deprecated
        CoverInfo getCoverInfo();

        @Deprecated
        CoverPhoto getCoverPhoto();

        @Deprecated
        int getLayout();

        @Deprecated
        boolean hasCoverInfo();

        @Deprecated
        boolean hasCoverPhoto();

        @Deprecated
        boolean hasLayout();
    }

    @Deprecated
    public static final class Gender {
        @Deprecated
        public static final int FEMALE = 1;
        @Deprecated
        public static final int MALE = 0;
        @Deprecated
        public static final int OTHER = 2;

        private Gender() {
        }
    }

    @Deprecated
    @VisibleForTesting
    public interface Image extends Freezable<Image> {
        @Deprecated
        String getUrl();

        @Deprecated
        boolean hasUrl();
    }

    @Deprecated
    @VisibleForTesting
    public interface Name extends Freezable<Name> {
        @Deprecated
        String getFamilyName();

        @Deprecated
        String getFormatted();

        @Deprecated
        String getGivenName();

        @Deprecated
        String getHonorificPrefix();

        @Deprecated
        String getHonorificSuffix();

        @Deprecated
        String getMiddleName();

        @Deprecated
        boolean hasFamilyName();

        @Deprecated
        boolean hasFormatted();

        @Deprecated
        boolean hasGivenName();

        @Deprecated
        boolean hasHonorificPrefix();

        @Deprecated
        boolean hasHonorificSuffix();

        @Deprecated
        boolean hasMiddleName();
    }

    @Deprecated
    public static final class ObjectType {
        @Deprecated
        public static final int PAGE = 1;
        @Deprecated
        public static final int PERSON = 0;

        private ObjectType() {
        }
    }

    @Deprecated
    @VisibleForTesting
    public interface Organizations extends Freezable<Organizations> {

        @Deprecated
        public static final class Type {
            @Deprecated
            public static final int SCHOOL = 1;
            @Deprecated
            public static final int WORK = 0;

            private Type() {
            }
        }

        @Deprecated
        String getDepartment();

        @Deprecated
        String getDescription();

        @Deprecated
        String getEndDate();

        @Deprecated
        String getLocation();

        @Deprecated
        String getName();

        @Deprecated
        String getStartDate();

        @Deprecated
        String getTitle();

        @Deprecated
        int getType();

        @Deprecated
        boolean hasDepartment();

        @Deprecated
        boolean hasDescription();

        @Deprecated
        boolean hasEndDate();

        @Deprecated
        boolean hasLocation();

        @Deprecated
        boolean hasName();

        @Deprecated
        boolean hasPrimary();

        @Deprecated
        boolean hasStartDate();

        @Deprecated
        boolean hasTitle();

        @Deprecated
        boolean hasType();

        @Deprecated
        boolean isPrimary();
    }

    @Deprecated
    @VisibleForTesting
    public interface PlacesLived extends Freezable<PlacesLived> {
        @Deprecated
        String getValue();

        @Deprecated
        boolean hasPrimary();

        @Deprecated
        boolean hasValue();

        @Deprecated
        boolean isPrimary();
    }

    @Deprecated
    public static final class RelationshipStatus {
        @Deprecated
        public static final int ENGAGED = 2;
        @Deprecated
        public static final int IN_A_RELATIONSHIP = 1;
        @Deprecated
        public static final int IN_CIVIL_UNION = 8;
        @Deprecated
        public static final int IN_DOMESTIC_PARTNERSHIP = 7;
        @Deprecated
        public static final int ITS_COMPLICATED = 4;
        @Deprecated
        public static final int MARRIED = 3;
        @Deprecated
        public static final int OPEN_RELATIONSHIP = 5;
        @Deprecated
        public static final int SINGLE = 0;
        @Deprecated
        public static final int WIDOWED = 6;

        private RelationshipStatus() {
        }
    }

    @Deprecated
    @VisibleForTesting
    public interface Urls extends Freezable<Urls> {

        public static final class Type {
            @Deprecated
            public static final int CONTRIBUTOR = 6;
            @Deprecated
            public static final int OTHER = 4;
            @Deprecated
            public static final int OTHER_PROFILE = 5;
            @Deprecated
            public static final int WEBSITE = 7;

            private Type() {
            }
        }

        @Deprecated
        String getLabel();

        @Deprecated
        int getType();

        @Deprecated
        String getValue();

        @Deprecated
        boolean hasLabel();

        @Deprecated
        boolean hasType();

        @Deprecated
        boolean hasValue();
    }

    @Deprecated
    String getAboutMe();

    @Deprecated
    AgeRange getAgeRange();

    @Deprecated
    String getBirthday();

    @Deprecated
    String getBraggingRights();

    @Deprecated
    int getCircledByCount();

    @Deprecated
    Cover getCover();

    @Deprecated
    String getCurrentLocation();

    @Deprecated
    String getDisplayName();

    @Deprecated
    int getGender();

    @Deprecated
    String getId();

    @Deprecated
    Image getImage();

    @Deprecated
    String getLanguage();

    @Deprecated
    Name getName();

    @Deprecated
    String getNickname();

    @Deprecated
    int getObjectType();

    @Deprecated
    List<Organizations> getOrganizations();

    @Deprecated
    List<PlacesLived> getPlacesLived();

    @Deprecated
    int getPlusOneCount();

    @Deprecated
    int getRelationshipStatus();

    @Deprecated
    String getTagline();

    @Deprecated
    String getUrl();

    @Deprecated
    List<Urls> getUrls();

    @Deprecated
    boolean hasAboutMe();

    @Deprecated
    boolean hasAgeRange();

    @Deprecated
    boolean hasBirthday();

    @Deprecated
    boolean hasBraggingRights();

    @Deprecated
    boolean hasCircledByCount();

    @Deprecated
    boolean hasCover();

    @Deprecated
    boolean hasCurrentLocation();

    @Deprecated
    boolean hasDisplayName();

    @Deprecated
    boolean hasGender();

    @Deprecated
    boolean hasId();

    @Deprecated
    boolean hasImage();

    @Deprecated
    boolean hasIsPlusUser();

    @Deprecated
    boolean hasLanguage();

    @Deprecated
    boolean hasName();

    @Deprecated
    boolean hasNickname();

    @Deprecated
    boolean hasObjectType();

    @Deprecated
    boolean hasOrganizations();

    @Deprecated
    boolean hasPlacesLived();

    @Deprecated
    boolean hasPlusOneCount();

    @Deprecated
    boolean hasRelationshipStatus();

    @Deprecated
    boolean hasTagline();

    @Deprecated
    boolean hasUrl();

    @Deprecated
    boolean hasUrls();

    @Deprecated
    boolean hasVerified();

    @Deprecated
    boolean isPlusUser();

    @Deprecated
    boolean isVerified();
}

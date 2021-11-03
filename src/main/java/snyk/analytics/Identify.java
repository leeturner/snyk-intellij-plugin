//
//  Identify.java
//  This file is auto-generated by Amplitude. Run `ampli pull jetbrains` to update.
//
//  Works with versions 1.2+ of ly.iterative.itly:sdk and plugins
//  https://search.maven.org/search?q=itly
//

package snyk.analytics;

import ly.iterative.itly.Event;

import java.util.HashMap;

public class Identify extends Event {
    private static final String NAME = "identify";
    private static final String ID = "identify";
    private static final String VERSION = "94.0.0";

    private Identify(Builder builder) {
        super(NAME, builder.properties, ID, VERSION);
    }

    private Identify(Identify clone) {
        super(NAME, new HashMap<>(clone.getProperties()), ID, VERSION);
    }

    public Identify clone() {
        return new Identify(this);
    }

    public static Builder builder() { return new Builder(); }

    // Inner Builder class with required properties
    public static class Builder implements IBuild {
        private final HashMap<String, Object> properties = new HashMap<String, Object>();

        private Builder() {

        }

        /**
         * Link to access more information about the user
         */
        public Builder adminLink(String adminLink) {
            this.properties.put("adminLink", adminLink);
            return this;
        }

        /**
         * Auth provider (login method)
         */
        public Builder authProvider(String authProvider) {
            this.properties.put("authProvider", authProvider);
            return this;
        }

        /**
         * Timestamp of user creation
         */
        public Builder createdAt(Double createdAt) {
            this.properties.put("createdAt", createdAt);
            return this;
        }

        /**
         * Email address for the user
         */
        public Builder email(String email) {
            this.properties.put("email", email);
            return this;
        }

        /**
         * Whether or not the user has their first integration set up
         */
        public Builder hasFirstIntegration(boolean hasFirstIntegration) {
            this.properties.put("hasFirstIntegration", hasFirstIntegration);
            return this;
        }

        /**
         * Whether or not the user has their first project imported
         */
        public Builder hasFirstProject(boolean hasFirstProject) {
            this.properties.put("hasFirstProject", hasFirstProject);
            return this;
        }

        /**
         * Applies to non-user identities, such as Snyk Orgs
         */
        public Builder isNonUser(boolean isNonUser) {
            this.properties.put("isNonUser", isNonUser);
            return this;
        }

        /**
         * Whether or not the user belongs to the Snyk org (determined by the email address ending with @snyk.io)
         */
        public Builder isSnyk(boolean isSnyk) {
            this.properties.put("isSnyk", isSnyk);
            return this;
        }

        /**
         * Whether or not the user should be considered a Snyk administrator
         */
        public Builder isSnykAdmin(boolean isSnykAdmin) {
            this.properties.put("isSnykAdmin", isSnykAdmin);
            return this;
        }

        /**
         * Name of the user
         */
        public Builder name(String name) {
            this.properties.put("name", name);
            return this;
        }

        /**
         * Public ID of user
         */
        public Builder userId(String userId) {
            this.properties.put("user_id", userId);
            return this;
        }

        /**
         * Username of the user
         */
        public Builder username(String username) {
            this.properties.put("username", username);
            return this;
        }

        /**
         * query utm_campaign
         */
        public Builder utmCampaign(String utmCampaign) {
            this.properties.put("utmCampaign", utmCampaign);
            return this;
        }

        /**
         * query utm_medium
         */
        public Builder utmMedium(String utmMedium) {
            this.properties.put("utmMedium", utmMedium);
            return this;
        }

        /**
         * query utm_source
         */
        public Builder utmSource(String utmSource) {
            this.properties.put("utmSource", utmSource);
            return this;
        }

        public Identify build() {
            return new Identify(this);
        }
    }

    /** Build interface with optional properties */
    public interface IBuild {
        IBuild adminLink(String adminLink);
        IBuild authProvider(String authProvider);
        IBuild createdAt(Double createdAt);
        IBuild email(String email);
        IBuild hasFirstIntegration(boolean hasFirstIntegration);
        IBuild hasFirstProject(boolean hasFirstProject);
        IBuild isNonUser(boolean isNonUser);
        IBuild isSnyk(boolean isSnyk);
        IBuild isSnykAdmin(boolean isSnykAdmin);
        IBuild name(String name);
        IBuild userId(String userId);
        IBuild username(String username);
        IBuild utmCampaign(String utmCampaign);
        IBuild utmMedium(String utmMedium);
        IBuild utmSource(String utmSource);
        Identify build();
    }
}

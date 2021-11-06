//
//  HealthScoreIsClicked.java
//  This file is auto-generated by Amplitude. Run `ampli pull jetbrains` to update.
//
//  Works with versions 1.2+ of ly.iterative.itly:sdk and plugins
//  https://search.maven.org/search?q=itly
//

package snyk.analytics;

import ly.iterative.itly.Event;

import java.util.HashMap;

public class HealthScoreIsClicked extends Event {
    private static final String NAME = "Health Score Is Clicked";
    private static final String ID = "47cf2487-2066-4f12-9846-cba17d1fa257";
    private static final String VERSION = "5.0.0";

    public enum Ide {
        VISUAL_STUDIO_CODE("Visual Studio Code"), VISUAL_STUDIO("Visual Studio"), ECLIPSE("Eclipse"), JETBRAINS("JetBrains");

        private String ide;

        public String getIde()
        {
            return this.ide;
        }

        Ide(String ide)
        {
            this.ide = ide;
        }
    }

    public enum Ecosystem {
        ALL("all"), DOCKER("docker"), JAVA("java"), JAVASCRIPT("javascript"), KUBERNETES("kubernetes"), NPM("npm"), PYTHON("python"), PHP("php"), C__("c++"), C_("c#");

        private String ecosystem;

        public String getEcosystem()
        {
            return this.ecosystem;
        }

        Ecosystem(String ecosystem)
        {
            this.ecosystem = ecosystem;
        }
    }

    private HealthScoreIsClicked(Builder builder) {
        super(NAME, builder.properties, ID, VERSION);
    }

    private HealthScoreIsClicked(HealthScoreIsClicked clone) {
        super(NAME, new HashMap<>(clone.getProperties()), ID, VERSION);
    }

    public HealthScoreIsClicked clone() {
        return new HealthScoreIsClicked(this);
    }

    public static IEcosystem builder() { return new Builder(); }

    // Inner Builder class with required properties
    public static class Builder implements IEcosystem, IIde, IPackageName, IBuild {
        private final HashMap<String, Object> properties = new HashMap<String, Object>();

        private Builder() {
            this.properties.put("itly", true);
        }

        /**
         * Advisor: what ecosystem for advisor score was used:
         *
         * * docker
         *
         * * npm
         *
         * * python
         *
         * Learn: the most general grouping for topics/lessons:
         *
         * * java
         *
         * * javascript
         *
         * * kubernetes
         * <p>
         * Must be followed by {@link IIde#ide(Ide)
         */
        public IIde ecosystem(Ecosystem ecosystem) {
            this.properties.put("ecosystem", ecosystem.getEcosystem());
            return this;
        }

        /**
         * Ide family.
         * <p>
         * Must be followed by {@link IPackageName#packageName(String)
         */
        public IPackageName ide(Ide ide) {
            this.properties.put("ide", ide.getIde());
            return this;
        }

        /**
         * Name of the package for which we get a score.
         * <p>
         * Must be followed by by additional optional properties or build() method
         */
        public Builder packageName(String packageName) {
            this.properties.put("packageName", packageName);
            return this;
        }

        public HealthScoreIsClicked build() {
            return new HealthScoreIsClicked(this);
        }
    }

    // Required property interfaces
    public interface IEcosystem {
        IIde ecosystem(Ecosystem ecosystem);
    }

    public interface IIde {
        IPackageName ide(Ide ide);
    }

    public interface IPackageName {
        Builder packageName(String packageName);
    }

    /** Build interface with optional properties */
    public interface IBuild {
        HealthScoreIsClicked build();
    }
}

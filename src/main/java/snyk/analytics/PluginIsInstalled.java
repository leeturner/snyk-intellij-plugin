//
//  PluginIsInstalled.java
//  This file is auto-generated by Amplitude. Run `ampli pull jetbrains` to update.
//
//  Works with versions 1.2+ of ly.iterative.itly:sdk and plugins
//  https://search.maven.org/search?q=itly
//

package snyk.analytics;

import ly.iterative.itly.Event;

import java.util.HashMap;

public class PluginIsInstalled extends Event {
    private static final String NAME = "Plugin Is Installed";
    private static final String ID = "7bb34693-366e-460e-8f4c-5b3f1c71888a";
    private static final String VERSION = "1.0.0";

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

    private PluginIsInstalled(Builder builder) {
        super(NAME, builder.properties, ID, VERSION);
    }

    private PluginIsInstalled(PluginIsInstalled clone) {
        super(NAME, new HashMap<>(clone.getProperties()), ID, VERSION);
    }

    public PluginIsInstalled clone() {
        return new PluginIsInstalled(this);
    }

    public static IIde builder() { return new Builder(); }

    // Inner Builder class with required properties
    public static class Builder implements IIde, IBuild {
        private final HashMap<String, Object> properties = new HashMap<String, Object>();

        private Builder() {
            this.properties.put("itly", true);
        }

        /**
         * Ide family.
         * <p>
         * Must be followed by by additional optional properties or build() method
         */
        public Builder ide(Ide ide) {
            this.properties.put("ide", ide.getIde());
            return this;
        }

        public PluginIsInstalled build() {
            return new PluginIsInstalled(this);
        }
    }

    // Required property interfaces
    public interface IIde {
        Builder ide(Ide ide);
    }

    /** Build interface with optional properties */
    public interface IBuild {
        PluginIsInstalled build();
    }
}

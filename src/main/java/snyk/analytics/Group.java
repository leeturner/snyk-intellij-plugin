//
//  Group.java
//  This file is auto-generated by Iteratively. Run `itly pull jetbrains` to update.
//

package snyk.analytics;

import java.util.HashMap;

import ly.iterative.itly.Event;

public class Group extends Event {
    private static final String NAME = "group";
    private static final String ID = "group";
    private static final String VERSION = "31.0.0";

    public enum GroupType {
        ORG("org"), GROUP("group"), ACCOUNT("account");

        private String groupType;

        public String getGroupType()
        {
            return this.groupType;
        }

        GroupType(String groupType)
        {
            this.groupType = groupType;
        }
    }

    private Group(Builder builder) {
        super(NAME, builder.properties, ID, VERSION);
    }

    private Group(Group clone) {
        super(NAME, new HashMap<>(clone.getProperties()), ID, VERSION);
    }

    public Group clone() {
        return new Group(this);
    }

    public static Builder builder() { return new Builder(); }

    // Inner Builder class with required properties
    public static class Builder implements IBuild {
        private final HashMap<String, Object> properties = new HashMap<String, Object>();

        private Builder() {

        }

        /**
         * ID that is used in conjunction with a groupType to specify an Org or a Group association: {groupId: 1234, groupType: "org"}
         */
        public Builder groupId(String groupId) {
            this.properties.put("groupId", groupId);
            return this;
        }

        /**
         * The name of the group associated with the org
         */
        public Builder groupName(String groupName) {
            this.properties.put("groupName", groupName);
            return this;
        }

        /**
         * Key that is used to specify the name of the Segment Group that a groupId is being set for.
         */
        public Builder groupType(GroupType groupType) {
            this.properties.put("groupType", groupType.getGroupType());
            return this;
        }

        /**
         * The internal name (org.name) of the org
         */
        public Builder internalName(String internalName) {
            this.properties.put("internalName", internalName);
            return this;
        }

        /**
         * The display name of the org
         */
        public Builder name(String name) {
            this.properties.put("name", name);
            return this;
        }

        /**
         * The plan of the org
         */
        public Builder plan(String plan) {
            this.properties.put("plan", plan);
            return this;
        }

        public Group build() {
            return new Group(this);
        }
    }

    /** Build interface with optional properties */
    public interface IBuild {
        IBuild groupId(String groupId);
        IBuild groupName(String groupName);
        IBuild groupType(GroupType groupType);
        IBuild internalName(String internalName);
        IBuild name(String name);
        IBuild plan(String plan);
        Group build();
    }
}
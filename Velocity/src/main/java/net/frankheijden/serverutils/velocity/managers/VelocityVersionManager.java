package net.frankheijden.serverutils.velocity.managers;

import net.frankheijden.serverutils.common.managers.AbstractVersionManager;
import net.frankheijden.serverutils.velocity.ServerUtils;

public class VelocityVersionManager extends AbstractVersionManager {

    /**
     * Creates a new VersionManager instance.
     * Used for automatic updating.
     */
    public VelocityVersionManager() {
        super(ServerUtils.getInstance().getVersion());
    }
}

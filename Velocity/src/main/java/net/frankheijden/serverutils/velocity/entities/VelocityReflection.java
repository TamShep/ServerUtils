package net.frankheijden.serverutils.velocity.entities;

import net.frankheijden.serverutils.common.reflection.ReflectionUtils;
import net.frankheijden.serverutils.common.reflection.VersionParam;

public class VelocityReflection extends ReflectionUtils {

    @Override
    public boolean isCompatible(VersionParam param) {
        return true;
    }
}

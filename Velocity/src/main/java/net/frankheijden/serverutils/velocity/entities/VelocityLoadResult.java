package net.frankheijden.serverutils.velocity.entities;

import net.frankheijden.serverutils.common.entities.LoadResult;
import net.frankheijden.serverutils.common.entities.Result;

public class VelocityLoadResult extends LoadResult<Object> {

    public VelocityLoadResult(Object obj, Result result) {
        super(obj, result);
    }

    public VelocityLoadResult(Object obj) {
        super(obj);
    }

    public VelocityLoadResult(Result result) {
        super(result);
    }
}

package net.frankheijden.serverutils.velocity.managers;

import com.google.inject.Inject;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.ScheduledTask;
import net.frankheijden.serverutils.common.managers.AbstractTaskManager;
import net.frankheijden.serverutils.velocity.ServerUtils;

public class VelocityTaskManager extends AbstractTaskManager<ScheduledTask> {

    @Inject
    private ServerUtils plugin;

    @Inject
    private ProxyServer proxy;

    public VelocityTaskManager() {
        super(ScheduledTask::cancel);
    }

    @Override
    protected ScheduledTask runTaskImpl(Runnable runnable) {
        return runTaskAsynchronouslyImpl(runnable);
    }

    @Override
    protected ScheduledTask runTaskAsynchronouslyImpl(Runnable runnable) {
        return proxy.getScheduler().buildTask(plugin, runnable).schedule();
    }
}

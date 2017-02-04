package jp.ne.naokiur.em.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jp.ne.naokiur.em.dao.InitialAccessor;

public class InitialContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InitialAccessor.INSTANCE.initialize();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

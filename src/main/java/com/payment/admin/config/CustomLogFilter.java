package com.payment.admin.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

public class CustomLogFilter extends AbstractMatcherFilter<ILoggingEvent> {

    private String levels;

    private Level[] arrLevel;

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (arrLevel == null && levels != null) {
            setLevels();
        }
        if (arrLevel != null) {
            for (Level lev : arrLevel) {
                if (lev == event.getLevel()) {
                    return FilterReply.ACCEPT;
                }
            }
        }
        return FilterReply.DENY;
    }

    private void setLevels() {
        if (!levels.isEmpty()) {
            arrLevel = new Level[levels.split("\\|").length];
            int i = 0;
            for (String str : levels.split("\\|")) {
                arrLevel[i] = Level.valueOf(str);
                i++;
            }
        }
    }

}


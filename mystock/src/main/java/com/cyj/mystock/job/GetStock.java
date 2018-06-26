package com.cyj.mystock.job;

import com.cyj.mystock.service.DfbService;
import com.cyj.mystock.service.GetDxjlService;
import com.cyj.mystock.service.GetStockService;
import com.cyj.mystock.service.ZfbService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Component
@Log4j2
public class GetStock {
    @Autowired
    private GetStockService getStockService;
    @Autowired
    private DfbService dfbService;
    @Autowired
    private ZfbService zfbService;
    @Autowired
    private GetDxjlService getDxjlService;

    private Timer timer = new Timer(true);

    private TimerTask timerTask = null;

    private boolean flag;

    private final long SECOND = 5 * 1000L;

    public boolean start() throws Exception {
        if (timerTask == null) {
            timer.schedule(timerTaskInstance(), 0L, SECOND);
        }
        return flag;
    }

    private TimerTask timerTaskInstance() {
        if (timerTask == null) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    try {
                        getStockService.run(flag);
                        getDxjlService.run();
                        zfbService.run();
                        dfbService.run();
                        if (flag) {
                            timerTask.cancel();
                            timerTask = null;
//                            timer = new Timer(true);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("[CronJob Execute Timer Exception]:", e);
                    }
                }
            };
        }
        return timerTask;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

package org.jeecg.modules.cinema.api.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.jeecg.modules.cinema.api.CinemaHelloApi;
import lombok.Setter;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JeecgBoot
 */
@Slf4j
@Component
public class CinemaHelloFallback implements FallbackFactory<CinemaHelloApi> {
    @Setter
    private Throwable cause;

    @Override
    public CinemaHelloApi create(Throwable throwable) {
        log.error("微服务接口调用失败： {}", cause);
        return null;
    }

}

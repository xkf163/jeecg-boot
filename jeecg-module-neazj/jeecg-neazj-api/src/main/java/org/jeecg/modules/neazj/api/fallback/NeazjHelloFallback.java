package org.jeecg.modules.neazj.api.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.jeecg.modules.neazj.api.NeazjHelloApi;
import lombok.Setter;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JeecgBoot
 */
@Slf4j
@Component
public class NeazjHelloFallback implements FallbackFactory<NeazjHelloApi> {
    @Setter
    private Throwable cause;

    @Override
    public NeazjHelloApi create(Throwable throwable) {
        log.error("微服务接口调用失败： {}", cause);
        return null;
    }

}

package org.jeecg.modules.neazj.api;
import org.jeecg.modules.neazj.api.fallback.NeazjHelloFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "jeecg-neazj", fallbackFactory = NeazjHelloFallback.class)
public interface NeazjHelloApi {

    /**
     * neazj hello 微服务接口
     * @param
     * @return
     */
    @GetMapping(value = "/neazj/hello")
    String callHello();
}

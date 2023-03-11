package org.jeecg.modules.cinema.api;
import org.jeecg.modules.cinema.api.fallback.CinemaHelloFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "jeecg-cinema", fallbackFactory = CinemaHelloFallback.class)
public interface CinemaHelloApi {

    /**
     * cinema hello 微服务接口
     * @param
     * @return
     */
    @GetMapping(value = "/cinema/hello")
    String callHello();
}

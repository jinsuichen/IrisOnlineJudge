package fun.icpc.iris.irisonlinejudge.config;

import fun.icpc.iris.irisonlinejudge.controller.interceptor.LoginInterceptor;
import fun.icpc.iris.irisonlinejudge.controller.interceptor.UserAccessInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAccessInterceptor(stringRedisTemplate))
                .addPathPatterns("/**")
                .order(0);

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/**/auth/register", "/api/**/auth/login")
                .order(1);
    }
}

package fun.icpc.iris.irisonlinejudge.config;

import fun.icpc.iris.irisonlinejudge.rest.interceptor.LoginInterceptor;
import fun.icpc.iris.irisonlinejudge.rest.interceptor.UserAccessInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final UserAccessInterceptor userAccessInterceptor;
    private final LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAccessInterceptor)
                .addPathPatterns("/**")
                .order(0);

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/**/auth/**")
                .order(1);
    }
}

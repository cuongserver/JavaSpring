package demo.spring.config.pipeline;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class PipelineConfigure implements WebMvcConfigurer {
    private final CorsInterceptor  corsInterceptor;
    private final AuthorizationInterceptor authorizationInterceptor;
    public PipelineConfigure(CorsInterceptor corsInterceptor, AuthorizationInterceptor authorizationInterceptor)
    {
        this.corsInterceptor = corsInterceptor;
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor).order(Ordered.HIGHEST_PRECEDENCE);
        registry.addInterceptor(authorizationInterceptor).order(0);
    }
}

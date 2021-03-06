package com.gmail.derevets.artem.springsecurityapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Класс конфигурации Spring компонентов
 * представления , настройка MVC.
 * Указывает Spring где находятся компоненты
 * представления, и как их отображать.
 * Помечен аннотацией @Configuration -
 * класс является источником определения
 * бинов; аннотацией @EnableWebMvc -
 * разрешает проекту использовать MVC;
 * аннотацией @ComponentScan - указываем
 * фреймворку Spring, что компоненты надо
 * искать внутри пакетах "ua.com.artemcoffee.controller"
 * и "ua.com.artemcoffee.config".
 *
 * @author Artem Derevets (derevets.artem@gmail.com)
 * @version 1.2
 * @see AppInitializer
 * @see RootConfig
 */
@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = {
                "com.gmail.derevets.artem.springsecurityapp.controller",
                "com.gmail.derevets.artem.springsecurityapp.config"
        }
)
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * Тип кодировки вьюшек.
     */
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";

    /**
     * Путь к вьюшкам.
     */
    private static final String PREFIX = "/WEB-INF/views/";

    /**
     * Разрешение вьюшек.
     */
    private static final String SUFFIX = ".jsp";

    /**
     * Путь к ресурсам.
     */
    private static final String RESOURCES_URL = "/resources/";

    /**
     * URL запроса для авторизации.
     */
    private static final String LOGIN_URL = "/login";

    /**
     * Название вьюшки авторизации.
     */
    private static final String LOGIN_VIEW_NAME = "login";

    /**
     * Указывает Spring'у где находятся
     * компоненты
     * представления, и как их отображать.
     * Вьюшкибудут лежать в директории
     * /WEB-INF/views/ и иметь разширение *.jsp.
     *
     * @return Реализация интерфейса ViewResolver
     * с настройками для вьюшек.
     */
    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setContentType(CONTENT_TYPE);
        viewResolver.setPrefix(PREFIX);
        viewResolver.setSuffix(SUFFIX);
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }

    /**
     * Указывает где будут хранится ресурсы.
     *
     * @param resource Объект класса ResourceHandlerRegistry
     *                 с настройками для ресурсов.
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry resource) {
        resource.addResourceHandler(RESOURCES_URL + "**")
                .addResourceLocations(RESOURCES_URL);
    }

    /**
     * Настройка логин-контроллера.
     * Оказывает помощь в регистрации
     * простого автоматизированного
     * логин-контроллера предварительно
     * сконфигурированных с кодом
     * состояния и вьюшкой.
     *
     * @param viewController Объект класса ViewControllerRegistry.
     */
    @Override
    public void addViewControllers(
            final ViewControllerRegistry viewController
    ) {
        viewController.addViewController("/").setViewName("");
        viewController.addViewController("/welcome").setViewName("welcome");
        viewController.addViewController("/admin").setViewName("admin/all");
        viewController.addViewController(LOGIN_URL)
                .setViewName(LOGIN_VIEW_NAME);

    }
}

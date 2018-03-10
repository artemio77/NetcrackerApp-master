package com.gmail.derevets.artem.springsecurityapp.config;

import com.gmail.derevets.artem.springsecurityapp.dao.imp.EmployeeDaoImp;
import com.gmail.derevets.artem.springsecurityapp.dao.imp.JobDaoImp;
import com.gmail.derevets.artem.springsecurityapp.dao.imp.UserDaoImp;
import com.gmail.derevets.artem.springsecurityapp.dao.imp.DepartmentDaoImp;
import com.gmail.derevets.artem.springsecurityapp.dao.interfaces.EmployeeDao;
import com.gmail.derevets.artem.springsecurityapp.model.Department;
import com.gmail.derevets.artem.springsecurityapp.model.Employee;
import com.gmail.derevets.artem.springsecurityapp.model.Job;
import com.gmail.derevets.artem.springsecurityapp.model.User;
import com.gmail.derevets.artem.springsecurityapp.service.imp.JobServiceImp;
import com.gmail.derevets.artem.springsecurityapp.service.imp.UserServiceImpl;
import com.gmail.derevets.artem.springsecurityapp.validator.UserValidator;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Locale;

@Configuration
@ComponentScan("com.gmail.derevets.artem.springsecurityapp.*")
@PropertySource("classpath:database.properties")
public class RootConfig {

    private final
    Environment env;

    private final static String CACHE_NAME = "MYCACHE";

    @Autowired
    public RootConfig(Environment env) {
        this.env = env;
    }


    @Bean
    public Job job() {
        return new Job();
    }

    @Bean
    public Employee employee() {
        return new Employee();
    }

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public Department department() {
        return new Department();
    }

    @Bean
    public UserValidator userValidator() {
        return new UserValidator();
    }

    @Bean
    public JobServiceImp jobService() {
        return new JobServiceImp();
    }

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserDaoImp userDao() {
        return new UserDaoImp();
    }

    @Bean
    public JobDaoImp jobDao() {
        return new JobDaoImp();
    }

    @Bean
    public DepartmentDaoImp departmentDaoImp() {
        return new DepartmentDaoImp(new Department());
    }

    @Bean
    public EmployeeDao employeeDao() {
        return new EmployeeDaoImp(new Employee());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:validation.properties");
        // if true, the key of the message will be displayed if the key is not
        // found, instead of throwing a NoSuchMessageException
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        // # -1 : never reload, 0 always reload
        messageSource.setCacheSeconds(0);
        return messageSource;
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        Locale.setDefault(Locale.ENGLISH);
        dataSource.setURL("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUser("artem");
        dataSource.setPassword("artem");
       /* dataSource.setConnectionCachingEnabled(true);
        dataSource.setConnectionCacheName(CACHE_NAME);
        Properties cacheProps = new Properties();
        cacheProps.setProperty("MinLimit", "1");
        cacheProps.setProperty("MaxLimit", "4");
        cacheProps.setProperty("InitialLimit", "1");
        cacheProps.setProperty("ConnectionWaitTimeout", "1");
        cacheProps.setProperty("ValidateConnection", "true");

        dataSource.setConnectionCacheProperties(cacheProps);
*/
        return dataSource;
    }


}

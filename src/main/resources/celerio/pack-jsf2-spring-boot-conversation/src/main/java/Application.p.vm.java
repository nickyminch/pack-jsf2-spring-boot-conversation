$output.java($Root, "Application")##

$output.require("javax.faces.webapp.FacesServlet")##

$output.require("org.springframework.boot.SpringApplication")##
$output.require("org.springframework.boot.autoconfigure.SpringBootApplication")##
$output.require("org.springframework.boot.web.servlet.ServletListenerRegistrationBean")##
$output.require("org.springframework.boot.web.servlet.ServletRegistrationBean")##
$output.require("org.springframework.context.ApplicationContext")##
$output.require("org.springframework.context.annotation.Bean")##
$output.require($Configuration, "AppContext")##

$output.require("com.sun.faces.config.ConfigureListener")##

@SpringBootApplication(scanBasePackages = { "com.jaxio.jpa.querybyexample", "${Root.packageName}" })
public class ${output.currentClass}  {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(${output.currentClass}.class, args);
		AppContext.getInstance().setContext(context);
	}

	 @Bean
    public FacesServlet facesServlet() {
        FacesServlet fs =  new FacesServlet();
        return fs;
    }

    @Bean
    public ServletRegistrationBean<FacesServlet> facesServletRegistration() {
        ServletRegistrationBean<FacesServlet> registration = new ServletRegistrationBean<FacesServlet>(
            facesServlet(), "*.faces");
        registration.setName("facesServlet");
        return registration;
    }
    
    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<ConfigureListener>(
            new ConfigureListener());
    }
}

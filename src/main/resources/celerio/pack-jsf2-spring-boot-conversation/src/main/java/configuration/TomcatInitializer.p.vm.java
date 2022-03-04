$output.java($Configuration, "TomcatInitializer")##

$output.require("org.apache.catalina.Context")##
$output.require("org.apache.tomcat.util.descriptor.web.ErrorPage")##
$output.require("org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer")##
$output.require("org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory")##
$output.require("org.springframework.boot.web.server.WebServerFactoryCustomizer")##
$output.require("org.springframework.context.annotation.Configuration")##

@Configuration
public class $output.currentClass implements 
    WebServerFactoryCustomizer<TomcatServletWebServerFactory> , TomcatContextCustomizer {
    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addContextCustomizers(this);
    }

    private ErrorPage createStatusErrorPage(int errorCode, String location) {
        ErrorPage errorPage = new ErrorPage();
        errorPage.setErrorCode(errorCode);
        errorPage.setLocation(location);
        return errorPage;
    }

    private ErrorPage createExceptionErrorPage() {
        ErrorPage errorPage = new ErrorPage();
        errorPage.setExceptionType("java.lang.Throwable");
        errorPage.setLocation("/error/error.faces");
        return errorPage;
    }

    @Override
    public void customize(Context context) {
//        context.addWelcomeFile("index.html");
        context.addWelcomeFile("home.faces");
        context.addErrorPage(createStatusErrorPage(404, "/error/not-found.faces"));
        context.addErrorPage(createStatusErrorPage(500, "/error/error.faces"));
        context.addErrorPage(createExceptionErrorPage());
        context.setSessionTimeout(120);
    }
}
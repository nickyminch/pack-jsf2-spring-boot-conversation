$output.java($Configuration, "ServletConfiguration")##

$output.require("java.util.EnumSet")##
$output.require("java.util.Set")##
$output.require("java.util.TreeSet")##

$output.require($WebFilter, "LogContextFilter")##
$output.require($WebFilter, "LocaleResolverRequestFilter")##

$output.require("javax.servlet.DispatcherType")##
$output.require("javax.servlet.ServletContext")##
$output.require("javax.servlet.ServletException")##
$output.require("javax.servlet.ServletRegistration")##
$output.require("javax.servlet.SessionTrackingMode")##

$output.require("org.springframework.boot.web.servlet.ServletContextInitializer")##
$output.require("org.springframework.context.annotation.Configuration")##

@Configuration
public class $output.currentClass implements ServletContextInitializer {
	@Override
	public void onStartup(ServletContext sc) throws ServletException {
        ServletRegistration.Dynamic facesServlet = sc.addServlet("facesServlet", javax.faces.webapp.FacesServlet.class);
        facesServlet.setLoadOnStartup(1);
//        facesServlet.setAsyncSupported(true);
        
        if (facesServlet != null) {
        	facesServlet.addMapping("*.faces");
        }
		sc.addListener("com.sun.faces.config.ConfigureListener");
		sc.addFilter("localeResolverRequestFilter", LocaleResolverRequestFilter.class);
		sc.addFilter("gzipResponseFilter", org.omnifaces.filter.GzipResponseFilter.class);
		sc.addFilter("javamelodyFilter", net.bull.javamelody.MonitoringFilter.class);
		sc.addFilter("logContextFilter", LogContextFilter.class);
		sc.addFilter("fileUploadFilter", org.primefaces.webapp.filter.FileUploadFilter.class);
		
		sc.getFilterRegistration("springSecurityFilterChain").addMappingForServletNames(
				EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD,
					DispatcherType.ASYNC), false, "facesServlet");
		sc.getFilterRegistration("localeResolverRequestFilter").addMappingForServletNames(
				EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD,
					DispatcherType.ASYNC), false, "facesServlet");
		sc.getFilterRegistration("gzipResponseFilter").addMappingForServletNames(
				EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD,
					DispatcherType.ASYNC), false, "facesServlet");
		sc.getFilterRegistration("logContextFilter").addMappingForServletNames(
				EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD,
					DispatcherType.ASYNC), false, "facesServlet");
		sc.getFilterRegistration("fileUploadFilter").addMappingForServletNames(
				EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD,
					DispatcherType.ASYNC), false, "facesServlet");

		sc.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());           
		sc.setInitParameter("javax.faces.CONFIG_FILES", "WEB-INF/faces-config.xml");
        sc.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        sc.setInitParameter("facelets.DEVELOPMENT", Boolean.TRUE.toString());
        sc.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
//        sc.setInitParameter("javax.faces.FACELETS_LIBRARIES", "springsecurity.taglib.xml");
//        sc.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
        sc.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/taglib/components.xml");
        sc.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
        sc.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", Boolean.TRUE.toString());
        sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        sc.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
        sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
        sc.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
        sc.setInitParameter("primefaces.THEME", "bootstrap");
        System.setProperty("org.apache.el.parser.SKIP_IDENTIFIER_CHECK", "true");
        
        sc.setSessionTimeout(10);
        Set<SessionTrackingMode> trackingModes = new TreeSet();
        trackingModes.add(SessionTrackingMode.COOKIE);
        sc.setSessionTrackingModes(trackingModes);

   }
}

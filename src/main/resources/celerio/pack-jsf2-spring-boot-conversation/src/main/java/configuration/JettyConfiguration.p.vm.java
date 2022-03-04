$output.java($Configuration, "JettyConfiguration")##

$output.require("org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory");
$output.require("org.springframework.boot.web.server.WebServerFactoryCustomizer")##
$output.require("org.springframework.stereotype.Component")##

@Component
public class $output.currentClass implements WebServerFactoryCustomizer<JettyServletWebServerFactory> {
	@Override
	public void customize(JettyServletWebServerFactory factory) {
		System.out.println("--------------------");
		factory.setPort(8181);
	}
}

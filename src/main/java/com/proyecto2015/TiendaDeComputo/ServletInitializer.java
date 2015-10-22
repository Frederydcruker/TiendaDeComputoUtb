/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto2015.TiendaDeComputo;

import java.util.HashSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author Juansa
 */
public class ServletInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext webApplicationContext = getWebContext();
        servletContext.setSessionTrackingModes(new HashSet<SessionTrackingMode>(){{
            add(SessionTrackingMode.COOKIE);
        }});
        servletContext.addListener(new ContextLoaderListener(webApplicationContext));
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("ProyectoSoftware", new DispatcherServlet(webApplicationContext));
        dispatcher.setAsyncSupported(true);
        dispatcher.setLoadOnStartup(0);
        dispatcher.addMapping("/*");
    }
    
    public WebApplicationContext getWebContext(){
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ApplicationConfigurator.class);
        return context;
    }

    
    
}

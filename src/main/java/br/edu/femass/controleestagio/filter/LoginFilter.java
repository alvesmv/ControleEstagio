/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.filter;

import br.edu.femass.controleestagio.gui.GuiLogin;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodrigo
 */
public class LoginFilter implements Filter {

    @Inject
    private GuiLogin guilogin;
   
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();
        System.out.println(url.contains("/pages"));
        System.out.println(guilogin.getUsuario());
        System.out.println("Entrou no teste ");
        if (url.contains("/pages") ) {
            System.out.println("Entrou no teste do pages");
            if (guilogin.getUsuario().getLogin()==null) {
                System.out.println("Entrou no NULL");
                res.sendRedirect(req.getServletContext().getContextPath() + "/faces/index.xhtml ");
                System.out.println(req.getServletContext().getContextPath());
                System.out.println("Não Continuou");
                return;
            }
        }
            System.out.println("Continuou");
            chain.doFilter(request, response);

        
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

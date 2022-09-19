package com.edu.hutech.major.configuration;

import com.edu.hutech.major.model.Role;
import com.edu.hutech.major.model.User;
import com.edu.hutech.major.model.UserRole;
import com.edu.hutech.major.repository.RoleRepository;
import com.edu.hutech.major.repository.UserRepository;
import com.edu.hutech.major.service.UserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleService userRoleRepository;
    
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    HttpSession session;
    
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String email = token.getPrincipal().getAttributes().get("email").toString();
        Optional<User> uOtn = userRepository.findUserByEmail(email);
        if(uOtn.isPresent()){
        	  Optional<UserRole> userRole = userRoleRepository.findByUser(uOtn.get());
        	  if(userRole.isEmpty()) {
        		 UserRole role =  userRoleRepository.add( new UserRole(uOtn.get(), new Role(2, "ROLE_USER") ) );
        		  session.setAttribute("user",  role);
        	  } else {
        		  session.setAttribute("user",  userRole.get());
        	  }
        	  
        }else {
            User user = new User();
            user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
            user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode("1"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findById(2).get());
            user.setRoles(roles);
            userRepository.save(user);
            /* Tim kiem lai user de luu vao user_role */
              Optional<User> uOtn2 = userRepository.findUserByEmail(email);
	      	  Optional<UserRole> userRole = userRoleRepository.findByUser(uOtn2.get());
	    	  if(userRole.isEmpty()) {
	    		  UserRole role =  userRoleRepository.add( new UserRole(uOtn2.get(), new Role(2, "ROLE_USER") ) );
	    		  session.setAttribute("user",  role);
	    	  }
            
        }

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
    }


}

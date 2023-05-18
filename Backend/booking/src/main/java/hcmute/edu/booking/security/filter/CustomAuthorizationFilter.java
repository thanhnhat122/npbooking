package hcmute.edu.booking.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import hcmute.edu.booking.exception.MyExceptionResonseHandler;
import hcmute.edu.booking.model.DataResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/api/login") ||
                request.getServletPath().equals("/api/User/token/refresh") ||
                request.getServletPath().equals("/api/User/signup") ||
                request.getServletPath().equals("/api/User/activation") ||
                request.getServletPath().contains("/api/Pay") && "GET".equalsIgnoreCase(request.getMethod()) ||
                //Booking
                request.getServletPath().contains("/api/Booking") && "GET".equalsIgnoreCase(request.getMethod()) ||

                //DetailBooking
                request.getServletPath().contains("/api/DetailBooking") && "GET".equalsIgnoreCase(request.getMethod()) ||

                //Hotel
                request.getServletPath().contains("/api/Hotel") && "GET".equalsIgnoreCase(request.getMethod()) ||
                request.getServletPath().equals("/api/Hotel/filter") ||

                //Image
                request.getServletPath().contains("/api/Image") && "GET".equalsIgnoreCase(request.getMethod()) ||
                request.getServletPath().equals("/api/Image/getAllImageHotel") ||
                request.getServletPath().equals("/api/Image/getAllImageHotelRoom") ||
                //Review
                request.getServletPath().contains("/api/Review") && "GET".equalsIgnoreCase(request.getMethod()) ||
                request.getServletPath().equals("/api/Review/hotel") ||
                //Room
                request.getServletPath().contains("/api/Room") && "GET".equalsIgnoreCase(request.getMethod()) ||
                request.getServletPath().equals("/api/Room/roomDTO") ||

                //Province
                request.getServletPath().contains("/api/Province") && "GET".equalsIgnoreCase(request.getMethod()) ||

                //User
                request.getServletPath().contains("/api/User") && "GET".equalsIgnoreCase(request.getMethod())) {

//					request.getServletPath().contains("/api/Author") && "GET".equalsIgnoreCase(request.getMethod()) ||
////					request.getServletPath().contains("/api/Author") && "POST".equalsIgnoreCase(request.getMethod()) ||
////					request.getServletPath().contains("/api/Author") && "PUT".equalsIgnoreCase(request.getMethod()) ||
////					request.getServletPath().contains("/api/Author") && "DELETE".equalsIgnoreCase(request.getMethod()) ||
////
//					request.getServletPath().contains("/api/Book") && "GET".equalsIgnoreCase(request.getMethod()) ||
//					request.getServletPath().equals("/api/Book/filter") ||
////					request.getServletPath().contains("/api/Book") && "POST".equalsIgnoreCase(request.getMethod()) ||
////					request.getServletPath().contains("/api/Book/uploadImage") && "POST".equalsIgnoreCase(request.getMethod()) ||
////					request.getServletPath().contains("/api/Book") && "PUT".equalsIgnoreCase(request.getMethod()) ||
////					request.getServletPath().contains("/api/Book") && "DELETE".equalsIgnoreCase(request.getMethod()) ||
//
//					request.getServletPath().contains("/api/Order") && "GET".equalsIgnoreCase(request.getMethod()) ||
//					request.getServletPath().equals("/api/Order/email") ||
//					request.getServletPath().equals("/api/Order/orderDTO") ||
//					request.getServletPath().contains("/api/OrderItem") && "GET".equalsIgnoreCase(request.getMethod()) ||
//					request.getServletPath().equals("/api/OrderItem/orderId") ||
//
//					request.getServletPath().contains("/api/Publisher") && "GET".equalsIgnoreCase(request.getMethod()) ||
////					request.getServletPath().contains("/api/Publisher") && "POST".equalsIgnoreCase(request.getMethod()) ||
////					request.getServletPath().contains("/api/Publisher") && "PUT".equalsIgnoreCase(request.getMethod()) ||
////					request.getServletPath().contains("/api/Publisher") && "DELETE".equalsIgnoreCase(request.getMethod()) ||
////
//
////					request.getServletPath().contains("/api/Review") && "POST".equalsIgnoreCase(request.getMethod()) ||
////					request.getServletPath().contains("/api/Review") && "PUT".equalsIgnoreCase(request.getMethod()) ||
////					request.getServletPath().contains("/api/Review") && "DELETE".equalsIgnoreCase(request.getMethod()) ||


            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    for (int i = 0; i < roles.length; i++)
                        authorities.add(new SimpleGrantedAuthority(roles[i]));

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    MyExceptionResonseHandler.exceptionResponseHandler(response, new DataResponse("403", "Invalid Token 1", 200), e);
                }
            } else {
                MyExceptionResonseHandler.exceptionResponseHandler(response, new DataResponse("403", "Invalid Token", 200), null);
                filterChain.doFilter(request, response);
            }
        }
    }
}

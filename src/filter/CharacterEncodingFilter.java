package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
public class CharacterEncodingFilter implements Filter {
	protected String encoding=null;
	protected FilterConfig filterConfig=null;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig=filterConfig;
		this.encoding=encoding;
	}
	@Override
	public void doFilter(ServletRequest requset, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(encoding==null){
			requset.setCharacterEncoding(encoding);
			response.setContentType("txt/html;charset="+encoding);
		}
		chain.doFilter(requset, response);
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.encoding=null;
		this.filterConfig=null;
	}

	

}

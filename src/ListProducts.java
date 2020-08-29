import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ecommerce.EProduct;

/**
 * Servlet implementation class ListProducts
 */
@WebServlet("/list")
public class ListProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			
			Session session = factory.openSession();
			
			@SuppressWarnings("unchecked")
			List<EProduct> list = session.createQuery("from EProduct").list();
			session.close();
			
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<h3>product list<h3>");
			for(EProduct p:list) {
				out.println(String.valueOf(p.getId())+","+p.getName()+","+String.valueOf(p.getPrice())+","+
							p.getDateAdded()+"<br>");
			}
			out.println("</body></html>");
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

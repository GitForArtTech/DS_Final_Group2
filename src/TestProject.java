

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**s
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		String[] sa = request.getParameter("keyword").split(" ");
		Rank.clean();
		for(int i=0;i<sa.length;i++) {
			Rank.addKeyWords(new Keyword(sa[i],sa.length-i));
		}
//		System.out.println(""+sa.length);
		String kw = request.getParameter("keyword").replaceAll(" ", "+");
		GoogleQuery google = new GoogleQuery(kw);
		ArrayList<WebTree>[] query = google.query();
		String[][] s = new String[query[0].size() + query[1].size()][2];
		int index = 0;
		for(int i = 0 ; i < query[0].size(); i++) {
			s[index][0] = query[0].get(i).root.webPage.name;
			s[index][1] = query[0].get(i).root.webPage.url;
			index++;
		}
		for(int i = 0 ; i < query[1].size(); i++) {
			s[index][0] = query[1].get(i).root.webPage.name;
			s[index][1] = query[1].get(i).root.webPage.url;
			index++;
		}
//		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
//		int num = 0;
//		for(Entry<String, String> entry : query.entrySet()) {
//		    String key = entry.getKey();
//		    String value = entry.getValue();
//		    s[num][0] = key;
//		    s[num][1] = value;
//		    num++;
//		}
		request.getRequestDispatcher("googleitem.jsp")
		 .forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

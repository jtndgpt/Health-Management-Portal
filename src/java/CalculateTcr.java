
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jitendra
 */
public class CalculateTcr extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        float bmr = Float.parseFloat(request.getParameter("bmr"));
        float exerciseLevel = Float.parseFloat(request.getParameter("exerciseLevel"));
        float tcr=0;
        tcr = (float)(bmr*exerciseLevel);
        out.print(tcr);
    }
}

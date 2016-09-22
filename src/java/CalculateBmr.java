
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
public class CalculateBmr extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        float weight = Float.parseFloat(request.getParameter("weight"));
        float height = Float.parseFloat(request.getParameter("height"));
        float age = Float.parseFloat(request.getParameter("age"));
        float bmr=0;
        String gender = (String)request.getParameter("gender");
        String female = "female";
        if(gender.equals(female)){
            bmr=(float)(655 + weight*9.66 + height*1.8 - age*4.7);
        }
        else{
            bmr=(float)(66 + weight*13.7 + height*5 - age*6.8);
        }
        out.print(bmr);
    }

}

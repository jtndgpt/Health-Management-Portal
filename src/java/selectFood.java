
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author jitendra
 */
public class selectFood extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int foodSelected = Integer.parseInt(request.getParameter("foodSelected"));
        int choice = Integer.parseInt(request.getParameter("choice"));
        
        File file = null;
        String path = request.getServletContext().getRealPath("/WEB-INF");
        file =  new File(path+File.separator+"RDA-Project-Data.xls");
        Workbook wb = null;
        try {
            wb = Workbook.getWorkbook(file);
            Sheet Food = wb.getSheet(choice);
            Cell cell = Food.getCell(3, foodSelected);
            String foodCalory = cell.getContents();
            out.print(foodCalory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}

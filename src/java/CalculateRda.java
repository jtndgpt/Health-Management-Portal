
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.json.simple.JSONObject;

/**
 *
 * @author jitendra
 */
public class CalculateRda extends HttpServlet {

    public static final double range = 0.1;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        float tcr = Float.parseFloat(request.getParameter("tcr"));
        float carbsLower = (float)(tcr*0.4);
        float carbsUpper = (float)(tcr*0.6);
        float proteinLower = (float)(tcr*0.1);
        float proteinUpper = (float)(tcr*0.35);
        float fatLower = (float)(tcr*0.2);
        float fatUpper = (float)(tcr*0.34);
        int proteinsCap, grainsCap, veggiesCap, fruitsCap, dairyCap;
        float totalCarbs, totalProtein, totalFat, totalcal;
        int proteinSelected = Integer.parseInt(request.getParameter("proteinSelected"));
        int grainSelected = Integer.parseInt(request.getParameter("grainSelected"));
        int veggeisSelected = Integer.parseInt(request.getParameter("veggeisSelected"));
        int fruitSelected = Integer.parseInt(request.getParameter("fruitSelected"));
        int dairySelected = Integer.parseInt(request.getParameter("dairySelected"));
        
        float [][]cpf = new float[5][4];
        
        File file = null;
        String path = request.getServletContext().getRealPath("/WEB-INF");
        file =  new File(path+File.separator+"RDA-Project-Data.xls");
        Workbook wb = null;
        try {
            wb = Workbook.getWorkbook(file);
        } catch (Exception e) {}
        Cell cell;
        Sheet Grains = wb.getSheet(0);
        cell = Grains.getCell(3, grainSelected);        
        cpf[1][0] = Float.parseFloat(cell.getContents());
        cell = Grains.getCell(4, grainSelected);
        cpf[1][1] = Float.parseFloat(cell.getContents())*4;
        cell = Grains.getCell(5, grainSelected);
        cpf[1][2] = Float.parseFloat(cell.getContents())*4;
        cell = Grains.getCell(6, grainSelected);
        cpf[1][3] = Float.parseFloat(cell.getContents())*9;

        
        Sheet Proteins = wb.getSheet(1);
        Cell cell1;
        cell1 = Proteins.getCell(3, proteinSelected);
        cpf[0][0] = Float.parseFloat(cell.getContents());
        cell = Proteins.getCell(4, proteinSelected);
        cpf[0][1] = Float.parseFloat(cell.getContents())*4;
        cell = Proteins.getCell(5, proteinSelected);
        cpf[0][2] = Float.parseFloat(cell.getContents())*4;
        cell = Proteins.getCell(6, proteinSelected);
        cpf[0][3] = Float.parseFloat(cell.getContents())*9;
        
        Sheet Veggies = wb.getSheet(2);
        cell = Veggies.getCell(3, veggeisSelected);
        cpf[2][0] = Float.parseFloat(cell.getContents());
        cell = Veggies.getCell(4, veggeisSelected);
        cpf[2][1] = Float.parseFloat(cell.getContents())*4;
        cell = Veggies.getCell(5, veggeisSelected);
        cpf[2][2] = Float.parseFloat(cell.getContents())*4;
        cell = Veggies.getCell(6, veggeisSelected);
        cpf[2][3] = Float.parseFloat(cell.getContents())*9;

        Sheet Fruits = wb.getSheet(3);
        cell = Fruits.getCell(3, fruitSelected);
        cpf[3][0] = Float.parseFloat(cell.getContents());
        cell = Fruits.getCell(4, fruitSelected);
        cpf[3][1] = Float.parseFloat(cell.getContents())*4;
        cell = Fruits.getCell(5, fruitSelected);
        cpf[3][2] = Float.parseFloat(cell.getContents())*4;
        cell = Fruits.getCell(6, fruitSelected);
        cpf[3][3] = Float.parseFloat(cell.getContents())*9;

        Sheet Dairy = wb.getSheet(4);
        cell = Dairy.getCell(3, dairySelected);
        cpf[4][0] = Float.parseFloat(cell.getContents());
        cell = Dairy.getCell(4, dairySelected);
        cpf[4][1] = Float.parseFloat(cell.getContents())*4;
        cell = Dairy.getCell(5, dairySelected);
        cpf[4][2] = Float.parseFloat(cell.getContents())*4;
        cell = Dairy.getCell(6, dairySelected);
        cpf[4][3] = Float.parseFloat(cell.getContents())*9;

        proteinsCap = (int)(tcr/cpf[0][0]);
        if(proteinsCap<5)
            proteinsCap = proteinsCap;
        else if(proteinsCap>=5 && proteinsCap<25)
            proteinsCap = 5;
        else if(proteinsCap>=25 && proteinsCap<50)
            proteinsCap = 6;
        else if(proteinsCap>=50 && proteinsCap<100)
            proteinsCap = 7;
        else
            proteinsCap = 8;
        
        grainsCap = (int)(tcr/cpf[1][0]);
        if(grainsCap<5)
            grainsCap = grainsCap;
        else if(grainsCap>=5 && grainsCap<25)
            grainsCap = 5;
        else if(grainsCap>=25 && grainsCap<50)
            grainsCap = 6;
        else if(grainsCap>=50 && grainsCap<100)
            grainsCap = 7;
        else
            grainsCap = 8;
        
        veggiesCap = (int)(tcr/cpf[2][0]);
        if(veggiesCap<5)
            veggiesCap = veggiesCap;
        else if(veggiesCap>=5 && veggiesCap<25)
            veggiesCap = 5;
        else if(veggiesCap>=25 && veggiesCap<50)
            veggiesCap = 6;
        else if(veggiesCap>=50 && veggiesCap<100)
            veggiesCap = 7;
        else
            veggiesCap = 8;
        
        fruitsCap = (int)(tcr/cpf[3][0]);
        if(fruitsCap<5)
            fruitsCap = fruitsCap;
        else if(fruitsCap>=5 && fruitsCap<25)
            fruitsCap = 5;
        else if(fruitsCap>=25 && fruitsCap<50)
            fruitsCap = 6;
        else if(fruitsCap>=50 && fruitsCap<100)
            fruitsCap = 7;
        else
            fruitsCap = 8;
        
        dairyCap = (int)(tcr/cpf[4][0]);
        if(dairyCap<5)
            dairyCap = dairyCap;
        else if(dairyCap>=5 && dairyCap<25)
            dairyCap = 5;
        else if(dairyCap>=25 && dairyCap<50)
            dairyCap = 6;
        else if(dairyCap>=50 && dairyCap<100)
            dairyCap = 7;
        else
            dairyCap = 8;
        
        int startRange = 0;
        float sumProduct = 0;
        for(int  i =0;i<5;i++){
            sumProduct += cpf[i][0];
        }
        if(tcr>sumProduct){
            startRange = 1;
        }
        
        List<Solutions> list = new ArrayList<Solutions>();
        for(int a=startRange; a<=proteinsCap; a++){
            for(int b=startRange; b<=grainsCap; b++){
                for(int c=startRange; c<=veggiesCap; c++){
                    for(int d=startRange; d<=fruitsCap; d++){
                        for(int e=startRange; e<=dairyCap; e++){
                            totalCarbs=(cpf[0][1]*a)+(cpf[1][1]*b)+(cpf[2][1]*c)+(cpf[3][1]*d)+(cpf[4][1]*e);
                            totalProtein=(cpf[0][2]*a)+(cpf[1][2]*b)+(cpf[2][2]*c)+(cpf[3][2]*d)+(cpf[4][2]*e);
                            totalFat=(cpf[0][3]*a)+(cpf[1][3]*b)+(cpf[2][3]*c)+(cpf[3][3]*d)+(cpf[4][3]*e);
                            totalcal=totalCarbs+totalProtein+totalFat;
                            if(totalcal>=(tcr-tcr*range) && totalcal<=(tcr+tcr*range) && totalCarbs>=carbsLower && totalCarbs<=carbsUpper && totalProtein>=proteinLower && totalProtein<=proteinUpper && totalFat>=fatLower && totalFat<=fatUpper){
                                Solutions solution = new Solutions();
                                solution.setA(a);
                                solution.setB(b);
                                solution.setC(c);
                                solution.setD(d);
                                solution.setE(e);
                                solution.setTotal(totalcal);
                                list.add(solution);
                            }
                        }
                    }
                }
            }
        }
        if(!list.isEmpty()){
            List<Solutions> sd = new ArrayList<Solutions>();
            Iterator itr = list.iterator();
            for(int i = 8; i>0;){
                float mean, std ,stdroot ;
                while(itr.hasNext()){
                    Solutions s = (Solutions)itr.next();
                    if(s.getTotal()>=(tcr-tcr*(range/i)) && (s.getTotal()<=(tcr+tcr*(range/i)))){
                        mean = (float)(s.getA()+s.getB()+s.getC()+s.getD()+s.getE())/5;
                        std = ((float)(Math.pow((s.getA()-mean),2))+(float)(Math.pow((s.getB()-mean),2))+(float)(Math.pow((s.getC()-mean),2))+(float)(Math.pow((s.getD()-mean),2))+(float)(Math.pow((s.getE()-mean),2)))/5;
                        stdroot =  (float)Math.sqrt(std);
                        s.setStandardDeviation(stdroot);
                        sd.add(s);
                    }                   
                }
                if(!sd.isEmpty()){
                    Solutions result = null;
                    boolean flag =true;
                    Iterator<Solutions> it = sd.iterator();
                    while(it.hasNext()){
                        Solutions s = it.next();
                        if(flag){
                            result = s;
                            flag = false;
                        }else{
                            if(s.getStandardDeviation()<result.getStandardDeviation()){
                                result = s;
                            }
                        }
                    }
                    JSONObject jo = new JSONObject();
                    jo.put("protein", result.getA()+" Unit");
                    jo.put("grains", result.getB()+" Unit");
                    jo.put("veggies", result.getC()+" Unit");
                    jo.put("fruits", result.getD()+" Unit");
                    jo.put("dairy", result.getE()+" Unit");
                    jo.put("status", true);
                    out.print(jo);
                    break;
                }
                i = i/2;
            }
        }
        else{
            JSONObject jo = new JSONObject();
            jo.put("status", false);
            jo.put("err_msg", "Unable to find a solution within the given range limits");
            out.print(jo);
        }
    }
}

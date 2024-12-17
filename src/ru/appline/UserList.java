package ru.appline;

import ru.appline.logic.Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/get" )
public class UserList extends HttpServlet {
    Model model= Model.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        StringBuffer jb = new StringBuffer();
        String line;
        try{
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8;");
        PrintWriter pw=response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));

        if (id==0){
            pw.print(gson.toJson(model.getFromList()));
        }
        else if (id > model.getFromList().size()){
                pw.print("Пользователь не найден");
            }
        else if (id <= model.getFromList().size() && id>0) {
                pw.print(gson.toJson(model.getFromList().get(id)));
            }
        else  {
                pw.print("Ошибка");
            }

    }
}

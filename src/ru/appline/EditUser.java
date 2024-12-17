package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/edit")
public class EditUser extends HttpServlet {
    Model model = Model.getInstance();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

        request.setCharacterEncoding("UTF-8");

        int id = jobj.get("id").getAsInt();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        if ((id < 1) || (id > model.getFromList().size())){
            pw.print("Пользователь не найден");
        } else {
            String name = jobj.get("name").getAsString();
            String surname = jobj.get("surname").getAsString();
            double salary = jobj.get("salary").getAsDouble();

            User user = new User(name, surname, salary);
            user.setName(name);
            user.setSurname(surname);
            user.setSalary(salary);
            model.add(user, id);
        }
    }


}

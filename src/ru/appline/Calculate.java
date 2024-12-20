package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/calc")
public class Calculate extends HttpServlet {

    private double result;

    public Calculate() {

    }

    Map<String, Double> calc=new HashMap<>();

    public Calculate(double result) {
        this.result = result;
    }

    public double getResult(double result) {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

        request.setCharacterEncoding("UTF-8");

        double a = jobj.get("a").getAsInt();
        double b = jobj.get("b").getAsInt();

        String math= jobj.get("math").getAsString();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        boolean isError = false;
        switch (math) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    pw.print("На 0 делить нельзя");
                    isError = true;
                } else {
                    result = a / b;
                }
                break;
            default:
                pw.print("Неправильная операция");
                isError = true;
                break;
        }
        if (!isError) {
            calc.put("result", result);
            pw.print(gson.toJson(calc));
        }
    }
}
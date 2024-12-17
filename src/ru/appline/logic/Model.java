package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance=new Model();
    private final Map <Integer, User> model;

    public static Model getInstance(){
        return instance;
    }

    private Model(){
        model=new HashMap<>();
        model.put(1, new User("Peter","Ivanov",50000));
        model.put(2, new User("Vasily","Smirnov",80000));
        model.put(3, new User("Maria","Kuznetsova",120000));
        model.put(4, new User("Egor","Nikolaev",75000));
    }

    public void add(User user, int id){
        model.put(id,user);
    }
    public void del(int id){
        model.remove(id);
    }

    public Map<Integer, User> getFromList(){
        return model;
    }

}

package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Repository <T> {

    private Map<String , T> data = new HashMap<String, T>();

    //TODO dodac obsluge co jesli jest juz key albo nie ma tutaj lub w service :)

    public void add (String key, T value){
        data.put(key, value);
    }

    public T get(String key){
        return data.get(key);
    }

    public void delete(String key){
        data.remove(key);
    }

    public List<T> getAll(){
        return (List<T>)data.values();
    }
}

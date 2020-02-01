package pl.bialekkostrzewa.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RepositoryTemplate<T> {

    private Map<String , T> data = new HashMap<String, T>();

    public T add (String key, T value){
        return data.putIfAbsent(key, value);
    }

    public T get(String key){
        return data.get(key);
    }

    public void update(String key, T value){
        data.replace(key, value);
    }

    public void delete(String key){
        data.remove(key);
    }

    public List<T> getAll(){
        return new ArrayList<>(data.values());
    }
}

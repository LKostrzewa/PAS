package service;

import model.Showing;
import repository.ShowingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.LongUnaryOperator;

public class ShowingService {

    private ShowingRepository showings = new ShowingRepository();

    public void createShowing(String id, String name, int ageRestriction, int room, int hour, int freeTickets, double price) throws Exception {
        Optional<Showing> opt = showings.getAll().stream()
                .filter(s -> room == s.getRoom() && hour == s.getHour()).findFirst();
        if(opt.isEmpty()) showings.add(id, new Showing(id, name, ageRestriction, room, hour, freeTickets, price));
        else throw new Exception("no nie mozna"); //tutaj to lepiej sie zrobi mozna wyjatyki wlasne dac //TODO dac wyjatki
    }

    public void endShowing(String id){
        showings.delete(id);
    }

    public List<Showing> getAllShowings(){
        return showings.getAll();
    }

    public Showing getShowing(String id) {
        return showings.get(id);
    }

    public List<Showing> showingsForAge(int age) throws Exception{
        List<Showing> result = new ArrayList<>();
        for (Showing s : showings.getAll()) {
            if(s.getAgeRestriction() <= age)
                result.add(s);
        }
        /*Optional<Showing> opt = showings.getAll().stream()
                .filter(s -> s.getAgeRestriction() <= age).findAny();
        opt.ifPresent(result::add);*/
        if(!result.isEmpty()) return result;
        else throw new Exception("no ni ma seansuf"); //tutaj to lepiej sie zrobi mozna wyjatyki wlasne dac //TODO dac wyjatki
    }
}

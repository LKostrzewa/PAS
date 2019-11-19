package pl.bialekkostrzewa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.bialekkostrzewa.model.Table;

import static org.junit.jupiter.api.Assertions.*;

class ResourceRepositoryTest {

    @Test
    void addToRepoTest(){

        ResourceRepository rr = new ResourceRepository();

        Table t1 = new Table("1",234,12,4);
        rr.add("1", t1);

        Table t2 = new Table("1", 451, 12, 2);
        rr.add("1", t2);

        Assertions.assertEquals(rr.get("1"), t1);

        Table t3 = new Table("2", 651, 14, 2);
        rr.add("2", t3);

        Assertions.assertEquals(rr.get("2"), t3);

        //System.out.println(rr.getAll());
    }

    @Test
    void updateTest(){

        ResourceRepository rr = new ResourceRepository();

        Table t1 = new Table("1",234,12,4);
        rr.add("1", t1);

        rr.update("2", new Table("3", 42, 12, 4));

        Assertions.assertNull(rr.get("2"));

        Table t2 = new Table("1", 512, 14, 4);
        rr.update("1", t2);

        Assertions.assertEquals(rr.get("1"), t2);

        //Przy update trzeba uwazac zeby nie dopuscic do sytuacji gdy Table.id != id w mapie
        rr.update("1", new Table("3", 125,1,2));
        System.out.println(rr.get("1").getId());
    }

}
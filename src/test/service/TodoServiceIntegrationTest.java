package service;

import com.example.sweater.model.Todo;
import com.example.sweater.service.TodoService;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.List;

public class TodoServiceIntegrationTest {

    @InjectMocks
    private TodoService service;

    //@Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

  //  @Test
    public void test() {


        List<Todo> list = service.findAll();

        Assert.notNull(list, "lista este goala");


        // si tot asa mai departe
        // aici daca metoda nu returneaza nimic testezi ceia ce a fost apelat in ea
        // mai pe scurt o sa-ti arat cind o sa vii
        //acolo transmiti ce trebuie sa fie la iesire si ce pui la intrare?
        // da, o sa vezi


        // la integration testezi ceia ce returneaza
        //assertion here
    }
}

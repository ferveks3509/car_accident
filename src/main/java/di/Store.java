package di;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Store {
    private List<String> data = new ArrayList<>();

    public void add(String val) {
        data.add(val);
    }
    public List<String> getAll() {
        return data;
    }
}

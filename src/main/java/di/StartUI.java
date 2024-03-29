package di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StartUI {
    @Autowired
    private Store store;

    public void add(String val) {
        store.add(val);
    }
    public void print() {
        for (String el: store.getAll()) {
            System.out.println(el);
        }
    }
}

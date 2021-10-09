package di;

import org.springframework.stereotype.Component;

@Component
public class StartUI {
    private Store store;

    public StartUI(Store store) {
        this.store = store;
    }
    public void add(String val) {
        store.add(val);
    }
    public void print() {
        for (String el: store.getAll()) {
            System.out.println(el);
        }
    }
}

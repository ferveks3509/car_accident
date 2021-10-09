package di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDi {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("di");
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ui.add("One");
        ui.add("two");
        ui.add("three");
        ui.print();
    }
}

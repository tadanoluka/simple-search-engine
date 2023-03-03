package search;

import java.util.List;

public class Person {
    private static int counter = 0;

    private String name;
    private String surname;
    private String email;
    private final int index;

    public Person(String[] info) {
        index = counter;
        counter++;

        switch (info.length) {
            case 3 -> {
                this.name = info[0];
                this.surname = info[1];
                this.email = info[2];
            }
            case 2 -> {
                this.name = info[0];
                this.surname = info[1];
                this.email = "";
            }
            case 1 -> {
                this.name = info[0];
                this.surname = "";
                this.email = "";
            }
            default -> System.out.println("Person init error");
        }
    }

    public String getAllInfoString() {
        if (!email.equals("")) {
            return name + " " + surname + " " + email;
        } else if (!surname.equals("")) {
            return name + " " + surname;
        } else {
            return name;
        }
    }

    public List<String> getAllInfoList() {
        if (!email.equals("")) {
            return List.of(name, surname, email);
        } else if (!surname.equals("")) {
            return List.of(name, surname);
        } else {
            return List.of(name);
        }
    }

    public int getIndex() {
        return index;
    }
}

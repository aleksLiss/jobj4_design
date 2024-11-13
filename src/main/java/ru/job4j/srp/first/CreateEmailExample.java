package ru.job4j.srp.first;

public class CreateEmailExample {

    private String domain;
    private String firstName;
    private String secondName;

    public CreateEmailExample(String domain, String firstName, String secondName) {
        this.domain = domain;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    /**
     * Нарушение принципа SRP, так как
     * класс в данном случае должен служить только для создания
     * шаблона e-mail адреса, а он содержит метод
     * для проверки корректности получившегося e-mail-а.
     *
     * @param example
     * @return true if email is correct or false if isn't correct.
     */
    public boolean isCorrectEmail(CreateEmailExample example) {
        if (!example.domain.equals("ru") || !example.domain.contains("com")) {
            return false;
        }
        if (firstName.endsWith("@") || firstName.startsWith("@")) {
            return false;
        }
        if (secondName.endsWith(".")) {
            return false;
        }
        return true;
    }
}

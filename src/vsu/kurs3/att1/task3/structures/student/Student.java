package vsu.kurs3.att1.task3.structures.student;

public class Student {

    private final String name;
    private final String surname;
    private MarksOperator marksOperator;

    @Override
    public String toString() {return name + ' ' + surname; }

    public Student(String name, String surname){
        this.name = name;
        this.surname = surname;
        marksOperator = new MarksOperator();
    }

    public MarksOperator getMarksOperator() {
        return marksOperator;
    }
}
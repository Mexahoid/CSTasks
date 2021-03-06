package vsu.kurs3.task3.hibNormal.models.dto;

import java.util.LinkedList;
import java.util.List;

public class GroupDTO {

    private long id;

    private long number;

    private CourseDTO course;

    private List<StudentDTO> students;

    public GroupDTO(){ students = new LinkedList<>(); }


    public long getId() { return id; }

    public CourseDTO getCourse() { return course; }

    public long getNumber() { return number; }

    public List<StudentDTO> getStudents() { return students; }


    public void setId(long id) { this.id = id; }

    public void setCourse(CourseDTO course) { this.course = course; }

    public void setNumber(long number) { this.number = number; }

    public void setStudents(List<StudentDTO> students) { this.students = students; }

    public void addStudent(StudentDTO student){
        students.add(student);
    }

    public void deleteStudent(StudentDTO student){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getName().equals(student.getName()) &&
               students.get(i).getSurname().equals(student.getSurname()))
            {
                students.remove(i);
                return;
            }
        }
    }

}

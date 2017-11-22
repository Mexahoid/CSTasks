package vsu.kurs3.att1.task3.hibNormal.models.converters;

import vsu.kurs3.att1.task3.hibNormal.models.dto.GroupDTO;
import vsu.kurs3.att1.task3.hibNormal.models.dto.StudentDTO;
import vsu.kurs3.att1.task3.hibNormal.models.entities.Group;
import vsu.kurs3.att1.task3.hibNormal.models.entities.Student;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;


public class GroupConverter {
    public static GroupDTO convertToDTO(Group group){
        GroupDTO grDto = new GroupDTO();
        grDto.setNumber(group.getNumber());
        Iterator iter = group.getStudents().iterator();
        Set<StudentDTO> set = new LinkedHashSet<>();
        while(iter.hasNext())
        {
            set.add(StudentConverter.convertToDTO((Student)iter.next()));
        }
        grDto.setStudents(set);
        return grDto;
    }

    public static Group convertToEntity(GroupDTO group){
        Group gr = new Group();
        gr.setNumber(group.getNumber());
        Iterator iter = group.getStudents().iterator();
        Set<Student> set = new LinkedHashSet<>();
        while(iter.hasNext())
        {
            set.add(StudentConverter.convertToEntity((StudentDTO)iter.next()));
        }
        gr.setStudents(set);
        return gr;
    }
}

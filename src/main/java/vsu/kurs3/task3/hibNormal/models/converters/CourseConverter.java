package vsu.kurs3.task3.hibNormal.models.converters;

import vsu.kurs3.task3.hibNormal.models.dto.CourseDTO;
import vsu.kurs3.task3.hibNormal.models.dto.GroupDTO;
import vsu.kurs3.task3.hibNormal.models.entities.Course;
import vsu.kurs3.task3.hibNormal.models.entities.Group;

import java.util.*;

public class CourseConverter {
    public static CourseDTO convertToDTO(Course course){
        CourseDTO coDto = new CourseDTO();
        if(course == null)
            return coDto;
        coDto.setId(course.getId());
        coDto.setNumber(course.getCoursenum());
        List<Group> dts = course.getGroups();
        if(dts == null)
            return coDto;
        Iterator iter = dts.iterator();
        List<GroupDTO> set = new LinkedList<>();
        while(iter.hasNext())
        {
            GroupDTO grp = GroupConverter.convertToDTO((Group)iter.next());
            grp.setCourse(coDto);
            set.add(grp);
        }
        coDto.setGroups(set);
        return coDto;
    }

    public static Course convertToEntity(CourseDTO course){
        Course co = new Course();
        if(course == null)
            return co;
        co.setCoursenum(course.getNumber());
        co.setId(course.getId());
        List<GroupDTO> dts = course.getGroups();
        if(dts == null)
            return co;
        Iterator iter = dts.iterator();
        List<Group> set = new LinkedList<>();
        while(iter.hasNext())
        {
            Group grp = GroupConverter.convertToEntity((GroupDTO)iter.next());
            grp.setCourse(co);
            set.add(grp);
        }
        co.setGroups(set);
        return co;
    }
}

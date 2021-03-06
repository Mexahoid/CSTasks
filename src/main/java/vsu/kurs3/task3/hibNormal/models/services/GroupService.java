package vsu.kurs3.task3.hibNormal.models.services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vsu.kurs3.task3.hibNormal.models.converters.GroupConverter;
import vsu.kurs3.task3.hibNormal.models.dto.CourseDTO;
import vsu.kurs3.task3.hibNormal.models.dto.GroupDTO;
import vsu.kurs3.task3.hibNormal.models.dto.StudentDTO;
import vsu.kurs3.task3.hibNormal.models.entities.Group;
import vsu.kurs3.task3.hibNormal.models.entities.Student;
import vsu.kurs3.task3.hibNormal.models.repositories.GroupRepository;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository repository;
    private int count;

    public GroupService(GroupRepository repository) { this.repository = repository; }

    @Transactional
    public GroupDTO add(GroupDTO group) {
        Group grp = GroupConverter.convertToEntity(group);
        List<Student> stds = grp.getStudents();
        if(stds != null)
            for(Student st : stds)
                st.setGroup(grp);
        grp = repository.save(grp);
        return GroupConverter.convertToDTO(grp);
    }

    @Transactional
    public GroupDTO edit(GroupDTO group){
       /* delete(group);
        add(group);
        return get(group.getId());*/

        GroupDTO oldGroup = get(group.getId());
        if(oldGroup != null){
            Group grp = GroupConverter.convertToEntity(group);
            List<Student> stds = grp.getStudents();
            if(stds != null)
                for(Student st : stds)
                    st.setGroup(grp);
            grp.setCourse(repository.findOne(group.getId()).getCourse());
            return GroupConverter.convertToDTO(repository.save(grp));
        }
        return null;
    }

    @Transactional
    public void delete(long id) { repository.delete(id); }



    public void delete(GroupDTO group){
        delete(group.getId());
    }

    public GroupDTO get(long id) {
        GroupDTO group = GroupConverter.convertToDTO(repository.findOne(id));
        if (group != null)
            return group;
        else
            return null;
    }

    // Не удалять, трясется в контроллере
    public Iterable<GroupDTO> getAll() {
        count = 0;
        Iterable<Group> groups = repository.findAllByOrderByNumberAsc();
        List<GroupDTO> grs = new LinkedList<>();
        for (Group gr: groups) {
            grs.add(GroupConverter.convertToDTO(gr));
            count++;
        }
        grs.sort(Comparator.comparingLong(GroupDTO::getNumber));
        return grs;
    }

    public GroupDTO getFromCourseByNum(long courseId, long number){
        return GroupConverter.convertToDTO(repository.findByCourse_IdAndNumber(courseId, number));
    }


    public GroupDTO getByName(String name, long courseId){
        long num = Long.parseLong(name.replaceAll("[\\D]", ""));
        return getFromCourseByNum(courseId, num);
    }

    public Iterable<GroupDTO> getAllGroupsFromCourseById(long courseId){
        count = 0;
        Iterable<Group> groups = repository.findGroupsByCourse_IdOrderByNumber(courseId);
        List<GroupDTO> grs = new LinkedList<>();
        for (Group gr: groups) {
            grs.add(GroupConverter.convertToDTO(gr));
            count++;
        }
        grs.sort(Comparator.comparingLong(GroupDTO::getNumber));
        return grs;
    }

    public int getCount(){
        return count;
    }

    public List<String> getStudentsByCourseAndGroup(CourseDTO course, String group){
        List<String> lst = new LinkedList<>();
        long num = Long.parseLong(group.replaceAll("[\\D]", ""));
        GroupDTO grp = getFromCourseByNum(course.getId(), num);
        Iterable<StudentDTO> iter = grp.getStudents();
        if(iter != null){
            for (StudentDTO std : iter)
                lst.add(std.getName() + ' ' + std.getSurname());
        }
        return lst;
    }


    public List<String> getGroups(CourseDTO course){
        List<String> lst = new LinkedList<>();

        Iterable<GroupDTO> groups = getAllGroupsFromCourseById(course.getId());
        if(groups != null)
            for(GroupDTO gr: groups)
                lst.add(gr.getNumber() + " группа.");
        return lst;
    }

}

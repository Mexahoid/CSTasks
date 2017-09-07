package com.company;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Course {

    private List<Group> _groups;
    public final int CourseNum;
    private int _groupCount;

    public Course(int num)
    {
        _groups = new LinkedList<>();
        CourseNum = num;
    }

    public void CreateGroup()
    {
        _groups.add(new Group(++_groupCount, CourseNum));
    }

    public void GetGroupsList()
    {
        IntStream.range(1, _groups.size()).forEach(
                num -> System.out.println("    " + _groups.get(num - 1).Number + " группа.")
        );
    }

    public Group SelectGroup(int num) {
        int ptr = 0;
        while (_groups.get(ptr).Number != num)
            ptr++;
        return _groups.get(ptr);
    }

    public void DeleteGroup(int num)
    {
        IntStream.range(1, _groups.size()).forEach(
                nbr -> {
                    if(_groups.get(nbr - 1).Number == num) {
                        _groups.remove(nbr - 1);
                        return;
                    }
                }
        );
        System.out.println("Такой группы нет.");
    }
}
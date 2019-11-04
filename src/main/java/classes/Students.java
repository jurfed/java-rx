package classes;

import java.util.Objects;

public class Students {
    private String name;
    private int year;
    private String speciality;

    public Students(String name, int year, String speciality) {
        this.name = name;
        this.year = year;
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", speciality='" + speciality + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return year == students.year &&
                Objects.equals(name, students.name) &&
                Objects.equals(speciality, students.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, speciality);
    }
}

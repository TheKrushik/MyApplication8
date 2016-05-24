package info.krushik.android.myapplication8;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("FirstName")
    public String FirstName;

    @SerializedName("LastName")
    public String LastName;

    @SerializedName("Age")
    public int Age;

    public Student(String firstName, String lastName, int age) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
    }


}

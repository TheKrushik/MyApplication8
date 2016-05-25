package info.krushik.android.myapplication8;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Student")
public class Student {

    @Element(name="FirstName")
    @SerializedName("FirstName")
    public String FirstName;

    @Element(name="LastName")
    @SerializedName("LastName")
    public String LastName;

    @Element(name="Age")
    @SerializedName("Age")
    public int Age;

    public Student(String firstName, String lastName, int age) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
    }


}

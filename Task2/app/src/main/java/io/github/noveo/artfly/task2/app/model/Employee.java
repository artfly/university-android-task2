package io.github.noveo.artfly.task2.app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arty on 23.02.16.
 */
public class Employee implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
    public String name;
    public String surname;
    public String skills;

    public Employee(String name, String surname, String skills) {
        this.name = name;
        this.surname = surname;
        this.skills = skills;
    }

    public Employee(Parcel in) {
        String[] data = new String[3];

        in.readStringArray(data);
        this.name = data[0];
        this.surname = data[1];
        this.skills = data[2];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeStringArray(new String[]{this.name,
                this.surname,
                this.skills});
    }


}

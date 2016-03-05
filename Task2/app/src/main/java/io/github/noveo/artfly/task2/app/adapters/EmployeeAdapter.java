package io.github.noveo.artfly.task2.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.noveo.artfly.task2.app.R;
import io.github.noveo.artfly.task2.app.model.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    private ButtonListener listener;

    public EmployeeAdapter(Context context, ArrayList<Employee> employees, ButtonListener listener) {
        super(context, 0, employees);
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Employee employee = getItem(position);
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_employee, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.nameView.setText(employee.name);
        holder.surnameView.setText(employee.surname);
        holder.skillsView.setText(employee.skills);
        holder.editButton.setTag(getPosition(employee));
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                listener.onButtonClick(getItem(position).skills, position);
            }
        });

        return convertView;
    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            employees.add((Employee) getItem(i));
        }
        return employees;
    }

    public interface ButtonListener {
        void onButtonClick(String skills, int position);
    }

    static class ViewHolder {
        @Bind(R.id.name)
        TextView nameView;
        @Bind(R.id.surname)
        TextView surnameView;
        @Bind(R.id.skills)
        TextView skillsView;
        @Bind(R.id.edit_button)
        Button editButton;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

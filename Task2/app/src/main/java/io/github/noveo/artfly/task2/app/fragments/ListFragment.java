package io.github.noveo.artfly.task2.app.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.noveo.artfly.task2.app.model.Employee;
import io.github.noveo.artfly.task2.app.adapters.EmployeeAdapter;
import io.github.noveo.artfly.task2.app.R;

import java.util.ArrayList;

public class ListFragment extends Fragment implements EmployeeAdapter.ButtonListener, EditDialogFragment.EditSkillsListener {
    private final static int REQUEST_CODE = 1;
    private final static String EDIT_TAG = "EDIT_TAG";
    private final static String EMPLOYEES_KEY = "EMPLOYEES_KEY";
    @Bind(R.id.listview_employees)
    ListView employeesView;
    private EmployeeAdapter adapter;
    private ArrayList<Employee> employeeList;

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onButtonClick(String skills, int position) {
        FragmentManager fm = getFragmentManager();
        EditDialogFragment fragment = EditDialogFragment.newInstance(skills, position);
        fragment.setTargetFragment(this, REQUEST_CODE);
        fragment.show(fm, EDIT_TAG);
    }

    @Override
    public void onFinishEdit(String skills, int position) {
        employeeList.get(position).skills = skills;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            employeeList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                employeeList.add(new Employee("Name" + i, "Surname" + i,
                        "Long line with skills of employee" + i));
            }
        } else {
            employeeList = savedInstanceState.getParcelableArrayList(EMPLOYEES_KEY);
        }
        adapter = new EmployeeAdapter(getActivity(), employeeList, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        employeeList = adapter.getEmployees();
        savedState.putParcelableArrayList(EMPLOYEES_KEY, employeeList);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        try {
            AdapterView.OnItemClickListener listener = ((ListFragmentListener) getActivity()).getItemListener();
            if (listener != null) {
                employeesView.setOnItemClickListener(listener);
            } else {
                final View headerView = getActivity().getLayoutInflater().inflate(R.layout.detail_info,
                        employeesView, false);
                employeesView.addHeaderView(headerView);
                employeesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Employee employee = (Employee) employeesView.getItemAtPosition(position);
                        if (employee != null) {
                            ((TextView) headerView.findViewById(R.id.detail_name)).setText(employee.name);
                            ((TextView) headerView.findViewById(R.id.detail_surname)).setText(employee.surname);
                            ((TextView) headerView.findViewById(R.id.detail_skills)).setText(employee.skills);
                        }
                    }
                });
            }
            employeesView.setAdapter(adapter);
        } catch (ClassCastException e) {
            Log.d(getClass().getSimpleName(), "MainActivity should implement ListFragmentListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public interface ListFragmentListener {
        AdapterView.OnItemClickListener getItemListener();
    }

}

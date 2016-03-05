package io.github.noveo.artfly.task2.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.noveo.artfly.task2.app.model.Employee;
import io.github.noveo.artfly.task2.app.R;

public class DetailFragment extends Fragment implements AdapterView.OnItemClickListener {

    @Bind(R.id.detail_name)
    TextView nameView;
    @Bind(R.id.detail_surname)
    TextView surnameView;
    @Bind(R.id.detail_skills)
    TextView skillsView;

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        Bundle params = new Bundle();
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Employee emp = (Employee) adapterView.getItemAtPosition(position);
        if (emp != null) {
            nameView.setText(emp.name);
            surnameView.setText(emp.surname);
            skillsView.setText(emp.skills);
        }
    }
}

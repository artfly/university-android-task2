package io.github.noveo.artfly.task2.app.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.noveo.artfly.task2.app.R;

public class EditDialogFragment extends DialogFragment {
    private static final String SKILLS_KEY = "SKILLS_KEY";
    private static final String POSITION_KEY = "POSITION_KEY";
    @Bind(R.id.edit_skills)
    EditText editText;

    public EditDialogFragment() {
    }

    public static EditDialogFragment newInstance(String skills, int position) {
        EditDialogFragment fragment = new EditDialogFragment();
        Bundle args = new Bundle();
        args.putString(SKILLS_KEY, skills);
        args.putInt(POSITION_KEY, position);
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.ok_button)
    public void sendResult() {
        EditSkillsListener listener = (EditSkillsListener) getTargetFragment();
        if (listener != null)
            listener.onFinishEdit(editText.getText().toString(), getArguments().getInt(POSITION_KEY));
        dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_edit, container, false);
        ButterKnife.bind(this, v);
        editText.setText(getArguments().getString(SKILLS_KEY));
        getDialog().setTitle(R.string.edit_title);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public interface EditSkillsListener {
        void onFinishEdit(String skills, int position);
    }
}

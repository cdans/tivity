package com.example.tivity.ui.todos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tivity.R;

public class TodosFragment extends Fragment {

    private TodosViewModel todosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        todosViewModel =
                ViewModelProviders.of(this).get(TodosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_todos, container, false);
        final TextView textView = root.findViewById(R.id.text_todos);
        todosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
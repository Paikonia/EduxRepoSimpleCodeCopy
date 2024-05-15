package com.example.edux.fragments.setupfragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.edux.MainPageActivity;
import com.example.edux.R;
import com.example.edux.ViewModels.signup.SigninViewModel;

public class Login_fragment extends Fragment {

    EditText userEmail, password;
    TextView loginButton, setup, needHelp;
    View v;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    SigninViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.login_layout,container, false);

        userEmail = v.findViewById(R.id.login_email);
        password = v.findViewById(R.id.login_password);
        needHelp = v.findViewById(R.id.login_need_help);
        loginButton = v.findViewById(R.id.login_button);
        setup = v.findViewById(R.id.login_account);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(SigninViewModel.class);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setup.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.setup_fragment_container, new Setup_fragment()).commit();
        });

        needHelp.setOnClickListener(v->{
            Toast.makeText(getContext(), "This functionality has not yet been implemented in code", Toast.LENGTH_SHORT).show();
        });

        loginButton.setOnClickListener(v -> {

            Intent in = new Intent(getContext(), MainPageActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);

        });
    }
}

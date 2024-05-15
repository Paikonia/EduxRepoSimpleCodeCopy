package com.example.edux.fragments.setupfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.edux.R;


public class Setup_fragment extends Fragment {

    EditText name, email, password, confirmPassword;
    TextView setupButton, haveAnAccount;

    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.setup_layout, container, false);
        name = v.findViewById(R.id.setup_name);
        email = v.findViewById(R.id.setup_email);
        password = v.findViewById(R.id.setup_password);
        confirmPassword = v.findViewById(R.id.setup_confirm_password);

        setupButton = v.findViewById(R.id.setup_signup);
        haveAnAccount = v.findViewById(R.id.setup_signin);



        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        haveAnAccount.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.setup_fragment_container, new Login_fragment())
                    .commit();
        });
    }
}

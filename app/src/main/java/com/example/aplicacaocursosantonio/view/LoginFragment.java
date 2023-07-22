package com.example.aplicacaocursosantonio.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aplicacaocursosantonio.R;
import com.example.aplicacaocursosantonio.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //verificando se o usuario informou os dados
                if (!binding.etLoginUsuario.getText().toString().equals("")){
                    if (!binding.etSenha.getText().toString().equals("")){
                        //obtendo as informações
                        String usuario = binding.etLoginUsuario.getText().toString();
                        String senha = binding.etSenha.getText().toString();

                        //verificando se o usuario e senha estão corretos, "simulação"
                        if (usuario.equals("antonio") && senha.equals("1234")){
                        //utilizando o navigation e informando como argumento a ação criada anteriormente
                            Navigation.findNavController(view).navigate(R.id.acao_loginFragment_menuFragment);
                        } else {
                            Toast.makeText(getContext(), "Erro: usuário e/ou senha inválidos.", Toast.LENGTH_SHORT).show();
                        }

                    } else{
                        binding.etSenha.setError("Informe a senha.");
                        binding.etSenha.requestFocus();
                    }
                } else{
                    binding.etLoginUsuario.setError("Informe o usuário.");
                    binding.etLoginUsuario.requestFocus();
                }
            }
        });
        binding.btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });
    }

    public void limpaCampos(){
        binding.etLoginUsuario.setText("");
        binding.etSenha.setText("");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
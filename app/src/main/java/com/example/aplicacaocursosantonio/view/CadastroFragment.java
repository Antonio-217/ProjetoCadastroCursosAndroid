package com.example.aplicacaocursosantonio.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aplicacaocursosantonio.R;
import com.example.aplicacaocursosantonio.databinding.FragmentCadastroBinding;
import com.example.aplicacaocursosantonio.modelDominio.Curso;
import com.example.aplicacaocursosantonio.viewModel.InformacoesViewModel;

public class CadastroFragment extends Fragment {

    FragmentCadastroBinding binding;
    InformacoesViewModel informacoesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCadastroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        informacoesViewModel = new ViewModelProvider(getActivity()).get(InformacoesViewModel.class);

        binding.btCadastroSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verificando se o usuario digitou os dados
                if (!binding.etCadastroNome.getText().toString().equals("")){
                    if (!binding.etCadastroCargaHoraria.getText().toString().equals("")){
                        if (!binding.etCadastroConceito.getText().toString().equals("")){
                            if (binding.rbCadastroTecnologo.isChecked() || binding.rbCadastroBacharelado.isChecked() || binding.rbCadastroLicenciatura.isChecked()){
                                //obtendo as informações
                                String nome = binding.etCadastroNome.getText().toString();
                                float cargaHoraria = Float.parseFloat(binding.etCadastroCargaHoraria.getText().toString());
                                int conceito = Integer.parseInt(binding.etCadastroConceito.getText().toString());

                                int tipo;
                                if (binding.rbCadastroTecnologo.isChecked()){
                                    tipo = 1;
                                } else if (binding.rbCadastroBacharelado.isChecked()){
                                    tipo = 2;
                                } else{
                                    tipo = 3;
                                }

                                //criando o objeto da classe
                                Curso meuCurso = new Curso(nome, cargaHoraria, conceito, tipo);

                                //adiciona na lista
                                informacoesViewModel.getListaCurso().add(meuCurso);

                                Toast.makeText(getContext(), "Curso cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                limpaCampos();

                            } else{
                                Toast.makeText(getContext(), "Informe o tipo.", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            binding.etCadastroConceito.setError("Informe o conceito.");
                        }
                    } else{
                        binding.etCadastroCargaHoraria.setError("Informe a carga horária.");
                    }
                } else{
                    binding.etCadastroNome.setError("Informe o nome.");
                }

            }
        });

        binding.btCadastroCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });
    }

    public void limpaCampos(){
        binding.etCadastroCargaHoraria.setText("");
        binding.etCadastroConceito.setText("");
        binding.etCadastroNome.setText("");
        binding.rgCadastroTipo.clearCheck();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
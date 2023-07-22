package com.example.aplicacaocursosantonio.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aplicacaocursosantonio.Adapter.CursoAdapter;
import com.example.aplicacaocursosantonio.R;
import com.example.aplicacaocursosantonio.databinding.FragmentCadastroBinding;
import com.example.aplicacaocursosantonio.databinding.FragmentVisualizacaoBinding;
import com.example.aplicacaocursosantonio.modelDominio.Curso;
import com.example.aplicacaocursosantonio.viewModel.InformacoesViewModel;

public class VisualizacaoFragment extends Fragment {

    FragmentVisualizacaoBinding binding;
    InformacoesViewModel informacoesViewModel;
    CursoAdapter cursoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVisualizacaoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Instanciado o view model
        informacoesViewModel = new ViewModelProvider(getActivity()).get(InformacoesViewModel.class);

        if (informacoesViewModel.getListaCurso() != null){
            atualizaListagem();
        }
    }

    public void atualizaListagem(){
        cursoAdapter = new CursoAdapter(informacoesViewModel.getListaCurso(), trataCliqueItem);
        binding.rvVisualizacaoCursos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvVisualizacaoCursos.setItemAnimator(new DefaultItemAnimator());
        binding.rvVisualizacaoCursos.setAdapter(cursoAdapter);
    }

    CursoAdapter.CursoOnClickListener trataCliqueItem = new CursoAdapter.CursoOnClickListener() {
        @Override
        public void onClickCurso(View view, int position, Curso curso) {
            Toast.makeText(getContext(), "Nome: "+curso.getNome()
                                            +", Carga horária: "+curso.getCargaHoraria()
                                            +", Conceito: "+curso.getCargaHoraria()
                                            +", Tipo: "+curso.getTipoLiteral(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}


package com.example.aplicacaocursosantonio.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.aplicacaocursosantonio.modelDominio.Curso;
import java.util.ArrayList;

public class InformacoesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Curso>> mListaCurso;

    public ArrayList<Curso> getListaCurso(){
        if (mListaCurso == null){
            mListaCurso = new MutableLiveData<>();
            ArrayList<Curso> listaCursos = new ArrayList<>();
            mListaCurso.setValue(listaCursos);
        }
        return mListaCurso.getValue();
    }

}

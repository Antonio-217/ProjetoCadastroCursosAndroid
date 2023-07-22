package com.example.aplicacaocursosantonio.Adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplicacaocursosantonio.databinding.ItemListRowBinding;
import com.example.aplicacaocursosantonio.modelDominio.Curso;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.MyViewHolder> {
    private List<Curso> listaCursos;
    private CursoOnClickListener cursoOnClickListener;

    public CursoAdapter(List<Curso> listaCursos, CursoOnClickListener cursoOnClickListener) {
        this.listaCursos = listaCursos;
        this.cursoOnClickListener = cursoOnClickListener;
    }

    @Override
    public CursoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListRowBinding itemListRowBinding = ItemListRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemListRowBinding);
    }

    @Override
    public void onBindViewHolder(final CursoAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Curso meuCurso = listaCursos.get(position);
        holder.itemListRowBinding.tvCursoNome.setText(meuCurso.getNome());
        holder.itemListRowBinding.tvCursoTipo.setText(meuCurso.getTipoLiteral());
        /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado, deve ser feita a conversão com o String.valueOf() */

        // tratando o clique no item
        if (cursoOnClickListener != null) {
            holder.itemListRowBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cursoOnClickListener.onClickCurso(holder.itemView, position, meuCurso);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public  ItemListRowBinding itemListRowBinding;
        public MyViewHolder(ItemListRowBinding itemListRowBinding) {
            super(itemListRowBinding.getRoot());
            this.itemListRowBinding = itemListRowBinding;
        }
    }

    public interface CursoOnClickListener {
        public void onClickCurso(View view, int position, Curso curso);
    }

}

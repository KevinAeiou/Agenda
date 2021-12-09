package com.kevin.agenda.dao;

import androidx.annotation.Nullable;

import com.kevin.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorIds);
        alunos.add(aluno);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorIds++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if (alunoEncontrado != null){
            int posicaodoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaodoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a : alunos){
            if (a.getId()== aluno.getId()){
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno alunoEscolhido) {
        Aluno alunoDevolvido = buscaAlunoPeloId(alunoEscolhido);
        if (alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }
}

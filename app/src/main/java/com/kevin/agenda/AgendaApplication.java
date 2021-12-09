package com.kevin.agenda;

import android.app.Application;

import com.kevin.agenda.dao.AlunoDAO;
import com.kevin.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosTeste();
    }

    private void criaAlunosTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Alex", "1122223333", "alex@alura.com.br"));
        dao.salva(new Aluno("Fran", "1122223333", "fran@gmail.com"));
    }
}

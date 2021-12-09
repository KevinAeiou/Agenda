package com.kevin.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kevin.agenda.R;
import com.kevin.agenda.model.Aluno;
import com.kevin.agenda.ui.ListaAlunosView;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    public static final String CHAVE_ALUNO = "aluno";
    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover){
            listaAlunosView.confirmaRemocao(item);

        }
        return super.onContextItemSelected(item);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton fb = findViewById(R.id.fabNovoAluno);
        fb.setOnClickListener(v -> abreformularioModoNovoAluno());
    }

    private void abreformularioModoNovoAluno() {
        startActivity(new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activitylistaAlunos);
        listaAlunosView.configuraAdapter(listaDeAlunos);
        configuraListenerCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void configuraListenerCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((parent, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno)parent.getItemAtPosition(position);
            abreFormularioEditaAluno(alunoEscolhido);
        });
    }

    private void abreFormularioEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }
}
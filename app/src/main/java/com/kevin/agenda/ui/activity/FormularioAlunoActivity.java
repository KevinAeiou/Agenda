package com.kevin.agenda.ui.activity;

import static com.kevin.agenda.ui.activity.ListaAlunosActivity.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.kevin.agenda.R;
import com.kevin.agenda.dao.AlunoDAO;
import com.kevin.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita Aluno";
    private EditText campoNome, campoTelefone, campoEmail;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)){
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno)dados.getSerializableExtra("aluno");
            preencheCampos();
        }else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        campoTelefone.setText(aluno.getTelefone());
    }

    private void finalizaFormulario() {
        preencheAluno();
        if(aluno.temIdValido()){
            dao.edita(aluno);
        }else {
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializacaoCampos() {
        campoNome = findViewById(R.id.activityFormularioAlunoNome);
        campoTelefone = findViewById(R.id.activityFormularioAlunoTelefone);
        campoEmail = findViewById(R.id.activityFormularioAlunoEmail);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}
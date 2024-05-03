package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    EditText  praticienEt, motifEt, bilanEt, medicamentEt, avis_visiteurEt;
    TextView dateEt, id_formEt;
    ProgressBar pb;
    Button btn_up;

    String date,praticien,motif, bilan, medicament, avis_visiteur, id_form;
    String url = "https://mescompterendu.com/API_Mobile/update_form.php";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp_modif);
        dateEt = findViewById(R.id.dateEt);
        praticienEt = findViewById(R.id.praticienEt);
        motifEt = findViewById(R.id.motifEt);
        bilanEt = findViewById(R.id.bilanEt);
        medicamentEt = findViewById(R.id.medicamentEt);
        avis_visiteurEt = findViewById(R.id.avis_visiteurEt);
        btn_up = findViewById(R.id.btn_up);
        pb = findViewById(R.id.pb);
        id_formEt = findViewById(R.id.id_formEt);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            id_form = bundle.getString("id");
            date = bundle.getString("d");
            praticien = bundle.getString("p");
            motif = bundle.getString("m");
            bilan = bundle.getString("b");
            medicament = bundle.getString("med");
            avis_visiteur = bundle.getString("a");
            id_form = bundle.getString("id");

            dateEt.setText(date);
            praticienEt.setText(praticien);
            motifEt.setText(motif);
            bilanEt.setText(bilan);
            medicamentEt.setText(medicament);
            avis_visiteurEt.setText(avis_visiteur);
            id_formEt.setText(id_form);
        }else {
            Toast.makeText(this, "not recevied", Toast.LENGTH_SHORT).show();
        }

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);

                String praticienup = praticienEt.getText().toString();
                String motifup = motifEt.getText().toString();
                String bilanup = bilanEt.getText().toString();
                String medicamentup = medicamentEt.getText().toString();
                String avis_visiteurup = avis_visiteurEt.getText().toString();



                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dateEt.setText(date);
                        praticienEt.setText(praticien);
                        motifEt.setText(motif);
                        bilanEt.setText(bilan);
                        medicamentEt.setText(medicament);
                        avis_visiteurEt.setText(avis_visiteur);
                        id_formEt.setText(id_form);
                        pb.setVisibility(View.INVISIBLE);
                        Toast.makeText(EditActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EditActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        pb.setVisibility(View.INVISIBLE);
                    }
                }){

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> param = new HashMap<String, String>();


                        param.put("id_form", id_formEt.getText().toString());
                        param.put("praticien", praticienup);
                        param.put("motif", motifup);
                        param.put("bilan", bilanup);
                        param.put("medicament", medicamentup);
                        param.put("avis_visiteur", avis_visiteurup);

                        return param;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(EditActivity.this);
                requestQueue.add(request);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gsb_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item2:
                Intent acceuil = new Intent(EditActivity.this, MainActivity2.class);
                startActivity(acceuil);
                return true;
            case R.id.item3:
                Intent cr = new Intent(EditActivity.this, FormActivity.class);
                startActivity(cr);
                return true;
            case R.id.item4:
                Toast.makeText(this, "vous êtes déconnecter", Toast.LENGTH_LONG).show();
                Intent logout = new Intent(EditActivity.this, MainActivity.class);
                startActivity(logout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
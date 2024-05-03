package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.myapplication.param.idUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {
EditText praticienEt, dateEt, motifEt, bilanEt, medicamentEt, avis_Et;
Button button;
String url = "https://mescompterendu.com/API_Mobile/form.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        praticienEt = findViewById(R.id.praticien_Et);
        dateEt = findViewById(R.id.date_Et);
        motifEt = findViewById(R.id.motif_Et);
        bilanEt = findViewById(R.id.bilan_Et);
        medicamentEt = findViewById(R.id.medicament_Et);
        avis_Et = findViewById(R.id.avis_Et);
        button = findViewById(R.id.submit_btn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String praticien = praticienEt.getText().toString();
                String date = dateEt.getText().toString();
                String motif = motifEt.getText().toString();
                String bilan = bilanEt.getText().toString();
                String medicament = medicamentEt.getText().toString();
                String avis_visiteur = avis_Et.getText().toString();


                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        praticienEt.setText("");
                        dateEt.setText("");
                        motifEt.setText("");
                        bilanEt.setText("");
                        medicamentEt.setText("");
                        avis_Et.setText("");
                        Toast.makeText(FormActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        Intent acceuil = new Intent(FormActivity.this, MainActivity2.class);
                        startActivity(acceuil);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(FormActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("praticien", praticien);
                        param.put("date", date);
                        param.put("motif", motif);
                        param.put("medicament",medicament);
                        param.put("avis_visiteur", avis_visiteur);
                        param.put("id_user", String.valueOf(idUser));
                        return param;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(FormActivity.this);
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
                Intent acceuil = new Intent(FormActivity.this, MainActivity2.class);
                startActivity(acceuil);
                return true;
            case R.id.item3:
                Intent cr = new Intent(FormActivity.this, FormActivity.class);
                startActivity(cr);
                return true;
            case R.id.item4:
                Toast.makeText(this, "vous êtes déconnecter", Toast.LENGTH_LONG).show();
                Intent logout = new Intent(FormActivity.this, MainActivity.class);
                startActivity(logout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
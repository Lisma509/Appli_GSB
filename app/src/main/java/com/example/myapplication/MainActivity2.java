package com.example.myapplication;

import static android.widget.Toast.LENGTH_SHORT;

import static com.example.myapplication.param.idUser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    ListView mListView;
    MyAdapter mMyAdapter;
    DataClass mDataClass;
    String url = "https://mescompterendu.com/API_Mobile/view_form.php";
    public  static ArrayList<DataClass> sDataClassArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuil);

        mListView = findViewById(R.id.listeview);
        mMyAdapter = new MyAdapter(this,sDataClassArrayList);
        mListView.setAdapter(mMyAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String date = sDataClassArrayList.get(i).getDate();
                String praticien = sDataClassArrayList.get(i).getPraticien();
                String motif = sDataClassArrayList.get(i).getMotif();
                String bilan = sDataClassArrayList.get(i).getBilan();
                String medicament = sDataClassArrayList.get(i).getMedicament();
                String avis_visiteur = sDataClassArrayList.get(i).getAvis_visiteur();
                String id_form = sDataClassArrayList.get(i).getId_form();
                String id_user = sDataClassArrayList.get(i).getId_user();



                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                CharSequence[] items = {"Modifier"};
                builder.setTitle(sDataClassArrayList.get(i).getDate());
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainActivity2.this, EditActivity.class);
                                intent.putExtra("d",date);
                                intent.putExtra("p", praticien);
                                intent.putExtra("b", bilan);
                                intent.putExtra("m", motif);
                                intent.putExtra("med", medicament);
                                intent.putExtra("a", avis_visiteur);
                                intent.putExtra("id", id_form);

                                startActivity(intent);

                                break;

                        }
                    }
                });

                builder.create().show();
            }
        });

        getData();
    }



    private void getData() {
        StringRequest request =  new StringRequest(Request.Method.POST,url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){

                        Bundle bundle = getIntent().getExtras();



                        sDataClassArrayList.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (sucess.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String date = object.getString("date");
                                    String praticien = object.getString("praticien");
                                    String motif = object.getString("motif");
                                    String bilan = object.getString("bilan");
                                    String medicament = object.getString("medicament");
                                    String avis_visiteur = object.getString("avis_visiteur");
                                    String id_form = object.getString("id_form");
                                    String id_user = object.getString("id_user");

                                    mDataClass = new DataClass(date, praticien,motif, bilan, medicament, avis_visiteur, id_form, id_user);
                                    sDataClassArrayList.add(mDataClass);
                                    mMyAdapter.notifyDataSetChanged();
                                }
                            }

                        } catch (Exception e) {

                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(MainActivity2.this, error.getMessage(), LENGTH_SHORT).show();


                    }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<>();
                param.put("id_user", String.valueOf(idUser));
                return param;
            }
        };

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
                    requestQueue.add(request);

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
                Intent acceuil = new Intent(MainActivity2.this, MainActivity2.class);
                startActivity(acceuil);
                return true;
            case R.id.item3:
                Intent cr = new Intent(MainActivity2.this, FormActivity.class);
                startActivity(cr);
                return true;
            case R.id.item4:
                Toast.makeText(this, "vous êtes déconnecter", Toast.LENGTH_LONG).show();
                Intent logout = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(logout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
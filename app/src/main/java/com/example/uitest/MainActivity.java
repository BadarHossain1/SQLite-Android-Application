package com.example.uitest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name, location, price;
    Button insert, update, delete, view;

    DBHelper DB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name);
        location = findViewById(R.id.location);
        price = findViewById(R.id.price);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String locationTXT = location.getText().toString();
                String priceTXT = price.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, locationTXT, priceTXT);

                if(checkinsertdata==true)
                {
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this, "New Entry not inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String locationTXT = location.getText().toString();
                String priceTXT = price.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, locationTXT, priceTXT);

                if(checkupdatedata==true)
                {
                    Toast.makeText(MainActivity.this, " Entry Updated", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this, " Entry not updated", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();


                Boolean checkdeletedata = DB.deletedata(nameTXT);

                if(checkdeletedata==true)
                {
                    Toast.makeText(MainActivity.this, " Entry deleted", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this, " Entry not deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Property Name : "+res.getString(0)+"\n");
                    buffer.append("Location : "+res.getString(1)+"\n");
                    buffer.append("Price in Dollars : "+res.getString(2)+"\n\n");

                }
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("User Entries");
//                builder.setMessage(buffer.toString());
//                builder.show();

                Intent intent = new Intent(MainActivity.this, viewUsersActivity.class);
                intent.putExtra("user_data", buffer.toString());
                startActivity(intent);

            }
        });





    }

}

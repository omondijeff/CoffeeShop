package com.example.coffeeshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button_add, button_minus, button_order;
    TextView textView1;
    TextView priceText;
    EditText name,quantity;
    String username,addText,c,result;
    int addition;
    int coffeePrice=15;
    int chocolatePrice=25;
    int creamPrice=20;
    CheckBox choco,cream;
    LinearLayout mainLayout;

    int totalPrice;
    String toppings=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        quantity=findViewById(R.id.quantity);
        button_add=findViewById(R.id.button1);
        textView1=findViewById(R.id.textViewadd);
        priceText=findViewById(R.id.priceView);
        choco=findViewById(R.id.chkChoco);
        mainLayout=findViewById(R.id.main);
        cream=findViewById(R.id.chkCream);
        cream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bill(toppings);
            }
        });
        choco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bill(toppings);
            }
        });
    }
    public void order(View view) {

        if (cream.isChecked() && choco.isChecked())
        {
            toppings="Checked All";
            Toast.makeText(getApplicationContext(),toppings,Toast.LENGTH_SHORT).show();
        }
        else if(choco.isChecked())
        {
            toppings="Chocolate Topping";
            Toast.makeText(getApplicationContext(),toppings,Toast.LENGTH_SHORT).show();
        }
        else if (cream.isChecked())
            {
                toppings="Cream Topping";
                Toast.makeText(getApplicationContext(),toppings,Toast.LENGTH_SHORT).show();
            }
        else{
            toppings="No topping";
            Toast.makeText(getApplicationContext(),toppings,Toast.LENGTH_SHORT).show();
        }

        bill(toppings);
        order();

    }

    public void add(View view) {
        addText = textView1.getText().toString();
        addition = Integer.parseInt(addText);

        if (addition >= 10) {
            Toast.makeText(getApplicationContext(), "You cannot order more than 10 Coffee", Toast.LENGTH_LONG).show();
        } else {
            addition++;
            c = String.valueOf(addition);
            textView1.setText(c);
            Toast.makeText(getApplicationContext(), c, Toast.LENGTH_SHORT).show();
            add();
            bill(toppings);
        }
    }
        public void sub (View view)
        {
            addText = textView1.getText().toString();
            addition = Integer.parseInt(addText);
            if (addition <= 1) {
                Toast.makeText(getApplicationContext(), "You cannot order less than 1 Coffee", Toast.LENGTH_LONG).show();
            } else {
                addition--;
                String c = String.valueOf(addition);
                textView1.setText(c);
                Toast.makeText(getApplicationContext(), c, Toast.LENGTH_SHORT).show();
                sub();
                bill(toppings);
            }

        }


    public void order(){

    }
    public void add(){

    }
    public void sub(){}

    public void bill(String a){
        if (a=="Checked All"){
            totalPrice=chocolatePrice+coffeePrice+creamPrice;
        } else if(a=="Chocolate Topping"){
            totalPrice=(chocolatePrice+coffeePrice)*addition;
        } else if(a=="Cream Topping"){
            totalPrice=(creamPrice+coffeePrice)*addition;
        } else {
            totalPrice=(coffeePrice)*addition;
        }
        priceText.setText("Total Price :"+totalPrice);
        Toast.makeText(getApplicationContext(),String.valueOf(totalPrice),Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.clear:
                textView1.setText("1");
                name.setText("");
                cream.setSelected(false);
                choco.setSelected(false);
                return true;

            case R.id.backgroundRed:
                mainLayout.setBackgroundColor(getResources().getColor(R.color.colorRed));
                return true;
            case R.id.backgroundGreen:
                mainLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                return true;
            case   R.id.hide_topping:
                cream.setVisibility(View.GONE);
                choco.setVisibility(View.GONE);
                return true;
            case R.id.show_topping:
                cream.setVisibility(View.VISIBLE);
                choco.setVisibility(View.VISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

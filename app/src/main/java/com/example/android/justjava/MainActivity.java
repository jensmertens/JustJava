package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int coffeePrice = 5;
    int whippedCreamPrice = 1;
    int chocolatePrice = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View someView) {


        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText name = (EditText) findViewById(R.id.Name);
        String orderName = name.getText().toString();
        String priceMessage = createOrderSummary(coffeePrice, hasWhippedCream, hasChocolate, orderName);
        String nameString = name.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: jensmertens@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + nameString);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    /**
     * This method is for the increment button
     */
    public void increment(View view) {

        if (quantity > 99) {
            Toast toast = Toast.makeText(this, "Can't go above 100", Toast.LENGTH_SHORT);
            toast.show();

        } else
            quantity = quantity + 1;
        displayQuantity(quantity);

    }

    /**
     * This method is for the decrement button
     */
    public void decrement(View view) {

        if (quantity < 2) {

            Toast toast = Toast.makeText(this, "Can't go below 1", Toast.LENGTH_SHORT);
            toast.show();

        } else
            quantity = quantity - 1;
        displayQuantity(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);


    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     * <p/>
     * /* @param quantity is the number of cups of coffee ordered
     */

    private int calculatePrice(boolean hasChocolate, boolean hasWhippedCream) {
        int baseprice = coffeePrice;
        if (hasChocolate) {
            baseprice = baseprice + chocolatePrice;
        }
        if (hasWhippedCream) {
            baseprice = baseprice + whippedCreamPrice;
        }
        return quantity * baseprice;


    }


    private String createOrderSummary(int coffeePrice, boolean hasWhippedCream, boolean hasChocolate, String orderName) {

        String priceMessage = "Name: " + orderName;
        priceMessage += "\nQuatity: " + quantity;
        priceMessage += "\nTotal:€ " + calculatePrice(hasChocolate, hasWhippedCream);
        priceMessage += "\nWhipped Cream? " + hasWhippedCream;
        priceMessage += "\nChocolate? " + hasChocolate;
        priceMessage += "\nThank You!";


        return priceMessage;
    }
}

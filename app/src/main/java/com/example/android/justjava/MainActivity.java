package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 0;
    int coffeePrice = 5;
    String costumerName = "Jens Mertens";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        String priceMessage = createOrderSummary();

        displayMessage(priceMessage);


    }

    /**
     * This method is for the increment button
     */
    public void increment(View view) {


        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    /**
     * This method is for the decrement button
     */
    public void decrement(View view) {


        quantity = quantity - 1;
        displayQuantity(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
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

    private int calculatePrice() {
        return quantity * coffeePrice;
    }

    private String createOrderSummary() {
        return "Name: " + costumerName + "\n" + "Quantity: " + quantity + "\n" + "Total: € " + calculatePrice() + "\nThank You";


    }
}

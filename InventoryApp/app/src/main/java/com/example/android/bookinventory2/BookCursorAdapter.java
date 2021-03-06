package com.example.android.bookinventory2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.bookinventory2.data.BookContract.BookEntry;

public class BookCursorAdapter extends CursorAdapter{

    public BookCursorAdapter(Context context, Cursor cursor)
    {super(context, cursor, 0);}


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        //Find views that needs
        TextView nameTextView = view.findViewById(R.id.name);
        TextView priceTextView = view.findViewById(R.id.price);
        TextView quantityTextView = view.findViewById(R.id.quantity);

        // Find the columns that needs
        final int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_QUANTITY);

        // Read the cursors
        String bookName = cursor.getString(nameColumnIndex);
        String bookPrice = cursor.getString(priceColumnIndex);
        final int bookQuantity = cursor.getInt(quantityColumnIndex);

        // TextViews updating
        nameTextView.setText(bookName);
        priceTextView.setText("Price: " + bookPrice);
        quantityTextView.setText(bookQuantity + " left");

        // book id + quantity
        final int bookID = cursor.getInt(idColumnIndex);
        final int quantity = cursor.getInt(quantityColumnIndex);

        // buy button
        Button buyButton = view.findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatalogActivity Activity = (CatalogActivity) context;
                Activity.buyBook(bookID, quantity);
            }
        });


    }
}

package com.monu.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.monu.newsapp.BooksInDetailActivity;
import com.monu.newsapp.NewsInDetailActivity;
import com.monu.newsapp.R;
import com.monu.newsapp.parameter.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.ViewHolder> {

    private List<Book> books = new ArrayList<>();
    private Context context;
    private String Free = "FREE";
    public Recycler_Adapter(Context context, List<Book> bookList) {
        this.context = context;
        books = bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_item,parent,false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Book book = books.get(position);
        Picasso.with(context).load(book.getImage()).into(holder.book_image);
        holder.book_title.setText(book.getTittle());
        if(book.getTittle().length() > 40 )
            holder.book_title.append("...");
        String auth = book.getAuthors();
        holder.book_author.setText(auth);
        holder.book_ratingBar.setRating((float) book.getRating());
        if(book.getPrice() != 0.00)
            holder.book_price.setText(String.valueOf(book.getPrice()));
        else
            holder.book_price.setText(Free);
        String page = book.getPages()+" Pages";
        holder.books_page.setText(page);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BooksInDetailActivity.class);
                intent.putExtra("url", book.getPreView());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView book_image;
        TextView book_title,book_author,book_price,books_page;
        RatingBar book_ratingBar;
        CardView cardView;
        ViewHolder(View itemView) {
            super(itemView);
            book_image = (ImageView)itemView.findViewById(R.id.booksapp_image);
            book_title = (TextView) itemView.findViewById(R.id.booksapp_name);
            book_author = (TextView)itemView.findViewById(R.id.booksapp_author);
            book_ratingBar = (RatingBar)itemView.findViewById(R.id.booksapp_rating);
            book_price = (TextView)itemView.findViewById(R.id.booksapp_price);
            books_page = (TextView)itemView.findViewById(R.id.booksapp_pages);
            cardView = itemView.findViewById(R.id.bcardView);
        }
    }
}

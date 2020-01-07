package com.yaramobile.batman.database;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yaramobile.batman.R;

import java.util.Objects;

@Entity(tableName = "batman")
public class BatmanEntity {

    /**
     * Title : Batman Begins
     * Year : 2005
     * imdbID : tt0372784
     * Type : movie
     * Poster : https://m.media-amazon.com/images/M/MV5BZmUwNGU2ZmItMmRiNC00MjhlLTg5YWUtODMyNzkxODYzMmZlXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg
     */

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    @NonNull
    private int id;

    @ColumnInfo(name = "Title")
    private String Title;

    @ColumnInfo(name = "Year")
    private String Year;

    @ColumnInfo(name = "imdbID")
    private String imdbID;

    @ColumnInfo(name = "Type")
    private String Type;

    @ColumnInfo(name = "Poster")
    private String Poster;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String Poster) {
        this.Poster = Poster;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatmanEntity batman = (BatmanEntity) o;
        return Objects.equals(imdbID, batman.imdbID);
    }

    @BindingAdapter("batmanTitle")
    public static void setBatmanTitle(TextView textView, BatmanEntity item) {
        if (item != null) {
            textView.setText(item.Title);
        }
    }

    @BindingAdapter("batmanImage")
    public static void setBatmanImage(ImageView imageView, BatmanEntity item) {
        if (!TextUtils.isEmpty(item.Poster)) {
            Glide.with(imageView)
                    .load(item.Poster)
                    .centerCrop()
                    .apply(new RequestOptions().
                            placeholder(R.drawable.imdb_placeholder))
                    .into(imageView);

        }
    }

    public static final DiffUtil.ItemCallback<BatmanEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<BatmanEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull BatmanEntity oldItem, @NonNull BatmanEntity newItem) {
                    return oldItem.getImdbID().contentEquals(newItem.getImdbID());
                }

                @Override
                public boolean areContentsTheSame(@NonNull BatmanEntity oldContent, @NonNull BatmanEntity newContent) {
                    return oldContent.equals(newContent);
                }
            };
}

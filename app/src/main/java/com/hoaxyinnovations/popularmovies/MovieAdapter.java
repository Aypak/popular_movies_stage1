package com.hoaxyinnovations.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hoaxyinnovations.popularmovies.utlities.Movie;
import com.squareup.picasso.Picasso;

import static com.hoaxyinnovations.popularmovies.utlities.NetworkUtils.moviePosterUrl;


class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Movie[] mMovieList;

    private final Context mContext;

    public MovieAdapter(Context context, Movie[] movies) {
        mContext = context;
        mMovieList = movies;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View moviesView = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(moviesView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovieList[position];
        ImageView moviePoster = holder.moviePoster;
        String posterPath = (moviePosterUrl(movie.poster_path)).toString();
        Picasso.with(getContext()).load(posterPath).into(moviePoster);

    }

    @Override
    public int getItemCount() {
        if (mMovieList == null) {
            return 0;
        } else {
            return mMovieList.length;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView moviePoster;

        ViewHolder(View itemView) {
            super(itemView);
            moviePoster = (ImageView) itemView.findViewById(R.id.movie_list_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = mMovieList[position];
            Context context = getContext();
            Class destinationClass = DetailActivity.class;
            Intent intent = new Intent(context, destinationClass);
            intent.putExtra("movie", movie);
            context.startActivity(intent);

        }
    }

    void setMovieData(Movie[] movieData) {
        mMovieList = movieData;
        notifyDataSetChanged();
    }

}




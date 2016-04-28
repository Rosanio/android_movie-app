package com.example.guest.movieapp;

/**
 * Created by Guest on 4/27/16.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.movieapp.Actor;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 4/27/16.
 */
public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ActorViewHolder> {
    private Context mContext;
    private ArrayList<Actor> mActors = new ArrayList<>();

    public ActorListAdapter(Context context, ArrayList<Actor> actors) {
        mContext = context;
        mActors = actors;
    }

    @Override
    public ActorListAdapter.ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_list_item, parent, false);
        ActorViewHolder viewHolder = new ActorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ActorListAdapter.ActorViewHolder holder, int position) {
        holder.bindActor(mActors.get(position));
    }

    @Override
    public int getItemCount() {
        return mActors.size();
    }

    public class ActorViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.actorNameTextView) TextView mActorNameTextView;
        private Context mContext;

        public ActorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();
                    Actor actor = mActors.get(itemPosition);
                    if(actor.getName().equals("Kevin Bacon")) {
                        Intent intent = new Intent(mContext, WinActivity.class);
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mContext, MovieListActivity.class);
                        intent.putExtra("actor", Parcels.wrap(actor));
                        mContext.startActivity(intent);
                    }

                }
            });
        }

        public void bindActor(Actor actor) {
            mActorNameTextView.setText(actor.getName());
        }
    }
}

package com.example.lists2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

    // определяем интерфейс слушателя
    interface OnStateClickListener {
        // когда мы будем нажимать на обьект 'State'
        // будем получать выбранный обьект нашей страны и его позицию в списке
        void onStateClick (State state, int position);
    }

    // определяем переменные в классе
    OnStateClickListener onClickListener;
    LayoutInflater inflater;
    List<State> states;

    // нужен 'Context', тк как этот кон-р мы будем вызывать в 'MainActivity'
    public StateAdapter(Context context, List<State> states,
                        OnStateClickListener onClickListener ) {
        this.onClickListener = onClickListener;
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }

    // метод, который возвращает обьект нашего 'ViewHolder',
    // который будет хранить данные по олному обьекту, в данном случае 'State'
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        // создаем новый 'ViewHolder'
        return new ViewHolder(view);
    }

    // метод. который выполняет привязку обьекта 'ViewHolder' к обьекту 'State'
    // соединяет одну страну с одним пунктом нашего меню
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        State state = states.get(position);
        holder.flagView.setImageResource(state.getFlagResource());
        holder.nameView.setText(state.getName());
        holder.capitalView.setText(state.getCapital());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 'state' - выбранная страна, 'position' - ее позиция в списке
                onClickListener.onStateClick(state, position);
            }
        });
    }

    // метод возвращает кол-во обьектов в списке
    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView flagView;
        TextView nameView, capitalView;

        // прописываем класс, из которого состоит наш 1 Item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flagView = itemView.findViewById(R.id.flag);
            nameView = itemView.findViewById(R.id.name);
            capitalView = itemView.findViewById(R.id.capital);
        }
    }
}

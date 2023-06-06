package com.example.smartstudenthelper.СontrolTask.screens.main;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.smartstudenthelper.App;
import com.example.smartstudenthelper.R;
import com.example.smartstudenthelper.СontrolTask.ControlTask;
import com.example.smartstudenthelper.СontrolTask.screens.details.ControlTaskDetailsActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ControlTaskViewHolder>
{
    private SortedList<ControlTask> sortedList;

    public Adapter() {

        sortedList = new SortedList<>(ControlTask.class, new SortedList.Callback<ControlTask>() {
            @Override
            public int compare(ControlTask o1, ControlTask o2) {
                if (!o2.isDone() && o1.isDone()) {
                    return 1;
                }
                if (o2.isDone() && !o1.isDone()) {
                    return -1;
                }
                return (int) (o2.getTimestamp() - o1.getTimestamp());
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(ControlTask oldItem, ControlTask newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(ControlTask item1, ControlTask item2) {
                return item1.getUid() == item2.getUid();
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public ControlTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ControlTaskViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ControlTaskViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<ControlTask> controlTasks) {
        sortedList.replaceAll(controlTasks);
    }

    static class ControlTaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskText;
        CheckBox completed;
        View delete;

        ControlTask controlTask;

        boolean silentUpdate;

        public ControlTaskViewHolder(@NonNull final View itemView) {
            super(itemView);

            taskText = itemView.findViewById(R.id.task_text);
            completed = itemView.findViewById(R.id.completed);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ControlTaskDetailsActivity.start((Activity) itemView.getContext(), controlTask);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().getControlTaskDao().delete(controlTask);
                }
            });

            completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (!silentUpdate) {
                        controlTask.setDone(checked);
                        App.getInstance().getControlTaskDao().update(controlTask);
                    }
                    updateStrokeOut();
                }
            });

        }

        public void bind(ControlTask controlTask) {
            this.controlTask = controlTask;

            taskText.setText(controlTask.getName());
            updateStrokeOut();

            silentUpdate = true;
            completed.setChecked(controlTask.isDone());
            silentUpdate = false;
        }

        private void updateStrokeOut() {
            if (controlTask.isDone()) {
                taskText.setPaintFlags(taskText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                taskText.setPaintFlags(taskText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}

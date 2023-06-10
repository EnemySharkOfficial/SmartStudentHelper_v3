package com.example.smartstudenthelper.DebtTask.screens.main;

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
import com.example.smartstudenthelper.CourseTask.CourseTask;
import com.example.smartstudenthelper.DebtTask.DebtTask;
import com.example.smartstudenthelper.DebtTask.screens.details.DebtTaskDetailsActivity;
import com.example.smartstudenthelper.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.DebtTaskViewHolder>
{
    private SortedList<DebtTask> sortedList;

    public Adapter() {

        sortedList = new SortedList<>(DebtTask.class, new SortedList.Callback<DebtTask>() {
            @Override
            public int compare(DebtTask o1, DebtTask o2) {
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
            public boolean areContentsTheSame(DebtTask oldItem, DebtTask newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(DebtTask item1, DebtTask item2) {
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
    public DebtTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DebtTaskViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DebtTaskViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<DebtTask> debtTasks) {
        sortedList.replaceAll(debtTasks);
    }

    static class DebtTaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskText;
        CheckBox completed;
        View delete;

        DebtTask debtTask;

        boolean silentUpdate;

        public DebtTaskViewHolder(@NonNull final View itemView) {
            super(itemView);

            taskText = itemView.findViewById(R.id.task_text);
            completed = itemView.findViewById(R.id.completed);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DebtTaskDetailsActivity.start((Activity) itemView.getContext(), debtTask);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().getDebtTaskDao().delete(debtTask);
                }
            });

            completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (!silentUpdate) {
                        debtTask.setDone(checked);
                        App.getInstance().getDebtTaskDao().update(debtTask);
                    }
                    updateStrokeOut();
                }
            });

        }

        public void bind(DebtTask debtTask) {
            this.debtTask = debtTask;

            taskText.setText(debtTask.getName());
            updateStrokeOut();

            silentUpdate = true;
            completed.setChecked(debtTask.isDone());
            silentUpdate = false;
        }

        private void updateStrokeOut() {
            if (debtTask.isDone()) {
                taskText.setPaintFlags(taskText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                taskText.setPaintFlags(taskText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}

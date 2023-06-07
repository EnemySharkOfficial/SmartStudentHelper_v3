package com.example.smartstudenthelper.ExamTask.screens.main;

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
import com.example.smartstudenthelper.ExamTask.ExamTask;
import com.example.smartstudenthelper.ExamTask.screens.details.ExamTaskDetailsActivity;
import com.example.smartstudenthelper.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ExamTaskViewHolder>
{
    private SortedList<ExamTask> sortedList;

    public Adapter() {

        sortedList = new SortedList<>(ExamTask.class, new SortedList.Callback<ExamTask>() {
            @Override
            public int compare(ExamTask o1, ExamTask o2) {
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
            public boolean areContentsTheSame(ExamTask oldItem, ExamTask newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(ExamTask item1, ExamTask item2) {
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
    public ExamTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExamTaskViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExamTaskViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<ExamTask> examTasks) {
        sortedList.replaceAll(examTasks);
    }

    static class ExamTaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskText;
        CheckBox completed;
        View delete;

        ExamTask examTask;

        boolean silentUpdate;

        public ExamTaskViewHolder(@NonNull final View itemView) {
            super(itemView);

            taskText = itemView.findViewById(R.id.task_text);
            completed = itemView.findViewById(R.id.completed);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ExamTaskDetailsActivity.start((Activity) itemView.getContext(), examTask);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().getExamTaskDao().delete(examTask);
                }
            });

            completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (!silentUpdate) {
                        examTask.setDone(checked);
                        App.getInstance().getExamTaskDao().update(examTask);
                    }
                    updateStrokeOut();
                }
            });

        }

        public void bind(ExamTask examTask) {
            this.examTask = examTask;

            taskText.setText(examTask.getName());
            updateStrokeOut();

            silentUpdate = true;
            completed.setChecked(examTask.isDone());
            silentUpdate = false;
        }

        private void updateStrokeOut() {
            if (examTask.isDone()) {
                taskText.setPaintFlags(taskText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                taskText.setPaintFlags(taskText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}
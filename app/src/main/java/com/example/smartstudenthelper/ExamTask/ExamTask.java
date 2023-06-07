package com.example.smartstudenthelper.ExamTask;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class ExamTask implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "timestamp")
    private long timestamp;
    @ColumnInfo(name = "done")
    private boolean done;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "commentary")
    private String commentary;
    @ColumnInfo(name = "deadline")
    private long deadline;
    @ColumnInfo(name = "reminderDate")
    private long reminderDate;

    // Конструктор класса
    public ExamTask() {
    }

    // Методы доступа к полям класса
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCommentary() {
        return commentary;
    }
    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
    public long getDeadline() {
        return deadline;
    }
    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }
    public long getReminderDate() {
        return reminderDate;
    }
    public void setReminderDate(long reminderDate) {
        this.reminderDate = reminderDate;
    }

    // Переопределение метода equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamTask examTask = (ExamTask) o;
        return uid == examTask.uid &&
                timestamp == examTask.timestamp &&
                done == examTask.done &&
                deadline == examTask.deadline &&
                reminderDate == examTask.reminderDate &&
                Objects.equals(name, examTask.name) &&
                Objects.equals(commentary, examTask.commentary);
    }

    // Переопределение метода hashCode
    @Override
    public int hashCode() {
        return Objects.hash(uid, timestamp, done, name, commentary, deadline, reminderDate);
    }

    protected ExamTask(Parcel in) {
        uid = in.readInt();
        timestamp = in.readLong();
        done = in.readByte() != 0;
        name = in.readString();
        commentary = in.readString();
        deadline = in.readLong();
        reminderDate = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeLong(timestamp);
        dest.writeByte((byte) (done ? 1 : 0));
        dest.writeString(name);
        dest.writeString(commentary);
        dest.writeLong(deadline);
        dest.writeLong(reminderDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExamTask> CREATOR = new Creator<ExamTask>() {
        @Override
        public ExamTask createFromParcel(Parcel in) {
            return new ExamTask(in);
        }

        @Override
        public ExamTask[] newArray(int size) {
            return new ExamTask[size];
        }
    };
}

package com.example.smartstudenthelper.DebtTask;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DebtTask implements Parcelable
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
    @ColumnInfo(name = "executionTime")
    private long executionTime;
    @ColumnInfo(name = "reminderDate")
    private long reminderDate;

    // Конструктор класса
    public DebtTask() {
    }

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
    public long getExecutionTime() {
        return executionTime;
    }
    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
    public long getReminderDate() {
        return reminderDate;
    }
    public void setReminderDate(long reminderDate) {
        this.reminderDate = reminderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DebtTask debtTask = (DebtTask) o;

        if (uid != debtTask.uid) return false;
        if (timestamp != debtTask.timestamp) return false;
        if (done != debtTask.done) return false;
        if (deadline != debtTask.deadline) return false;
        if (executionTime != debtTask.executionTime) return false;
        if (reminderDate != debtTask.reminderDate) return false;
        if (name != null ? !name.equals(debtTask.name) : debtTask.name != null) return false;
        return commentary != null ? commentary.equals(debtTask.commentary) : debtTask.commentary == null;
    }

    // Переопределение метода hashCode()
    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (done ? 1 : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (commentary != null ? commentary.hashCode() : 0);
        result = 31 * result + (int) (deadline ^ (deadline >>> 32));
        result = 31 * result + (int) (executionTime ^ (executionTime >>> 32));
        result = 31 * result + (int) (reminderDate ^ (reminderDate >>> 32));
        return result;
    }

    protected DebtTask(Parcel in) {
        uid = in.readInt();
        timestamp = in.readLong();
        done = in.readByte() != 0;
        name = in.readString();
        commentary = in.readString();
        deadline = in.readLong();
        executionTime = in.readLong();
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
        dest.writeLong(executionTime);
        dest.writeLong(reminderDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DebtTask> CREATOR = new Creator<DebtTask>() {
        @Override
        public DebtTask createFromParcel(Parcel in) {
            return new DebtTask(in);
        }

        @Override
        public DebtTask[] newArray(int size) {
            return new DebtTask[size];
        }
    };
}

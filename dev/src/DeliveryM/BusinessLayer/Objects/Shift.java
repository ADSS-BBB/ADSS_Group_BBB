package DeliveryM.BusinessLayer.Objects;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Shift {

    private int shiftid;
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;

    public Shift(int shiftid, LocalDateTime startingTime, LocalDateTime endingTime) {
        this.shiftid = shiftid;
        this.startingTime = startingTime;
        this.endingTime = endingTime;

    }

    public int getShiftid() {
        return shiftid;
    }

    public void setShiftid(int shiftid) {
        this.shiftid = shiftid;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime;
    }
}

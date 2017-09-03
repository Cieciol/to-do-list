package pl.pollub.designPatterns1.User;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.designPatterns1.Comms.CommsChannels;

@Getter
@Setter
public class User {

    private final int id;

    private String email;

    private CommsChannels chosenNotifier;

    private CommsChannels chosenReminder;

    private boolean hasPaidForSms;


    User(Builder builder){
        this.id = builder.ID;
        this.email = builder.email;
        this.chosenNotifier = builder.chosenNotifier;
        this.chosenReminder = builder.chosenReminder;
    }

    public static class Builder{

        int ID;

        String email = "";

        CommsChannels chosenNotifier = CommsChannels.NOT_CHOSEN;

        CommsChannels chosenReminder = CommsChannels.NOT_CHOSEN;

        boolean hasPaidForSms = false;

        public Builder(int id){
            this.ID = id;
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withNotifier(CommsChannels notifier){
            this.chosenNotifier = notifier;
            return this;
        }

        public Builder withReminder(CommsChannels reminder){
            this.chosenReminder = reminder;
            return this;
        }

        public Builder withSmsPayment(boolean paid){
            this.hasPaidForSms = paid;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

}

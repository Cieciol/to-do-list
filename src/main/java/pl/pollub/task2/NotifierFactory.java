package pl.pollub.task2;

public class NotifierFactory {
    private SMSNotifier smsNotifier = new SMSNotifier();
    private PushNotifier pushNotifier = new PushNotifier();
    private EmailNotifier emailNotifier = new EmailNotifier();

    Notifier getNotifier(NotifierTypes notifierType){
        switch (notifierType){
            case SmsNotifier:
                return smsNotifier;
            case PushNotifier:
                return pushNotifier;
            case EmailNotifier:
                return emailNotifier;
            default:
                return null;
        }
    }
}

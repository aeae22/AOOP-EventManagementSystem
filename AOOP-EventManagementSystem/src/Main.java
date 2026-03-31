import controller.AttendeeController;
import controller.EventController;
import controller.RegistrationController;
import view.MainMenuView;

public class Main {
    public static void main(String[] args) {
        EventController eventController = new EventController();
        AttendeeController attendeeController = new AttendeeController();
        RegistrationController registrationController =
                new RegistrationController(eventController, attendeeController);

        MainMenuView menu = new MainMenuView(
                eventController,
                attendeeController,
                registrationController
        );

        menu.start();
    }
}